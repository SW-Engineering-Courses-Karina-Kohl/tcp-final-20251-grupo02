package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;
import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import jogo.Jogada;
import static com.raylib.Raylib.LoadTexture;

public class Torre extends Peca {

    public boolean jaMovido = false;
	private static Texture textura = LoadTexture("res/pecas/torre.png");

    public Torre(int x, int y, char id){
        super(x, y, id, textura);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){

	movimentos = new ArrayList<>();
	char cor = this.GetCorPeca();

	boolean PecaCima = false;
	boolean PecaBaixo = false;
	boolean PecaDireita = false;
	boolean PecaEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.grid_position.add(new Pair(0, - i));
            Pair baixo = this.grid_position.add(new Pair(0, + i));

            Pair direita = this.grid_position.add(new Pair(+ i, 0));
            Pair esquerda = this.grid_position.add(new Pair(- i, 0));

	    if(!PecaCima && cima.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.IsTherePecaNaPosicao(cima)){
		    PecaCima = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(cima).GetCorPeca()){
		    movimentos.add(cima);
		}
	    }

	    if(!PecaBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.IsTherePecaNaPosicao(baixo)){
		    PecaBaixo = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(baixo).GetCorPeca()){
		    movimentos.add(baixo);
		}
	    }

	    if(!PecaDireita && direita.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.IsTherePecaNaPosicao(direita)){
		    PecaDireita = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(direita).GetCorPeca()){
		    movimentos.add(direita);
		}
	    }

	    if(!PecaEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(tabuleiro.IsTherePecaNaPosicao(esquerda)){
		    PecaEsquerda = true;
		}
		if(cor != tabuleiro.GetPecaNaPosicao(esquerda).GetCorPeca()){
		    movimentos.add(esquerda);
		}
	    }

        }

        return movimentos;
    }

    @Override
    public void Mover(Jogada jogada){
        super.Mover(jogada);
        this.jaMovido = true;
    }

}
