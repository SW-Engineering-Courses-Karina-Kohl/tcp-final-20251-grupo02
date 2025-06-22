package game.pieces;

import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Move;

public class Pawn extends Piece {

	private boolean hasMoved = false;
	private boolean hasEnPassant = false;
	private Pair enPassantPosition;

	private static Texture pawnTexture;

	public Pawn(int x, int y, char id, boolean initUI) {
		super(x, y, id);
		if(initUI)
			this.loadSprite();
	}

	private void loadSprite() {
		if (pawnTexture == null) {
			pawnTexture = LoadTexture("res/pieces/pawn.png");
		}

		if (findPieceColor() == 'w')
			this.setSprite(new Sprite(pawnTexture, 2, 0, 0, 0, WHITE, 2));
		else
			this.setSprite(new Sprite(pawnTexture, 2, 0, 0, 1, WHITE, 2));
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	public boolean hasEnPassant() {
		return this.hasEnPassant;
	}

	public Pair getEnPassantPosition() {
		return this.enPassantPosition;
	}

	public void setHasMoved(boolean moved) {
		this.hasMoved = moved;
	}

	public int getMoveDirection() {

		if (this.findPieceColor() == 'w') {
			return -1;
		} else {
			return 1;
		}

	}

	@Override
	public ArrayList<Pair> validMovements(Board board, boolean testingCheck) {

		ArrayList<Pair> newMovements = new ArrayList<>();
		this.hasEnPassant = false;

		int direction = this.getMoveDirection();
		char color = this.findPieceColor();

		Pair up = this.getBoardPosition().add(new Pair(0, direction * 1));
		Pair doubleUp = this.getBoardPosition().add(new Pair(0, direction * 2));

		// The pawn only attacks on its diagonals
		Pair upperRight = this.getBoardPosition().add(new Pair(+1, direction * 1));
		Pair upperLeft = this.getBoardPosition().add(new Pair(-1, direction * 1));

		if (up.isPieceInsideBoard(0, SIZE)) {
			if (!(board.isTherePieceInPosition(up))) {
				this.checkMovement(board, newMovements, up, testingCheck);

				if (!this.hasMoved && doubleUp.isPieceInsideBoard(0, SIZE)) {
					if (!(board.isTherePieceInPosition(doubleUp))) {
						this.checkMovement(board, newMovements, doubleUp, testingCheck);
					}
				}
			}
		}

		if (upperRight.isPieceInsideBoard(0, SIZE)) {
			if (board.isTherePieceInPosition(upperRight)
					&& color != board.getPieceInPosition(upperRight).findPieceColor()) {
				this.checkMovement(board, newMovements, upperRight, testingCheck);
			}
			if (board.checkEnPassant(this, 'r')) {
				this.checkMovement(board, newMovements, upperRight, testingCheck);
				this.hasEnPassant = true;
				this.enPassantPosition = upperRight;
			}
		}

		if (upperLeft.isPieceInsideBoard(0, SIZE)) {
			if (board.isTherePieceInPosition(upperLeft)
					&& color != board.getPieceInPosition(upperLeft).findPieceColor()) {
				this.checkMovement(board, newMovements, upperLeft, testingCheck);
			}
			if (board.checkEnPassant(this, 'l')) {
				this.checkMovement(board, newMovements, upperLeft, testingCheck);
				this.hasEnPassant = true;
				this.enPassantPosition = upperLeft;
			}
		}

		if (testingCheck) {
			this.setMovements(newMovements);
		}

		return newMovements;
	}

	@Override
	public void movePiece(Move move) {
		super.movePiece(move);
		this.hasMoved = true;
	}
}
