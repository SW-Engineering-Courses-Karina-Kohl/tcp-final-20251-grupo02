package game;

import game.pieces.*;
import misc.Pair;
import java.util.Scanner;

public class Move {

	private Piece movedPiece;
	private Piece capturedPiece;

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

	// public boolean ValidateCastling(Board board) {
	// // Needs to be made
	// }

	/* Return if a move is valid */
	public boolean validateMove(Board board) {

		for (Pair p : this.getMovedPiece().getMovements()) {
			if (p.x == this.getCapturedPiece().getBoardPosition().x
					&& p.y == this.getCapturedPiece().getBoardPosition().y) {
				return true;
			}
		}

		return false;
	}

	public void checkPawnPromotion(Board board) {

		int promotionTile = 0;
		char pawnColor = this.movedPiece.findPieceColor();
		Pair pawnPosition = this.movedPiece.getBoardPosition();

		if (pawnColor == 'w') {
			promotionTile = 0;
		} else {
			promotionTile = 7;
		}

		if (pawnPosition.y == promotionTile) {

			// [T]orre [C]avalo [B]ispo [D]ama"
			Scanner scanner = new Scanner(System.in);
			char escolha = Character.toUpperCase(scanner.next().charAt(0));

			switch (escolha) {
				case 'T':
					board.setPieceInPosition(pawnPosition, new Rook(pawnPosition, this.promotionId(pawnColor, 'T')));
					break;
				case 'B':
					board.setPieceInPosition(pawnPosition, new Bishop(pawnPosition, this.promotionId(pawnColor, 'B')));
					break;
				case 'C':
					board.setPieceInPosition(pawnPosition, new Knight(pawnPosition, this.promotionId(pawnColor, 'C')));
					break;
				case 'D':
					board.setPieceInPosition(pawnPosition, new Queen(pawnPosition, this.promotionId(pawnColor, 'D')));
					break;
			}
		}
	}

	private char promotionId(char color, char uppercasePieceId) {
		if (color == 'w') {
			return uppercasePieceId;
		} else {
			return Character.toLowerCase(uppercasePieceId);
		}
	}
}
