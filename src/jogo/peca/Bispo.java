package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Bispo extends Peca {

	private static Texture bispoTexture = LoadTexture("res/pecas/bispo.png");
    public Bispo(int x, int y, char id){
        super(x, y, id);

        if (GetCorPeca() == 'b')
            sprite = new Sprite(bispoTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(bispoTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){

	movimentos = new ArrayList<>();
	char cor = this.GetCorPeca();

	boolean PecaSuperiorDireita = false;
	boolean PecaSuperiorEsquerda = false;
	boolean PecaInferiorDireita = false;
	boolean PecaInferiorEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            // diagonais
            Pair superior_direita = this.grid_position.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.grid_position.add(new Pair(- i, - i));

            Pair inferior_direita = this.grid_position.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.grid_position.add(new Pair(- i, + i));

            if(!PecaSuperiorDireita && superior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.IsTherePecaNaPosicao(superior_direita)){
		    PecaSuperiorDireita = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(superior_direita).GetCorPeca()){
		    movimentos.add(superior_direita);
		}
	    }

            if(!PecaSuperiorEsquerda && superior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.IsTherePecaNaPosicao(superior_esquerda)){
		    PecaSuperiorEsquerda = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(superior_esquerda).GetCorPeca()){
		    movimentos.add(superior_esquerda);
		}
	    }

            if(!PecaInferiorDireita && inferior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.IsTherePecaNaPosicao(inferior_direita)){
		    PecaInferiorDireita = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(inferior_direita).GetCorPeca()){
		    movimentos.add(inferior_direita);
		}
	    }

            if(!PecaInferiorEsquerda && inferior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.IsTherePecaNaPosicao(inferior_esquerda)){
		    PecaInferiorEsquerda = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(inferior_esquerda).GetCorPeca()){
		    movimentos.add(inferior_esquerda);
		}
	    }

        }

        return movimentos;
    }

}
