package jogo.pieces;
import jogo.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Bishop extends Piece {

	private static Texture bispoTexture = LoadTexture("res/pecas/bispo.png");
    public Bishop(int x, int y, char id){
        super(x, y, id);

        if (GetOurColorPiece() == 'b')
            sprite = new Sprite(bispoTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(bispoTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Board tabuleiro, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();

	char cor = this.GetOurColorPiece();

	boolean pecaSuperiorDireita = false;
	boolean pecaSuperiorEsquerda = false;
	boolean pecaInferiorDireita = false;
	boolean pecaInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            // diagonais
            Pair superior_direita = this.posicaoBoard.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.posicaoBoard.add(new Pair(- i, - i));

            Pair inferior_direita = this.posicaoBoard.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.posicaoBoard.add(new Pair(- i, + i));

            if(!pecaSuperiorDireita && superior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(superior_direita)){
		    pecaSuperiorDireita = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(superior_direita).GetOurColorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, superior_direita, testingCheck);
		}
	    }

            if(!pecaSuperiorEsquerda && superior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(superior_esquerda)){
		    pecaSuperiorEsquerda = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(superior_esquerda).GetOurColorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, superior_esquerda, testingCheck);
		}
	    }

            if(!pecaInferiorDireita && inferior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(inferior_direita)){
		    pecaInferiorDireita = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(inferior_direita).GetOurColorPiece()){
		    this.CheckMoviment(tabuleiro, newMovimentos, inferior_direita, testingCheck);
		}
	    }

            if(!pecaInferiorEsquerda && inferior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(inferior_esquerda)){
		    pecaInferiorEsquerda = true;
		}
		if(cor != tabuleiro.GetPieceNaPosicao(inferior_esquerda).GetOurColorPiece()){
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
