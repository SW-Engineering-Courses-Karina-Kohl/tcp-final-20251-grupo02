package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Dama extends Peca {

	private static Texture rainhaTexture = LoadTexture("res/pecas/rainha.png");
    public Dama(int x, int y, char id){
        super(x, y, id);

        if (GetCorPeca() == 'b')
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(rainhaTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){

        movimentos = new ArrayList<>();
	char cor = this.GetCorPeca();

	boolean PecaCima = false;
	boolean PecaBaixo = false;
	boolean PecaDireita = false;
	boolean PecaEsquerda = false;
	boolean PecaSuperiorDireita = false;
	boolean PecaSuperiorEsquerda = false;
	boolean PecaInferiorDireita = false;
	boolean PecaInferiorEsquerda = false;

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


	    if(!PecaCima && cima.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(cima)){
		    PecaCima = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(cima).GetCorPeca()){
		    movimentos.add(cima);
		}
	    }

	    if(!PecaBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(baixo)){
		    PecaBaixo = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(baixo).GetCorPeca()){
		    movimentos.add(baixo);
		}
	    }

	    if(!PecaDireita && direita.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(direita)){
		    PecaDireita = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(direita).GetCorPeca()){
		    movimentos.add(direita);
		}
	    }

	    if(!PecaEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.PosicaoOcupada(esquerda)){
		    PecaEsquerda = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(esquerda).GetCorPeca()){
		    movimentos.add(esquerda);
		}
	    }

            if(!PecaSuperiorDireita && superior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(superior_direita)){
		    PecaSuperiorDireita = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(superior_direita).GetCorPeca()){
		    movimentos.add(superior_direita);
		}
	    }

            if(!PecaSuperiorEsquerda && superior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(superior_esquerda)){
		    PecaSuperiorEsquerda = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(superior_esquerda).GetCorPeca()){
		    movimentos.add(superior_esquerda);
		}
	    }

            if(!PecaInferiorDireita && inferior_direita.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(inferior_direita)){
		    PecaInferiorDireita = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(inferior_direita).GetCorPeca()){
		    movimentos.add(inferior_direita);
		}
	    }

            if(!PecaInferiorEsquerda && inferior_esquerda.IsPieceInsideBoard(0, SIZE)){

                if(tabuleiro.PosicaoOcupada(inferior_esquerda)){
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
