package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Queen extends Piece {

	private static Texture rainhaTexture = LoadTexture("res/pieces/rainha.png");
    public Queen(int x, int y, char id){
        super(x, y, id);

        if (GetColorPiece() == 'w')
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetColorPiece();

	boolean pieceCima = false;
	boolean pieceBaixo = false;
	boolean pieceDireita = false;
	boolean pieceEsquerda = false;
	boolean pieceSuperiorDireita = false;
	boolean pieceSuperiorEsquerda = false;
	boolean pieceInferiorDireita = false;
	boolean pieceInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.boardPosition.add(new Pair(0, - i));
            Pair baixo = this.boardPosition.add(new Pair(0, + i));

            Pair direita = this.boardPosition.add(new Pair(+ i, 0));
            Pair esquerda = this.boardPosition.add(new Pair(- i, 0));

            // diagonais
            Pair superior_direita = this.boardPosition.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.boardPosition.add(new Pair(- i, - i));

            Pair inferior_direita = this.boardPosition.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.boardPosition.add(new Pair(- i, + i));



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

	    if(!pieceDireita && direita.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(direita)){
		    pieceDireita = true;
		}
		if(cor != board.GetPieceInPosition(direita).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, direita, testingCheck);
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
	    moviments = newMovimentos;
	}

        return newMovimentos;
    }

}
