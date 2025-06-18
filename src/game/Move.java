package game;
import game.pieces.*;
import misc.Pair;

public class Move {

    public Piece movedPiece;
    public Piece capturedPiece;
    //Player jogador;

    // movedPiece = tab.getPieceNaPosition(x,y)
    // capturedPiece = tab.getPieceNaPosition(x,y)
    public Move(Piece movedPiece, Piece capturedPiece){
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    public boolean ValidarRoque(Board board) {
        if (!(movedPiece instanceof King && capturedPiece instanceof Rook))
            return false;

        King king = (King) movedPiece;
        Rook rook = (Rook) capturedPiece;

        // ambos não devem ter movido
        if (king.jaMovido || rook.jaMovido)
            return false;

        Pair posKing = king.GetBoardPosition();
        Pair posRook = rook.GetBoardPosition();

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


        char moverColor = king.GetColorPiece();
        char oponenteColor = moverColor == 'w' ? 'b' : 'w';

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
                for (Pair mv : p.GetMoviments()) {
                    if (mv.IsEqualsTo(alvo))
                        return true;
                }
            }
        }
        return false;
    }

    // valida se a move pode ser feita e muda o board
    public boolean ValidarMove(Board board){

	for (Pair p : this.movedPiece.GetMoviments()) {
	    if(p.x == this.capturedPiece.GetBoardPosition().x && p.y == this.capturedPiece.GetBoardPosition().y){
		return true;
	    }
        }

	return false;
    }

    private boolean IsTherePieceInBetween(Board board){
        // +1 if capturedPiece.GetBoardPosition() > movedPiece.GetBoardPosition()
        //  0 if capturedPiece.GetBoardPosition() == movedPiece.GetBoardPosition()
        // -1 if capturedPiece.GetBoardPosition() < movedPiece.GetBoardPosition()

        int dx = Integer.compare(this.capturedPiece.GetBoardPosition().x, this.movedPiece.GetBoardPosition().x);
        int dy = Integer.compare(this.capturedPiece.GetBoardPosition().y, this.movedPiece.GetBoardPosition().y);

        int x = movedPiece.GetBoardPosition().x + dx;
        int y = movedPiece.GetBoardPosition().y + dy;

        while (! this.capturedPiece.GetBoardPosition().IsEqualsTo(new Pair(x, y)) ) {
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
            if (p instanceof Pawn && p.GetColorPiece() == 'w') {
                //promove para queen
                Queen queen = new Queen(x, 0, 'w');
                board.UpdateBoard(
                    new Move(p, queen)
                );
                promoveu = true;
            }
        }
        //verificação para as pretas
        for(int x = 0; x < 8; x++) {
            Piece p = board.GetPieceInPosition(x,7);
            if (p instanceof Pawn && p.GetColorPiece() == 'b') {
                //promove para queen
                Queen queen = new Queen(x, 7, 'b');
                board.UpdateBoard(
                    new Move(p, queen)
                );
                promoveu = true;
            }
        }

        return promoveu;
    }

    public char idPromocao(char cor, char tipoMaiusculo) {
	if (cor == 'w') {
	    // jogador branco: usa letra maiúscula
	    return tipoMaiusculo;
	} else {
		// jogador preto: usa letra minúscula
		return Character.toLowerCase(tipoMaiusculo);
	}
    }

}
