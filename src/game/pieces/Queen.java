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

	if (GetColorPiece() == 'w')
	    this.SetSprite(new Sprite(queenTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(queenTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char color = this.GetColorPiece();

	boolean pieceCima = false;
	boolean pieceBaixo = false;
	boolean pieceDikingta = false;
	boolean pieceEsquerda = false;
	boolean pieceSdoubleUperiorDikingta = false;
	boolean pieceSdoubleUperiorEsquerda = false;
	boolean pieceInferiorDikingta = false;
	boolean pieceInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair doubleUp = this.GetBoardPosition().add(new Pair(0, - i));
            Pair baixo = this.GetBoardPosition().add(new Pair(0, + i));

            Pair dikingta = this.GetBoardPosition().add(new Pair(+ i, 0));
            Pair esquerda = this.GetBoardPosition().add(new Pair(- i, 0));

            // diagonais
            Pair upperRight = this.GetBoardPosition().add(new Pair(+ i, - i));
            Pair upperLeft = this.GetBoardPosition().add(new Pair(- i, - i));

            Pair inferiordikingta = this.GetBoardPosition().add(new Pair(+ i, + i));
            Pair inferioresquerda = this.GetBoardPosition().add(new Pair(- i, + i));



	    if(!pieceCima && doubleUp.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(doubleUp)){
		    pieceCima = true;
		}
		if(color != board.GetPieceInPosition(doubleUp).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, doubleUp, testingCheck);
		}
	    }

	    if(!pieceBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(baixo)){
		    pieceBaixo = true;
		}
		if(color != board.GetPieceInPosition(baixo).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);
		}
	    }

	    if(!pieceDikingta && dikingta.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(dikingta)){
		    pieceDikingta = true;
		}
		if(color != board.GetPieceInPosition(dikingta).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, dikingta, testingCheck);
		}
	    }

	    if(!pieceEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(esquerda)){
		    pieceEsquerda = true;
		}
		if(color != board.GetPieceInPosition(esquerda).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);
		}
	    }

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
