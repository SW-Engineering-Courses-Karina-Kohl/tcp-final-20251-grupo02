package game;

import game.pieces.*;
import gui.ButtonRaise;
import misc.Pair;
import java.util.Scanner;

import com.raylib.Raylib.Camera2D;

public class Move {

	private Piece movedPiece;
	private Piece capturedPiece;
	private static ButtonRaise buttonRaise = new ButtonRaise(2);

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

	public boolean DoPromotion(Board board, Camera2D camera2d)
	{
		char pawnColor = this.movedPiece.findPieceColor();
		Pair pawnPosition = this.movedPiece.getBoardPosition();
		char escolha = Character.toUpperCase(buttonRaise.botaoPromocaoLogica(pawnColor, camera2d));
		if (escolha != '-')
		{
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
			return true;
		}
		return false;
	}
}
