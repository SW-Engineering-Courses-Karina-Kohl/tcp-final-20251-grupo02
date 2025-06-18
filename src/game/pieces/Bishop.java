package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Bishop extends Piece {

    private static Texture bishopTexture = LoadTexture("res/pieces/bishop.png");

    public Bishop(int x, int y, char id){
        super(x, y, id);

	if (GetColorPiece() == 'w')
	    this.SetSprite(new Sprite(bishopTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(bishopTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();

	char color = this.GetColorPiece();

	boolean pieceUpperRight = false;
	boolean pieceUpperLeft = false;
	boolean pieceLowerRight = false;
	boolean pieceLowerLeft = false;

        for(int i = 1; i < SIZE; i++) {

            Pair upperRight = this.GetBoardPosition().add(new Pair(+ i, - i));
            Pair upperLeft = this.GetBoardPosition().add(new Pair(- i, - i));
            Pair lowerRight = this.GetBoardPosition().add(new Pair(+ i, + i));
            Pair lowerLeft = this.GetBoardPosition().add(new Pair(- i, + i));

            if(!pieceUpperRight && upperRight.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(upperRight)){
		    pieceUpperRight = true;
		}
		if(color != board.GetPieceInPosition(upperRight).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, upperRight, testingCheck);
		}
	    }

            if(!pieceUpperLeft && upperLeft.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(upperLeft)){
		    pieceUpperLeft = true;
		}
		if(color != board.GetPieceInPosition(upperLeft).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, upperLeft, testingCheck);
		}
	    }

            if(!pieceLowerRight && lowerRight.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(lowerRight)){
		    pieceLowerRight = true;
		}
		if(color != board.GetPieceInPosition(lowerRight).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, lowerRight, testingCheck);
		}
	    }

            if(!pieceLowerLeft && lowerLeft.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(lowerLeft)){
		    pieceLowerLeft = true;
		}
		if(color != board.GetPieceInPosition(lowerLeft).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, lowerLeft, testingCheck);
		}
	    }
        }

	if(testingCheck){
	    this.SetMoviments(newMovimentos);
	}

        return newMovimentos;
    }

}
