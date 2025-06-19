package game;
import game.pieces.*;
import misc.Pair;

public class Move {

    private Piece movedPiece;
    private Piece capturedPiece;

    public Move(Piece movedPiece, Piece capturedPiece){
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    public Piece GetMovedPiece(){
	return movedPiece;
    }

    public Piece GetCapturedPiece(){
	return capturedPiece;
    }

    // public boolean ValidateCastling(Board board) {
    // 	// Needs to be made
    // }

    /* Return if a move is valid */
    public boolean ValidateMove(Board board){

	for (Pair p : this.GetMovedPiece().GetMoviments()) {
	    if(p.x == this.GetCapturedPiece().GetBoardPosition().x && p.y == this.GetCapturedPiece().GetBoardPosition().y){
		return true;
	    }
        }

	return false;
    }

    public char PromotionId(char color, char uppercasePieceId) {
	if (color == 'w') {
	    return uppercasePieceId;
	} else {
	    return Character.toLowerCase(uppercasePieceId);
	}
    }
}
