package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Cavalo extends Peca {

    private static Texture cavaloTexture = LoadTexture("res/pecas/cavalo.png");

    public Cavalo(int x, int y, char id){
        super(x, y, id);

        if (GetCorPeca() == 'b')
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetCorPeca();

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

        if(cima_direita.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(cima_direita).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, cima_direita, testingCheck);

        if(cima_esquerda.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(cima_esquerda).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, cima_esquerda, testingCheck);

        if(baixo_direita.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(baixo_direita).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, baixo_direita, testingCheck);

        if(baixo_esquerda.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(baixo_esquerda).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, baixo_esquerda, testingCheck);

        if(direita_cima.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(direita_cima).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, direita_cima, testingCheck);

        if(direita_baixo.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(direita_baixo).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, direita_baixo, testingCheck);

        if(esquerda_cima.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(esquerda_cima).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, esquerda_cima, testingCheck);

        if(esquerda_baixo.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPecaNaPosicao(esquerda_baixo).GetCorPeca())
	    this.CheckMoviment(tabuleiro, newMovimentos, esquerda_baixo, testingCheck);

	if(testingCheck){
	    movimentos = newMovimentos;
	}

        return newMovimentos;
    }

}
