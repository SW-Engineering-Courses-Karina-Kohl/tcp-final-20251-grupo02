package game;
import game.pieces.*;
import misc.Pair;

public class Move {

    public Piece movedPiece;
    public Piece capturedPiece;

    public Move(Piece movedPiece, Piece capturedPiece){
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    // public boolean ValidarRoque(Board board) {
    // 	// Needs to be made
    // }

    /* Return if a move is valid */
    public boolean ValidateMove(Board board){

	for (Pair p : this.movedPiece.GetMoviments()) {
	    if(p.x == this.capturedPiece.GetBoardPosition().x && p.y == this.capturedPiece.GetBoardPosition().y){
		return true;
	    }
        }

	return false;
    }

   public boolean ValidateMovecaoPawn(Board board) {
        boolean promoveu = false;
        //verificação para as brancas
        for(int x = 0; x < 8; x++) {
            Piece p = board.GetPieceInPosition(x,0);
            if (p instanceof Pawn && p.GetPieceColor() == 'w') {
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
            if (p instanceof Pawn && p.GetPieceColor() == 'b') {
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

    public char promotionId(char color, char uppercasePieceId) {
	if (color == 'w') {
	    return uppercasePieceId;
	} else {
	    return Character.toLowerCase(uppercasePieceId);
	}
    }
}
