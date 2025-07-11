package game.pieces;

import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Move;

public class King extends Piece {

	private boolean hasMoved = false;
	private static Texture kingTexture;

	public King(int x, int y, char id, boolean initUI) {
		super(x, y, id);
		if(initUI)
			this.loadSprite();
	}

	public King(Pair p, char id, boolean initUI) {
		super(p.x, p.y, id);
		if(initUI)
			this.loadSprite();
	}

	private void loadSprite() {
		if (kingTexture == null) {
			kingTexture = LoadTexture("res/pieces/king.png");
		}

		if (findPieceColor() == 'w')
			this.setSprite(new Sprite(kingTexture, 2, 0, 0, 0, WHITE, 4));
		else
			this.setSprite(new Sprite(kingTexture, 2, 0, 0, 1, WHITE, 4));
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean moved) {
		this.hasMoved = moved;
	}

	@Override
	public ArrayList<Pair> validMovements(Board board, boolean testingCheck) {

		ArrayList<Pair> newMovements = new ArrayList<>();
		char color = this.findPieceColor();

		Pair up = this.getBoardPosition().add(new Pair(0, -1));
		Pair down = this.getBoardPosition().add(new Pair(0, +1));
		Pair right = this.getBoardPosition().add(new Pair(+1, 0));
		Pair left = this.getBoardPosition().add(new Pair(-1, 0));
		Pair upperRight = this.getBoardPosition().add(new Pair(+1, -1));
		Pair upperLeft = this.getBoardPosition().add(new Pair(-1, -1));
		Pair lowerRight = this.getBoardPosition().add(new Pair(+1, +1));
		Pair lowerLeft = this.getBoardPosition().add(new Pair(-1, +1));

		if (up.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(up).findPieceColor())
			this.checkMovement(board, newMovements, up, testingCheck);

		if (down.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(down).findPieceColor())
			this.checkMovement(board, newMovements, down, testingCheck);

		if (right.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(right).findPieceColor())
			this.checkMovement(board, newMovements, right, testingCheck);

		if (left.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(left).findPieceColor())
			this.checkMovement(board, newMovements, left, testingCheck);

		if (upperRight.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(upperRight).findPieceColor())
			this.checkMovement(board, newMovements, upperRight, testingCheck);

		if (upperLeft.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(upperLeft).findPieceColor())
			this.checkMovement(board, newMovements, upperLeft, testingCheck);

		if (lowerRight.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(lowerRight).findPieceColor())
			this.checkMovement(board, newMovements, lowerRight, testingCheck);

		if (lowerLeft.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(lowerLeft).findPieceColor())
			this.checkMovement(board, newMovements, lowerLeft, testingCheck);

		// check castling
		boolean pieceLeft = false;
		boolean pieceRight = false;

		for (int i = 1; i < SIZE; i++) {

			right = this.getBoardPosition().add(new Pair(+i, 0));
			left = this.getBoardPosition().add(new Pair(-i, 0));

			if (!pieceRight && right.isPieceInsideBoard(0, SIZE)) {
			    if (board.isTherePieceInPosition(right)) {

				pieceRight = true;
				Piece piece = board.getPieceInPosition(right);

				if (piece instanceof Rook && piece.findPieceColor() == this.findPieceColor()) {
				    if(!testingCheck){
					newMovements.add(right);
				    } else if (board.checkCastling((Rook) piece, this, 'r')) {
					newMovements.add(right);
				    }
				}
			    }
			}

			if (!pieceLeft && left.isPieceInsideBoard(0, SIZE)) {
			    if (board.isTherePieceInPosition(left)) {

				pieceLeft = true;
				Piece piece = board.getPieceInPosition(left);

				if (piece instanceof Rook && piece.findPieceColor() == this.findPieceColor()) {
				    if(!testingCheck){
					newMovements.add(left);
				    } else if (board.checkCastling((Rook) piece, this, 'l')) {
					newMovements.add(left);
				    }
				}
			    }
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
