package game;

import game.pieces.*;
import gui.ButtonRaise;
import misc.Pair;
import java.util.Scanner;

import com.raylib.Raylib.Camera2D;

public class Move {

	private Piece movedPiece;
	private Piece capturedPiece;
	private static ButtonRaise buttonRaise;

	public Move(Piece movedPiece, Piece capturedPiece) {
		this.movedPiece = movedPiece;
		this.capturedPiece = capturedPiece;
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public boolean isEnPassant(Board board) {

		if (!(this.movedPiece instanceof Pawn)) {
			return false;
		}

		int directionY = ((Pawn) this.movedPiece).getMoveDirection();
		int directionX = this.capturedPiece.getBoardPosition().x - this.movedPiece.getBoardPosition().x;

		// to be an en passant, the click must be one column to left or right
		if (Math.abs(directionX) != 1) {
			return false;
		}

		if (this.capturedPiece.getBoardPosition().y != this.movedPiece.getBoardPosition().y + directionY) {
			return false;
		}

		// If there's not a pawn in the left or right (based on where it was clicked)
		Piece verifiedPiece = board.getPieceInPosition(this.movedPiece.getBoardPosition().add(new Pair(directionX, 0)));
		if (!(verifiedPiece instanceof Pawn)) {
			return false;
		}

		if (board.isTherePieceInPosition(this.movedPiece.getBoardPosition().add(new Pair(directionX, directionY)))) {
			return false;
		}

		return true;
	}

	public boolean isCastling() {

		if (this.movedPiece.findPieceColor() == this.capturedPiece.findPieceColor()) {
			if (this.movedPiece instanceof Rook && this.capturedPiece instanceof King) {
				return true;
			}
			if (this.movedPiece instanceof King && this.capturedPiece instanceof Rook) {
				return true;
			}
		}

		return false;

	}

	/* Return if a move is valid */
	public boolean validateMove(Board board) {

		for (Pair p : this.getMovedPiece().getMovements()) {
			if (p.isEqualsTo(this.getCapturedPiece().getBoardPosition())) {
				return true;
			}
		}

		return false;
	}

	public boolean checkPawnPromotion(Board board) {

		int promotionTile = 0;
		char pawnColor = this.movedPiece.findPieceColor();
		Pair pawnPosition = this.movedPiece.getBoardPosition();

		if (pawnColor == 'w') {
			promotionTile = 0;
		} else {
			promotionTile = 7;
		}

		if (pawnPosition.y == promotionTile) {

			return true;
		}
		return false;
	}

	private char promotionId(char color, char uppercasePieceId) {
		if (color == 'w') {
			return uppercasePieceId;
		} else {
			return Character.toLowerCase(uppercasePieceId);
		}
	}

	public boolean promote(Board board, Camera2D camera2d, boolean initUI) {
		char pawnColor = this.movedPiece.findPieceColor();
		Pair pawnPosition = this.movedPiece.getBoardPosition();

		char choice;
		if (initUI) {
			if (buttonRaise == null) {
				buttonRaise = new ButtonRaise(2);
			}
			choice = Character.toUpperCase(buttonRaise.botaoPromocaoLogica(pawnColor, camera2d));
		} else {
			Scanner scanner = new Scanner(System.in);
			choice = scanner.next().charAt(0);
			scanner.close();
		}
		if (choice != '-') {
			switch (choice) {
				case 'R':
					board.setPieceInPosition(pawnPosition,
							new Rook(pawnPosition, this.promotionId(pawnColor, 'R'), initUI));
					break;
				case 'B':
					board.setPieceInPosition(pawnPosition,
							new Bishop(pawnPosition, this.promotionId(pawnColor, 'B'), initUI));
					break;
				case 'H':
					board.setPieceInPosition(pawnPosition,
							new Knight(pawnPosition, this.promotionId(pawnColor, 'H'), initUI));
					break;
				case 'Q':
					board.setPieceInPosition(pawnPosition,
							new Queen(pawnPosition, this.promotionId(pawnColor, 'Q'), initUI));
					break;
			}
			return true;
		}
		return false;
	}
}
