package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Bishop extends Piece {

	private static Texture bispoTexture = LoadTexture("res/pieces/bispo.png");
    public Bishop(int x, int y, char id){
        super(x, y, id);

        if (GetColorPiece() == 'b')
            sprite = new Sprite(bispoTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(bispoTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();

	char cor = this.GetColorPiece();

	boolean pieceSuperiorDireita = false;
	boolean pieceSuperiorEsquerda = false;
	boolean pieceInferiorDireita = false;
	boolean pieceInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            // diagonais
            Pair superior_direita = this.boardPosition.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.boardPosition.add(new Pair(- i, - i));

            Pair inferior_direita = this.boardPosition.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.boardPosition.add(new Pair(- i, + i));

            if(!pieceSuperiorDireita && superior_direita.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(superior_direita)){
		    pieceSuperiorDireita = true;
		}
		if(cor != board.GetPieceInPosition(superior_direita).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, superior_direita, testingCheck);
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

            if(!pieceInferiorDireita && inferior_direita.IsPieceInsideBoard(0, SIZE)){

                if(board.IsTherePieceInPosition(inferior_direita)){
		    pieceInferiorDireita = true;
		}
		if(cor != board.GetPieceInPosition(inferior_direita).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, inferior_direita, testingCheck);
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
	    movimentos = newMovimentos;
	}

        return newMovimentos;
    }

}
