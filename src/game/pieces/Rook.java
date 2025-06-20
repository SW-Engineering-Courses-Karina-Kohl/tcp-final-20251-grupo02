package game.pieces;

import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Move;

public class Rook extends Piece {

    private boolean hasMoved = false;
    private static Texture rookTexture = LoadTexture("res/pieces/rook.png");

    public Rook(int x, int y, char id) {
	super(x, y, id);
	this.loadSprite();
    }

    public Rook(Pair p, char id) {
	super(p.x, p.y, id);
	this.loadSprite();
    }

    private void loadSprite() {
	if (findPieceColor() == 'w')
	    this.setSprite(new Sprite(rookTexture, 2, 0, 0, 0, WHITE, 2));
	else
	    this.setSprite(new Sprite(rookTexture, 2, 0, 0, 1, WHITE, 2));
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
	char color = this.findPieceColor();

	boolean pieceUp = false;
	boolean pieceDown = false;
	boolean pieceRight = false;
	boolean pieceLeft = false;

	for (int i = 1; i < SIZE; i++) {

	    Pair up = this.getBoardPosition().add(new Pair(0, -i));
	    Pair down = this.getBoardPosition().add(new Pair(0, +i));

	    Pair right = this.getBoardPosition().add(new Pair(+i, 0));
	    Pair left = this.getBoardPosition().add(new Pair(-i, 0));

	    if (!pieceUp && up.isPieceInsideBoard(0, SIZE)) {
		if (board.isTherePieceInPosition(up)) {
		    pieceUp = true;
		}
		if (color != board.getPieceInPosition(up).findPieceColor()) {
		    this.checkMovement(board, newMovimentos, up, testingCheck);
		}
	    }

	    if (!pieceDown && down.isPieceInsideBoard(0, SIZE)) {
		if (board.isTherePieceInPosition(down)) {
		    pieceDown = true;
		}
		if (color != board.getPieceInPosition(down).findPieceColor()) {
		    this.checkMovement(board, newMovimentos, down, testingCheck);
		}
	    }

	    if (!pieceRight && right.isPieceInsideBoard(0, SIZE)) {
		if (board.isTherePieceInPosition(right)) {
		    pieceRight = true;

		    Piece piece = board.getPieceInPosition(right);

		    if(piece instanceof King && piece.findPieceColor() == this.findPieceColor()){
			if(board.checkCastling(this, (King) piece, 'l')){
			    newMovimentos.add(right);
			}
		    }
		}
		if (color != board.getPieceInPosition(right).findPieceColor()) {
		    this.checkMovement(board, newMovimentos, right, testingCheck);
		}
	    }

	    if (!pieceLeft && left.isPieceInsideBoard(0, SIZE)) {
		if (board.isTherePieceInPosition(left)) {
		    pieceLeft = true;
		    Piece piece = board.getPieceInPosition(left);

		    if(piece instanceof King && piece.findPieceColor() == this.findPieceColor()){
			if(board.checkCastling(this, (King) piece, 'r')){
			    newMovimentos.add(left);
			}
		    }

		}
		if (color != board.getPieceInPosition(left).findPieceColor()) {
		    this.checkMovement(board, newMovimentos, left, testingCheck);
		}
	    }
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
