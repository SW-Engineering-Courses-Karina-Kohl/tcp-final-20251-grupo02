package jogo.pieces;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Knight extends Piece {

    private static Texture cavaloTexture = LoadTexture("res/pecas/cavalo.png");

    public Knight(int x, int y, char id){
        super(x, y, id);

        if (GetCorPiece() == 'b')
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetCorPiece();

        // L pra cima direita esquerda
        Pair cima_direita = this.posicaoTabuleiro.add(new Pair(+ 1, - 2));
        Pair cima_esquerda = this.posicaoTabuleiro.add(new Pair(- 1, - 2));

        // L pra baixo direita esquerda
        Pair baixo_direita = this.posicaoTabuleiro.add(new Pair(+ 1, + 2));
        Pair baixo_esquerda = this.posicaoTabuleiro.add(new Pair(- 1, + 2));

        // L pra direita cima baixo
        Pair direita_cima = this.posicaoTabuleiro.add(new Pair(+ 2, - 1));
        Pair direita_baixo = this.posicaoTabuleiro.add(new Pair(+ 2, + 1));

        // L pra esquerda cima baixo
        Pair esquerda_cima = this.posicaoTabuleiro.add(new Pair(- 2, - 1));
        Pair esquerda_baixo = this.posicaoTabuleiro.add(new Pair(- 2, + 1));

        if(cima_direita.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(cima_direita).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, cima_direita, testingCheck);

        if(cima_esquerda.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(cima_esquerda).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, cima_esquerda, testingCheck);

        if(baixo_direita.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(baixo_direita).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, baixo_direita, testingCheck);

        if(baixo_esquerda.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(baixo_esquerda).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, baixo_esquerda, testingCheck);

        if(direita_cima.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(direita_cima).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, direita_cima, testingCheck);

        if(direita_baixo.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(direita_baixo).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, direita_baixo, testingCheck);

        if(esquerda_cima.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(esquerda_cima).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, esquerda_cima, testingCheck);

        if(esquerda_baixo.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(esquerda_baixo).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, esquerda_baixo, testingCheck);

	if(testingCheck){
	    movimentos = newMovimentos;
	}

        return newMovimentos;
    }

}
