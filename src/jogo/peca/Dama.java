package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;
import java.util.ArrayList;

public class Dama extends Peca {

    public Dama(int x, int y, char id){
        super(x, y, id);
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
            Pair cima = this.grid_position.add(new Pair(0, - i));
            Pair baixo = this.grid_position.add(new Pair(0, + i));

            Pair direita = this.grid_position.add(new Pair(+ i, 0));
            Pair esquerda = this.grid_position.add(new Pair(- i, 0));

            // diagonais
            Pair superior_direita = this.grid_position.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.grid_position.add(new Pair(- i, - i));

            Pair inferior_direita = this.grid_position.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.grid_position.add(new Pair(- i, + i));


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
