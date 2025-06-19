package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Queen extends Piece {

    private static Texture queenTexture = LoadTexture("res/pieces/queen.png");

    public Queen(int x, int y, char id){
        super(x, y, id);
	this.LoadSprite();
    }

    public Queen(Pair p, char id){
        super(p.x, p.y, id);
	this.LoadSprite();
    }

    private void LoadSprite(){
	if (findPieceColor() == 'w')
	    this.setSprite(new Sprite(queenTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.setSprite(new Sprite(queenTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck){

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

        for(int i = 1; i < SIZE; i++) {

            Pair doubleUp = this.getBoardPosition().add(new Pair(0, - i));
            Pair down = this.getBoardPosition().add(new Pair(0, + i));
            Pair right = this.getBoardPosition().add(new Pair(+ i, 0));
            Pair left = this.getBoardPosition().add(new Pair(- i, 0));
            Pair upperRight = this.getBoardPosition().add(new Pair(+ i, - i));
            Pair upperLeft = this.getBoardPosition().add(new Pair(- i, - i));
            Pair lowerRight = this.getBoardPosition().add(new Pair(+ i, + i));
            Pair lowerLeft = this.getBoardPosition().add(new Pair(- i, + i));

	    if(!pieceUp && doubleUp.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(doubleUp)){
		    pieceUp = true;
		}
		if(color != board.GetPieceInPosition(doubleUp).findPieceColor()){
		    this.checkMovement(board, newMovimentos, doubleUp, testingCheck);
		}
	    }

	    if(!pieceDown && down.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(down)){
		    pieceDown = true;
		}
		if(color != board.GetPieceInPosition(down).findPieceColor()){
		    this.checkMovement(board, newMovimentos, down, testingCheck);
		}
	    }

	    if(!pieceRight && right.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(right)){
		    pieceRight = true;
		}
		if(color != board.GetPieceInPosition(right).findPieceColor()){
		    this.checkMovement(board, newMovimentos, right, testingCheck);
		}
	    }

	    if(!pieceLeft && left.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(left)){
		    pieceLeft = true;
		}
		if(color != board.GetPieceInPosition(left).findPieceColor()){
		    this.checkMovement(board, newMovimentos, left, testingCheck);
		}
	    }

            if(!pieceUpperRight && upperRight.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(upperRight)){
		    pieceUpperRight = true;
		}
		if(color != board.GetPieceInPosition(upperRight).findPieceColor()){
		    this.checkMovement(board, newMovimentos, upperRight, testingCheck);
		}
	    }

            if(!pieceUpperLeft && upperLeft.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(upperLeft)){
		    pieceUpperLeft = true;
		}
		if(color != board.GetPieceInPosition(upperLeft).findPieceColor()){
		    this.checkMovement(board, newMovimentos, upperLeft, testingCheck);
		}
	    }

            if(!pieceLowerRight && lowerRight.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(lowerRight)){
		    pieceLowerRight = true;
		}
		if(color != board.GetPieceInPosition(lowerRight).findPieceColor()){
		    this.checkMovement(board, newMovimentos, lowerRight, testingCheck);
		}
	    }

            if(!pieceLowerLeft && lowerLeft.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(lowerLeft)){
		    pieceLowerLeft = true;
		}
		if(color != board.GetPieceInPosition(lowerLeft).findPieceColor()){
		    this.checkMovement(board, newMovimentos, lowerLeft, testingCheck);
		}
	    }
        }

	if(testingCheck){
	    this.setMovements(newMovimentos);
	}

        return newMovimentos;
    }

}
