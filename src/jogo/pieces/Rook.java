package jogo.pieces;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import jogo.Jogada;

public class Rook extends Piece {

    public boolean jaMovido = false;
	private static Texture torreTexture = LoadTexture("res/pecas/torre.png");

    public Rook(int x, int y, char id){
        super(x, y, id);

        if (GetCorPiece() == 'b')
            sprite = new Sprite(torreTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(torreTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetCorPiece();

	boolean pecaCima = false;
	boolean pecaBaixo = false;
	boolean pecaDireita = false;
	boolean pecaEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.posicaoTabuleiro.add(new Pair(0, - i));
            Pair baixo = this.posicaoTabuleiro.add(new Pair(0, + i));

            Pair direita = this.posicaoTabuleiro.add(new Pair(+ i, 0));
            Pair esquerda = this.posicaoTabuleiro.add(new Pair(- i, 0));

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

        }

	if(testingCheck){
	    movimentos = newMovimentos;
	}

        return newMovimentos;
    }

    @Override
    public void Mover(Jogada jogada){
        super.Mover(jogada);
        this.jaMovido = true;
    }

}
