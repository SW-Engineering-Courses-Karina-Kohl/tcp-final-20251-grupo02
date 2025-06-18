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

	boolean pieceSdoubleUperiorDikingta = false;
	boolean pieceSdoubleUperiorEsquerda = false;
	boolean pieceInferiorDikingta = false;
	boolean pieceInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            // diagonais
            Pair upperRight = this.GetBoardPosition().add(new Pair(+ i, - i));
            Pair upperLeft = this.GetBoardPosition().add(new Pair(- i, - i));

            Pair inferiordikingta = this.GetBoardPosition().add(new Pair(+ i, + i));
            Pair inferioresquerda = this.GetBoardPosition().add(new Pair(- i, + i));

            if(!pieceSdoubleUperiorDikingta && upperRight.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(upperRight)){
		    pieceSdoubleUperiorDikingta = true;
		}
		if(color != board.GetPieceInPosition(upperRight).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, upperRight, testingCheck);
		}
	    }

            if(!pieceSdoubleUperiorEsquerda && upperLeft.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(upperLeft)){
		    pieceSdoubleUperiorEsquerda = true;
		}
		if(color != board.GetPieceInPosition(upperLeft).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, upperLeft, testingCheck);
		}
	    }

            if(!pieceInferiorDikingta && inferiordikingta.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(inferiordikingta)){
		    pieceInferiorDikingta = true;
		}
		if(color != board.GetPieceInPosition(inferiordikingta).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, inferiordikingta, testingCheck);
		}
	    }

            if(!pieceInferiorEsquerda && inferioresquerda.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(inferioresquerda)){
		    pieceInferiorEsquerda = true;
		}
		if(color != board.GetPieceInPosition(inferioresquerda).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, inferioresquerda, testingCheck);
		}
	    }

        }


	if(testingCheck){
	    this.SetMoviments(newMovimentos);
	}

        return newMovimentos;
    }

}
