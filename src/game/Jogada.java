package game;
import game.pieces.*;
import misc.Pair;

public class Jogada {

    public Piece pecaMovida;
    public Piece peca_capturada;
    //Player jogador;

    // pecaMovida = tab.getPieceNaPosicao(x,y)
    // peca_capturada = tab.getPieceNaPosicao(x,y)
    public Jogada(Piece pecaMovida, Piece peca_capturada){
        this.pecaMovida = pecaMovida;
        this.peca_capturada = peca_capturada;
    }

    public boolean ValidarRoque(Board board) {
        if (!(pecaMovida instanceof King && peca_capturada instanceof Rook))
            return false;

        King rei = (King) pecaMovida;
        Rook torre = (Rook) peca_capturada;

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
            if (!(board.GetPieceNaPosicao(x, posKing.y) instanceof Blank))
                return false;
            x += dir;
        }


        char moverOurColor = rei.GetOurColorPiece();
        char oponenteOurColor = moverOurColor == 'b' ? 'p' : 'b';

        Pair[] posicoes = new Pair[] {
            posKing,
            new Pair(posKing.x + dir, posKing.y),
            new Pair(posKing.x + 2*dir, posKing.y)
        };
        for (Pair p : posicoes) {
            if (isAtacado(board, p, oponenteOurColor))
                return false;
        }

        return true;
    }

    // verifica se uma casa está atacada por alguma peça da cor atacante, se houver, não há roque
    private boolean isAtacado(Board board, Pair alvo, char atacanteOurColor) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Piece p = board.GetPieceNaPosicao(x, y);
                if (p.GetOurColorPiece() != atacanteOurColor)
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

	for (Pair p : this.pecaMovida.GetMovimentos()) {
	    if(p.x == this.peca_capturada.posicaoBoard.x && p.y == this.peca_capturada.posicaoBoard.y){
		return true;
	    }
        }

	return false;
    }

    private boolean IsTherePieceInBetween(Board board){
        // +1 if peca_capturada.posicaoBoard > pecaMovida.posicaoBoard
        //  0 if peca_capturada.posicaoBoard == pecaMovida.posicaoBoard
        // -1 if peca_capturada.posicaoBoard < pecaMovida.posicaoBoard

        int dx = Integer.compare(this.peca_capturada.posicaoBoard.x, this.pecaMovida.posicaoBoard.x);
        int dy = Integer.compare(this.peca_capturada.posicaoBoard.y, this.pecaMovida.posicaoBoard.y);

        int x = pecaMovida.posicaoBoard.x + dx;
        int y = pecaMovida.posicaoBoard.y + dy;

        while (! this.peca_capturada.posicaoBoard.equals(new Pair(x, y)) ) {
            if (!(board.GetPieceNaPosicao(x, y) instanceof Blank)) {
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
            Piece p = board.GetPieceNaPosicao(x,0);
            if (p instanceof Pawn && p.GetOurColorPiece() == 'b') {
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
            Piece p = board.GetPieceNaPosicao(x,7);
            if (p instanceof Pawn && p.GetOurColorPiece() == 'p') {
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
}
