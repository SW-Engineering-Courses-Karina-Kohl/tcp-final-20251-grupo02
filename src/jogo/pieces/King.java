package jogo.pieces;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import jogo.Jogada;

public class King extends Piece{

    public boolean jaMovido = false;
    private static Texture reiTexture = LoadTexture("res/pecas/rei.png");

    public King(int x, int y, char id){
        super(x, y, id);

        if (GetCorPiece() == 'b')
            sprite = new Sprite(reiTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(reiTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetCorPiece();

        Pair cima = this.posicaoTabuleiro.add(new Pair(0, - 1));
        Pair baixo = this.posicaoTabuleiro.add(new Pair(0, + 1));

        Pair direita = this.posicaoTabuleiro.add(new Pair(+ 1, 0));
        Pair esquerda = this.posicaoTabuleiro.add(new Pair(- 1, 0));

        // diagonais
        Pair superior_direita = this.posicaoTabuleiro.add(new Pair(+ 1, - 1));
        Pair superior_esquerda = this.posicaoTabuleiro.add(new Pair(- 1, - 1));

        Pair inferior_direita = this.posicaoTabuleiro.add(new Pair(+ 1, + 1));
        Pair inferior_esquerda = this.posicaoTabuleiro.add(new Pair(- 1, + 1));

        if(cima.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(cima).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, cima, testingCheck);

        if(baixo.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(baixo).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, baixo, testingCheck);

        if(direita.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(direita).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, direita, testingCheck);

        if(esquerda.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(esquerda).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, esquerda, testingCheck);

        if(superior_direita.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(superior_direita).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, superior_direita, testingCheck);

        if(superior_esquerda.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(superior_esquerda).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, superior_esquerda, testingCheck);

        if(inferior_direita.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(inferior_direita).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, inferior_direita, testingCheck);

        if(inferior_esquerda.IsPieceInsideBoard(0, SIZE) && cor != tabuleiro.GetPieceNaPosicao(inferior_esquerda).GetCorPiece())
	    this.CheckMoviment(tabuleiro, newMovimentos, inferior_esquerda, testingCheck);


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
