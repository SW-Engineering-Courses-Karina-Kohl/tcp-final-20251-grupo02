package game;
import game.pieces.*;
import gui.ButtonRaise;
import misc.Pair;
import java.util.Scanner;


public class Move {

    private Piece movedPiece;
    private Piece capturedPiece;

	private static ButtonRaise buttonRaise = new ButtonRaise(2);

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

    public boolean CheckPawnPromotion(Board board){

		int promotionTile = 0;
		char pawnColor = this.movedPiece.GetPieceColor();
		Pair pawnPosition = this.movedPiece.GetBoardPosition();

		if(pawnColor == 'w'){
			promotionTile = 0;
		} else {
			promotionTile = 7;
		}

		if(pawnPosition.y == promotionTile){

			//[T]orre  [C]avalo  [B]ispo  [D]ama"
			return true;
		}

		return false;
    }

    private char PromotionId(char color, char uppercasePieceId) {
	if (color == 'w') {
	    return uppercasePieceId;
	} else {
	    return Character.toLowerCase(uppercasePieceId);
	}
    }

	public boolean DoPromotion(Board board)
	{
		char pawnColor = this.movedPiece.GetPieceColor();
		Pair pawnPosition = this.movedPiece.GetBoardPosition();
		char escolha = Character.toUpperCase(buttonRaise.BotaoPromocaoLogica(pawnColor));
		if (escolha != '-')
		{
			switch (escolha) {
			case 'T':
			board.SetPieceInPosition(pawnPosition, new Rook(pawnPosition, this.PromotionId(pawnColor, 'T')));
			break;
			case 'B':
			board.SetPieceInPosition(pawnPosition, new Bishop(pawnPosition, this.PromotionId(pawnColor, 'B')));
			break;
			case 'C':
			board.SetPieceInPosition(pawnPosition, new Knight(pawnPosition, this.PromotionId(pawnColor, 'C')));
			break;
			case 'D':
			board.SetPieceInPosition(pawnPosition, new Queen(pawnPosition, this.PromotionId(pawnColor, 'D')));
			break;
			}
			return true;
		}
		return false;
	}
}
