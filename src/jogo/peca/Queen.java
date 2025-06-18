package jogo.peca;
import jogo.Tabuleiro;
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

        if (GetCorPiece() == 'b')
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetCorPiece();

	boolean pecaCima = false;
	boolean pecaBaixo = false;
	boolean pecaDireita = false;
	boolean pecaEsquerda = false;
	boolean pecaSuperiorDireita = false;
	boolean pecaSuperiorEsquerda = false;
	boolean pecaInferiorDireita = false;
	boolean pecaInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.posicaoTabuleiro.add(new Pair(0, - i));
            Pair baixo = this.posicaoTabuleiro.add(new Pair(0, + i));

            Pair direita = this.posicaoTabuleiro.add(new Pair(+ i, 0));
            Pair esquerda = this.posicaoTabuleiro.add(new Pair(- i, 0));

            // diagonais
            Pair superior_direita = this.posicaoTabuleiro.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.posicaoTabuleiro.add(new Pair(- i, - i));

            Pair inferior_direita = this.posicaoTabuleiro.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.posicaoTabuleiro.add(new Pair(- i, + i));



	    if(!pecaCima && cima.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(cima)){
		    pecaCima = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(cima).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, cima, testingCheck);
		}
	    }

	    if(!pecaBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(baixo)){
		    pecaBaixo = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(baixo).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, baixo, testingCheck);
		}
	    }

	    if(!pecaDireita && direita.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(direita)){
		    pecaDireita = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(direita).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, direita, testingCheck);
		}
	    }

	    if(!pecaEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(esquerda)){
		    pecaEsquerda = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(esquerda).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, esquerda, testingCheck);
		}
	    }

            if(!pecaSuperiorDireita && superior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(superior_direita)){
		    pecaSuperiorDireita = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(superior_direita).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, superior_direita, testingCheck);
		}
	    }

            if(!pecaSuperiorEsquerda && superior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(superior_esquerda)){
		    pecaSuperiorEsquerda = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(superior_esquerda).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, superior_esquerda, testingCheck);
		}
	    }

            if(!pecaInferiorDireita && inferior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(inferior_direita)){
		    pecaInferiorDireita = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(inferior_direita).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, inferior_direita, testingCheck);
		}
	    }

            if(!pecaInferiorEsquerda && inferior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(inferior_esquerda)){
		    pecaInferiorEsquerda = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(inferior_esquerda).GetCorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, inferior_esquerda, testingCheck);
		}
	    }
        }

	if(testingCheck){
	    movimentos = newMovimentos;
	}

        return newMovimentos;
    }

}
