package game.pieces;

import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Queen extends Piece {

	private static Texture queenTexture;

	public Queen(int x, int y, char id, boolean initUI) {
		super(x, y, id);
		if(initUI)
			this.loadSprite();
	}

	public Queen(Pair p, char id, boolean initUI) {
		super(p.x, p.y, id);
		if(initUI)
			this.loadSprite();
	}

	private void loadSprite() {
		if (queenTexture == null) {
			queenTexture = LoadTexture("res/pieces/queen.png");
		}

		if (findPieceColor() == 'w')
			this.setSprite(new Sprite(queenTexture, 2, 0, 0, 0, WHITE, 2));
		else
			this.setSprite(new Sprite(queenTexture, 2, 0, 0, 1, WHITE, 2));
	}

	@Override
	public ArrayList<Pair> validMoviments(Board board, boolean testingCheck) {

		ArrayList<Pair> newMovimentos = new ArrayList<>();
		char color = this.findPieceColor();

		boolean pieceUp = false;
		boolean pieceDown = false;
		boolean pieceRight = false;
		boolean pieceLeft = false;
		boolean pieceUpperRight = false;
		boolean pieceUpperLeft = false;
		boolean pieceLowerRight = false;
		boolean pieceLowerLeft = false;

		for (int i = 1; i < SIZE; i++) {

			Pair doubleUp = this.getBoardPosition().add(new Pair(0, -i));
			Pair down = this.getBoardPosition().add(new Pair(0, +i));
			Pair right = this.getBoardPosition().add(new Pair(+i, 0));
			Pair left = this.getBoardPosition().add(new Pair(-i, 0));
			Pair upperRight = this.getBoardPosition().add(new Pair(+i, -i));
			Pair upperLeft = this.getBoardPosition().add(new Pair(-i, -i));
			Pair lowerRight = this.getBoardPosition().add(new Pair(+i, +i));
			Pair lowerLeft = this.getBoardPosition().add(new Pair(-i, +i));

			if (!pieceUp && doubleUp.isPieceInsideBoard(0, SIZE)) {
				if (board.isTherePieceInPosition(doubleUp)) {
					pieceUp = true;
				}
				if (color != board.getPieceInPosition(doubleUp).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, doubleUp, testingCheck);
				}
			}

			if (!pieceDown && down.isPieceInsideBoard(0, SIZE)) {
				if (board.isTherePieceInPosition(down)) {
					pieceDown = true;
				}
				if (color != board.getPieceInPosition(down).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, down, testingCheck);
				}
			}

			if (!pieceRight && right.isPieceInsideBoard(0, SIZE)) {
				if (board.isTherePieceInPosition(right)) {
					pieceRight = true;
				}
				if (color != board.getPieceInPosition(right).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, right, testingCheck);
				}
			}

			if (!pieceLeft && left.isPieceInsideBoard(0, SIZE)) {
				if (board.isTherePieceInPosition(left)) {
					pieceLeft = true;
				}
				if (color != board.getPieceInPosition(left).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, left, testingCheck);
				}
			}

			if (!pieceUpperRight && upperRight.isPieceInsideBoard(0, SIZE)) {

				if (board.isTherePieceInPosition(upperRight)) {
					pieceUpperRight = true;
				}
				if (color != board.getPieceInPosition(upperRight).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, upperRight, testingCheck);
				}
			}

			if (!pieceUpperLeft && upperLeft.isPieceInsideBoard(0, SIZE)) {

				if (board.isTherePieceInPosition(upperLeft)) {
					pieceUpperLeft = true;
				}
				if (color != board.getPieceInPosition(upperLeft).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, upperLeft, testingCheck);
				}
			}

			if (!pieceLowerRight && lowerRight.isPieceInsideBoard(0, SIZE)) {

				if (board.isTherePieceInPosition(lowerRight)) {
					pieceLowerRight = true;
				}
				if (color != board.getPieceInPosition(lowerRight).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, lowerRight, testingCheck);
				}
			}

			if (!pieceLowerLeft && lowerLeft.isPieceInsideBoard(0, SIZE)) {

				if (board.isTherePieceInPosition(lowerLeft)) {
					pieceLowerLeft = true;
				}
				if (color != board.getPieceInPosition(lowerLeft).findPieceColor()) {
					this.checkMoviment(board, newMovimentos, lowerLeft, testingCheck);
				}
			}
		}

		if (testingCheck) {
			this.setMovements(newMovimentos);
		}

		return newMovimentos;
	}

}
