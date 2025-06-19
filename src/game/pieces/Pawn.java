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

    public boolean hasMoved = false;

    private static Texture pawnTexture = LoadTexture("res/pieces/pawn.png");

    public Pawn(int x, int y, char id){
        super(x, y, id);
	this.LoadSprite();
    }

    private void LoadSprite(){
	if (findPieceColor() == 'w')
	    this.setSprite(new Sprite(pawnTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.setSprite(new Sprite(pawnTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();

	int direction = -1;
	char color = this.findPieceColor();
	if(color == 'w'){
	    direction = -1;
	} else {
	    direction = 1;
	}

	Pair up = this.getBoardPosition().add(new Pair(0, direction * 1));
	Pair doubleUp = this.getBoardPosition().add(new Pair(0, direction * 2));

	// The pawn only attacks on its diagonals
        Pair upperRight = this.getBoardPosition().add(new Pair(+ 1, direction * 1));
        Pair upperLeft = this.getBoardPosition().add(new Pair(- 1, direction * 1));

        if(up.IsPieceInsideBoard(0, SIZE) && !(board.IsTherePieceInPosition(up))){
	    this.checkMovement(board, newMovimentos, up, testingCheck);

	    if(!this.hasMoved && doubleUp.IsPieceInsideBoard(0, SIZE) && !(board.IsTherePieceInPosition(doubleUp))){
		this.checkMovement(board, newMovimentos, doubleUp, testingCheck);
	    }
	}

        if(upperRight.IsPieceInsideBoard(0, SIZE) && (board.IsTherePieceInPosition(upperRight)) && color != board.GetPieceInPosition(upperRight).findPieceColor()){
	    this.checkMovement(board, newMovimentos, upperRight, testingCheck);
	}

        if(upperLeft.IsPieceInsideBoard(0, SIZE) && (board.IsTherePieceInPosition(upperLeft)) && color != board.GetPieceInPosition(upperLeft).findPieceColor()){
	    this.checkMovement(board, newMovimentos, upperLeft, testingCheck);
	}

	if(testingCheck){
	    this.setMovements(newMovimentos);
	}

        return newMovimentos;
    }

    @Override
    public void movePiece(Move move){
        super.movePiece(move);
        this.hasMoved = true;
    }

}
