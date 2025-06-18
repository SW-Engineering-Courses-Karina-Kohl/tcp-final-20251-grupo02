package game;
import game.pieces.*;
import misc.Pair;

public class Jogada {

    public Piece pieceMovida;
    public Piece piece_capturada;
    //Player jogador;

    // pieceMovida = tab.getPieceNaPosition(x,y)
    // piece_capturada = tab.getPieceNaPosition(x,y)
    public Jogada(Piece pieceMovida, Piece piece_capturada){
        this.pieceMovida = pieceMovida;
        this.piece_capturada = piece_capturada;
    }

    public boolean ValidarRoque(Board board) {
        if (!(pieceMovida instanceof King && piece_capturada instanceof Rook))
            return false;

        King rei = (King) pieceMovida;
        Rook torre = (Rook) piece_capturada;

        // ambos não devem ter movido
        if (rei.jaMovido || torre.jaMovido)
            return false;

        Pair posKing = rei.posicaoBoard;
        Pair posRook = torre.posicaoBoard;

        if (posKing.y != posRook.y)
            return false;

        int dir = Integer.compare(posRook.x, posKing.x);

        // verificar casas entre King e Rook
        int x = posKing.x + dir;
        while (x != posRook.x) {
            if (!(board.GetPieceInPosition(x, posKing.y) instanceof Blank))
                return false;
            x += dir;
        }


        char moverColor = rei.GetColorPiece();
        char oponenteColor = moverColor == 'b' ? 'p' : 'b';

        Pair[] posicoes = new Pair[] {
            posKing,
            new Pair(posKing.x + dir, posKing.y),
            new Pair(posKing.x + 2*dir, posKing.y)
        };
        for (Pair p : posicoes) {
            if (isAtacado(board, p, oponenteColor))
                return false;
        }

        return true;
    }

    // verifica se uma casa está atacada por alguma peça da cor atacante, se houver, não há roque
    private boolean isAtacado(Board board, Pair alvo, char atacanteColor) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Piece p = board.GetPieceInPosition(x, y);
                if (p.GetColorPiece() != atacanteColor)
                    continue;
                for (Pair mv : p.GetMovimentos()) {
                    if (mv.equals(alvo))
                        return true;
                }
            }
        }
        return false;
    }

    // valida se a jogada pode ser feita e muda o board
    public boolean ValidarJogada(Board board){

	for (Pair p : this.pieceMovida.GetMovimentos()) {
	    if(p.x == this.piece_capturada.posicaoBoard.x && p.y == this.piece_capturada.posicaoBoard.y){
		return true;
	    }
        }

	return false;
    }

    private boolean IsTherePieceInBetween(Board board){
        // +1 if piece_capturada.posicaoBoard > pieceMovida.posicaoBoard
        //  0 if piece_capturada.posicaoBoard == pieceMovida.posicaoBoard
        // -1 if piece_capturada.posicaoBoard < pieceMovida.posicaoBoard

        int dx = Integer.compare(this.piece_capturada.posicaoBoard.x, this.pieceMovida.posicaoBoard.x);
        int dy = Integer.compare(this.piece_capturada.posicaoBoard.y, this.pieceMovida.posicaoBoard.y);

        int x = pieceMovida.posicaoBoard.x + dx;
        int y = pieceMovida.posicaoBoard.y + dy;

        while (! this.piece_capturada.posicaoBoard.equals(new Pair(x, y)) ) {
            if (!(board.GetPieceInPosition(x, y) instanceof Blank)) {
                return true;
            }

            x += dx;
            y += dy;
        }

        return false;
    }

    //private boolean ValidarXeque(Board board) {

    //}

    //private boolean ValidarXequeMate(Board board) {

    //}

   public boolean ValidarPromocaoPawn(Board board) {
        boolean promoveu = false;
        //verificação para as brancas
        for(int x = 0; x < 8; x++) {
            Piece p = board.GetPieceInPosition(x,0);
            if (p instanceof Pawn && p.GetColorPiece() == 'b') {
                //promove para rainha
                Queen dama = new Queen(x, 0, 'b');
                board.MudancaNoBoard(
                    new Jogada(p, dama)
                );
                promoveu = true;
            }
        }
        //verificação para as pretas
        for(int x = 0; x < 8; x++) {
            Piece p = board.GetPieceInPosition(x,7);
            if (p instanceof Pawn && p.GetColorPiece() == 'p') {
                //promove para rainha
                Queen dama = new Queen(x, 7, 'p');
                board.MudancaNoBoard(
                    new Jogada(p, dama)
                );
                promoveu = true;
            }
        }

        return promoveu;
    }

    public char idPromocao(char cor, char tipoMaiusculo) {
	if (cor == 'b') {
	    // jogador branco: usa letra maiúscula
	    return tipoMaiusculo;
	} else {
		// jogador preto: usa letra minúscula
		return Character.toLowerCase(tipoMaiusculo);
	}
    }

}
