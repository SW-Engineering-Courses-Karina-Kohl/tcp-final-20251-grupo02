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

    public boolean jaMovido = false;
    private static Texture rookTexture = LoadTexture("res/pieces/rook.png");

    public Rook(int x, int y, char id){
        super(x, y, id);

	if (GetColorPiece() == 'w')
	    this.SetSprite(new Sprite(rookTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(rookTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetColorPiece();

	boolean pieceCima = false;
	boolean pieceBaixo = false;
	boolean pieceDikingta = false;
	boolean pieceEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.GetBoardPosition().add(new Pair(0, - i));
            Pair baixo = this.GetBoardPosition().add(new Pair(0, + i));

            Pair dikingta = this.GetBoardPosition().add(new Pair(+ i, 0));
            Pair esquerda = this.GetBoardPosition().add(new Pair(- i, 0));

	    if(!pieceCima && cima.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(cima)){
		    pieceCima = true;
		}
		if(cor != board.GetPieceInPosition(cima).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, cima, testingCheck);
		}
	    }

	    if(!pieceBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(baixo)){
		    pieceBaixo = true;
		}
		if(cor != board.GetPieceInPosition(baixo).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);
		}
	    }

	    if(!pieceDikingta && dikingta.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(dikingta)){
		    pieceDikingta = true;
		}
		if(cor != board.GetPieceInPosition(dikingta).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, dikingta, testingCheck);
		}
	    }

	    if(!pieceEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(esquerda)){
		    pieceEsquerda = true;
		}
		if(cor != board.GetPieceInPosition(esquerda).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);
		}
	    }

        }

	if(testingCheck){
	    this.SetMoviments(newMovimentos);
	}

        return newMovimentos;
    }

    @Override
    public void MovePiece(Move move){
        super.MovePiece(move);
        this.jaMovido = true;
    }

}
