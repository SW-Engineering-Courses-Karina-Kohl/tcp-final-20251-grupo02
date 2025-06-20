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

    public boolean hasEnPassant(){
	return this.hasEnPassant;
    }

    public Pair getEnPassantPosition(){
	return this.enPassantPosition;
    }

    public void setHasMoved(boolean moved){
	this.hasMoved = moved;
    }

    public int getMoveDirection(){

	if (this.findPieceColor() == 'w') {
	    return -1;
	} else {
	    return 1;
	}

    }

    @Override
    public ArrayList<Pair> validMoviments(Board board, boolean testingCheck) {

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	this.hasEnPassant = false;

	int direction = this.getMoveDirection();
	char color = this.findPieceColor();

	Pair up = this.getBoardPosition().add(new Pair(0, direction * 1));
	Pair doubleUp = this.getBoardPosition().add(new Pair(0, direction * 2));

	// The pawn only attacks on its diagonals
	Pair upperRight = this.getBoardPosition().add(new Pair(+1, direction * 1));
	Pair upperLeft = this.getBoardPosition().add(new Pair(-1, direction * 1));

	if (up.isPieceInsideBoard(0, SIZE)){
	    if(!(board.isTherePieceInPosition(up))) {
		this.checkMoviment(board, newMovimentos, up, testingCheck);

		if (!this.hasMoved && doubleUp.isPieceInsideBoard(0, SIZE)){
		    if(!(board.isTherePieceInPosition(doubleUp))) {
			this.checkMoviment(board, newMovimentos, doubleUp, testingCheck);
		    }
		}
	    }
	}

	if (upperRight.isPieceInsideBoard(0, SIZE)){
	    if(board.isTherePieceInPosition(upperRight) && color != board.getPieceInPosition(upperRight).findPieceColor()) {
		this.checkMoviment(board, newMovimentos, upperRight, testingCheck);
	    }
	    if(board.checkEnPassant(this, 'r')){
		this.checkMoviment(board, newMovimentos, upperRight, testingCheck);
		this.hasEnPassant = true;
		this.enPassantPosition = upperRight;
	    }
	}

	if (upperLeft.isPieceInsideBoard(0, SIZE)){
	    if(board.isTherePieceInPosition(upperLeft) && color != board.getPieceInPosition(upperLeft).findPieceColor()) {
		this.checkMoviment(board, newMovimentos, upperLeft, testingCheck);
	    }
	    if(board.checkEnPassant(this, 'l')){
		this.checkMoviment(board, newMovimentos, upperLeft, testingCheck);
		this.hasEnPassant = true;
		this.enPassantPosition = upperLeft;
	    }
	}

	if (testingCheck) {
	    this.setMoviments(newMovimentos);
	}

	return newMovimentos;
    }

    @Override
    public void movePiece(Move move) {
	super.movePiece(move);
	this.hasMoved = true;
    }
}
