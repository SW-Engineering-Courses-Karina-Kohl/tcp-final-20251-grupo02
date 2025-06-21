package game.pieces;

import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Bishop extends Piece {

	private static Texture bishopTexture;

	public Bishop(int x, int y, char id, boolean useUI) {
		super(x, y, id);
	}

	public Bishop(int x, int y, char id) {
		super(x, y, id);
		this.LoadSprite();
	}

	public Bishop(Pair p, char id) {
		super(p.x, p.y, id);
		this.LoadSprite();
	}

	private void LoadSprite() {
		if(bishopTexture == null){
			bishopTexture = LoadTexture("res/pieces/bishop.png");
		}

		if (findPieceColor() == 'w')
			this.setSprite(new Sprite(bishopTexture, 2, 0, 0, 0, WHITE, 2));
		else
			this.setSprite(new Sprite(bishopTexture, 2, 0, 0, 1, WHITE, 2));
	}

	@Override
	public ArrayList<Pair> validMoviments(Board board, boolean testingCheck) {

		ArrayList<Pair> newMovimentos = new ArrayList<>();

		char color = this.findPieceColor();

		boolean pieceUpperRight = false;
		boolean pieceUpperLeft = false;
		boolean pieceLowerRight = false;
		boolean pieceLowerLeft = false;

		for (int i = 1; i < SIZE; i++) {

			Pair upperRight = this.getBoardPosition().add(new Pair(+i, -i));
			Pair upperLeft = this.getBoardPosition().add(new Pair(-i, -i));
			Pair lowerRight = this.getBoardPosition().add(new Pair(+i, +i));
			Pair lowerLeft = this.getBoardPosition().add(new Pair(-i, +i));

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
