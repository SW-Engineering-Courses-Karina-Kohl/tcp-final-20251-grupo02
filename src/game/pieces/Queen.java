package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Queen extends Piece {

	private static Texture rainhaTexture = LoadTexture("res/pecas/rainha.png");
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

	boolean pecaCima = false;
	boolean pecaBaixo = false;
	boolean pecaDireita = false;
	boolean pecaEsquerda = false;
	boolean pecaSuperiorDireita = false;
	boolean pecaSuperiorEsquerda = false;
	boolean pecaInferiorDireita = false;
	boolean pecaInferiorEsquerda = false;

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



	    if(!pecaCima && cima.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(cima)){
		    pecaCima = true;
		}
		if(cor != board.GetPieceNaPosicao(cima).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, cima, testingCheck);
		}
	    }

	    if(!pecaBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(baixo)){
		    pecaBaixo = true;
		}
		if(cor != board.GetPieceNaPosicao(baixo).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);
		}
	    }

	    if(!pecaDireita && direita.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(direita)){
		    pecaDireita = true;
		}
		if(cor != board.GetPieceNaPosicao(direita).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, direita, testingCheck);
		}
	    }

	    if(!pecaEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(board.PosicaoOcupada(esquerda)){
		    pecaEsquerda = true;
		}
		if(cor != board.GetPieceNaPosicao(esquerda).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);
		}
	    }

            if(!pecaSuperiorDireita && superior_direita.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(superior_direita)){
		    pecaSuperiorDireita = true;
		}
		if(cor != board.GetPieceNaPosicao(superior_direita).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, superior_direita, testingCheck);
		}
	    }

            if(!pecaSuperiorEsquerda && superior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(superior_esquerda)){
		    pecaSuperiorEsquerda = true;
		}
		if(cor != board.GetPieceNaPosicao(superior_esquerda).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, superior_esquerda, testingCheck);
		}
	    }

            if(!pecaInferiorDireita && inferior_direita.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(inferior_direita)){
		    pecaInferiorDireita = true;
		}
		if(cor != board.GetPieceNaPosicao(inferior_direita).GetOurColorPiece()){
		    this.CheckMoviment(board, newMovimentos, inferior_direita, testingCheck);
		}
	    }

            if(!pecaInferiorEsquerda && inferior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(board.PosicaoOcupada(inferior_esquerda)){
		    pecaInferiorEsquerda = true;
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
