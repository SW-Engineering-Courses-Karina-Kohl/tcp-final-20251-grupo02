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

        if (GetOurColorPiece() == 'b')
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetOurColorPiece();

	boolean pieceCima = false;
	boolean pieceBaixo = false;
	boolean pieceDireita = false;
	boolean pieceEsquerda = false;
	boolean pieceSuperiorDireita = false;
	boolean pieceSuperiorEsquerda = false;
	boolean pieceInferiorDireita = false;
	boolean pieceInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.posicaoBoard.add(new Pair(0, - i));
            Pair baixo = this.posicaoBoard.add(new Pair(0, + i));

            Pair direita = this.posicaoBoard.add(new Pair(+ i, 0));
            Pair esquerda = this.posicaoBoard.add(new Pair(- i, 0));

            // diagonais
            Pair superior_direita = this.posicaoBoard.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.posicaoBoard.add(new Pair(- i, - i));

            Pair inferior_direita = this.posicaoBoard.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.posicaoBoard.add(new Pair(- i, + i));



	    if(!pieceCima && cima.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(cima)){
		    pieceCima = true;
		}
		if(cor != board.GetPieceNaPosicao(cima).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, cima, testingCheck);
		}
	    }

	    if(!pieceBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(baixo)){
		    pieceBaixo = true;
		}
		if(cor != board.GetPieceNaPosicao(baixo).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);
		}
	    }

	    if(!pieceDireita && direita.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(direita)){
		    pieceDireita = true;
		}
		if(cor != board.GetPieceNaPosicao(direita).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, direita, testingCheck);
		}
	    }

	    if(!pieceEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(esquerda)){
		    pieceEsquerda = true;
		}
		if(cor != board.GetPieceNaPosicao(esquerda).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);
		}
	    }

            if(!pieceSuperiorDireita && superior_direita.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(superior_direita)){
		    pieceSuperiorDireita = true;
		}
		if(cor != board.GetPieceNaPosicao(superior_direita).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, superior_direita, testingCheck);
		}
	    }

            if(!pieceSuperiorEsquerda && superior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(superior_esquerda)){
		    pieceSuperiorEsquerda = true;
		}
		if(cor != board.GetPieceNaPosicao(superior_esquerda).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, superior_esquerda, testingCheck);
		}
	    }

            if(!pieceInferiorDireita && inferior_direita.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(inferior_direita)){
		    pieceInferiorDireita = true;
		}
		if(cor != board.GetPieceNaPosicao(inferior_direita).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, inferior_direita, testingCheck);
		}
	    }

            if(!pieceInferiorEsquerda && inferior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(inferior_esquerda)){
		    pieceInferiorEsquerda = true;
		}
		if(cor != board.GetPieceNaPosicao(inferior_esquerda).GetOurColorPiece()){
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
