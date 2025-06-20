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

    private static Texture pawnTexture = LoadTexture("res/pieces/pawn.png");

    public Pawn(int x, int y, char id) {
	super(x, y, id);
	this.loadSprite();
    }

    private void loadSprite() {
	if (findPieceColor() == 'w')
	    this.setSprite(new Sprite(pawnTexture, 2, 0, 0, 0, WHITE, 2));
	else
	    this.setSprite(new Sprite(pawnTexture, 2, 0, 0, 1, WHITE, 2));
    }

    public boolean hasMoved(){
	return hasMoved;
    }

    public void setHasMoved(boolean moved){
	this.hasMoved = moved;
    }


    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck) {

	ArrayList<Pair> newMovimentos = new ArrayList<>();

	int direction = -1;
	char color = this.findPieceColor();
	if (color == 'w') {
	    direction = -1;
	} else {
	    direction = 1;
	}

	Pair up = this.getBoardPosition().add(new Pair(0, direction * 1));
	Pair doubleUp = this.getBoardPosition().add(new Pair(0, direction * 2));

	// The pawn only attacks on its diagonals
	Pair upperRight = this.getBoardPosition().add(new Pair(+1, direction * 1));
	Pair upperLeft = this.getBoardPosition().add(new Pair(-1, direction * 1));

	if (up.isPieceInsideBoard(0, SIZE) && !(board.isTherePieceInPosition(up))) {
	    this.checkMovement(board, newMovimentos, up, testingCheck);

	    if (!this.hasMoved && doubleUp.isPieceInsideBoard(0, SIZE) && !(board.isTherePieceInPosition(doubleUp))) {
		this.checkMovement(board, newMovimentos, doubleUp, testingCheck);
	    }
	}

	if (upperRight.isPieceInsideBoard(0, SIZE) && (board.isTherePieceInPosition(upperRight))
	    && color != board.getPieceInPosition(upperRight).findPieceColor()) {
	    this.checkMovement(board, newMovimentos, upperRight, testingCheck);
	}

	if (upperLeft.isPieceInsideBoard(0, SIZE) && (board.isTherePieceInPosition(upperLeft))
	    && color != board.getPieceInPosition(upperLeft).findPieceColor()) {
	    this.checkMovement(board, newMovimentos, upperLeft, testingCheck);
	}

	if (testingCheck) {
	    this.setMovements(newMovimentos);
	}

	return newMovimentos;
    }

    @Override
    public void movePiece(Move move) {
	super.movePiece(move);
	this.hasMoved = true;
    }

}
