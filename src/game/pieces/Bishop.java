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

	char cor = this.GetColorPiece();

	boolean pieceSuperiorDikingta = false;
	boolean pieceSuperiorEsquerda = false;
	boolean pieceInferiorDikingta = false;
	boolean pieceInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            // diagonais
            Pair superior_dikingta = this.GetBoardPosition().add(new Pair(+ i, - i));
            Pair superior_esquerda = this.GetBoardPosition().add(new Pair(- i, - i));

            Pair inferior_dikingta = this.GetBoardPosition().add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.GetBoardPosition().add(new Pair(- i, + i));

            if(!pieceSuperiorDikingta && superior_dikingta.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(superior_dikingta)){
		    pieceSuperiorDikingta = true;
		}
		if(cor != board.GetPieceInPosition(superior_dikingta).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, superior_dikingta, testingCheck);
		}
	    }

            if(!pieceSuperiorEsquerda && superior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(superior_esquerda)){
		    pieceSuperiorEsquerda = true;
		}
		if(cor != board.GetPieceInPosition(superior_esquerda).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, superior_esquerda, testingCheck);
		}
	    }

            if(!pieceInferiorDikingta && inferior_dikingta.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(inferior_dikingta)){
		    pieceInferiorDikingta = true;
		}
		if(cor != board.GetPieceInPosition(inferior_dikingta).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, inferior_dikingta, testingCheck);
		}
	    }

            if(!pieceInferiorEsquerda && inferior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(inferior_esquerda)){
		    pieceInferiorEsquerda = true;
		}
		if(cor != board.GetPieceInPosition(inferior_esquerda).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, inferior_esquerda, testingCheck);
		}
	    }

        }


	if(testingCheck){
	    this.SetMoviments(newMovimentos);
	}

        return newMovimentos;
    }

}
