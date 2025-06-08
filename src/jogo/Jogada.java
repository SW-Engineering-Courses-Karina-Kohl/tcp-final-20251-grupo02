package jogo;
import jogo.peca.*;
import misc.Pair;

public class Jogada {

    public Peca peca_movida;
    public Peca peca_capturada;
    //Jogador jogador;

    // peca_movida = tab.getPecaNaPosicao(x,y)
    // peca_capturada = tab.getPecaNaPosicao(x,y)
    public Jogada(Peca peca_movida, Peca peca_capturada){
        this.peca_movida = peca_movida;
        this.peca_capturada = peca_capturada;
    }

    private boolean ValidarRoque(Tabuleiro tabuleiro){
        boolean reiPodeFazerRoque =     !((Rei) this.peca_movida).jaMovido
                                        // rei está na posicão inicial
                                        && this.peca_movida.grid_position.equals(new Pair(3, 7));

        boolean torrePodeFazerRoque =   !((Torre) this.peca_capturada).jaMovido
                                        // torre está uma das duas posições iniciais
                                        && (this.peca_capturada.grid_position.equals(new Pair(0, 7))
                                        || this.peca_capturada.grid_position.equals(new Pair(7, 7)));

        if ( reiPodeFazerRoque && torrePodeFazerRoque )
            return true;
        return false;
    }

    private boolean ValidarJogadaPeao(Tabuleiro tabuleiro){


	int direcao = -1; // direção permitida de movimento para peça
	if(peca_movida.identificador == 'P'){
	    direcao = -1;
	} else {
	    direcao = 1;
	}

	// Se a posição estiver vazia.
	if(this.peca_capturada instanceof Blank){

	    // se quer andar um para frente e esta vazio
	    if (this.peca_capturada.grid_position.equals(new Pair (peca_movida.grid_position.x, peca_movida.grid_position.y + (direcao * 1))))
                return true;

	    // se quer andar dois para frente e esta vazio, retorna falso
	    if (!(((Peao) peca_movida).jaMovido) &&(peca_capturada.grid_position.equals(new Pair (peca_movida.grid_position.x, peca_movida.grid_position.y + (direcao * 2)))))
		return true;

	    // se tentar andar na diagonal sem ter peça para capturar
	    if(((peca_capturada.grid_position.equals(
						     new Pair (peca_movida.grid_position.x + 1, peca_movida.grid_position.y + (direcao*1)))) ||
		(peca_capturada.grid_position.equals(
						     new Pair (peca_movida.grid_position.x - 1, peca_movida.grid_position.y + (direcao*1))) )))
		return false;


	} else {
	    // se quer andar um para frente e não está vazio retorna falso
	    if (peca_capturada.grid_position.equals(new Pair (peca_movida.grid_position.x, peca_movida.grid_position.y + (direcao * 1))))
                return false;

	    // se quer andar dois para frente e não esta vazio, retorna falso
	    if (peca_capturada.grid_position.equals(new Pair (peca_movida.grid_position.x, peca_movida.grid_position.y + (direcao * 2))))
		return false;

	    // se quer capturar na diagonal e tem peça lá, captura
	    if(((peca_capturada.grid_position.equals(
			    	new Pair (peca_movida.grid_position.x + 1, peca_movida.grid_position.y + (direcao*1)))) ||
		(peca_capturada.grid_position.equals(
				new Pair (peca_movida.grid_position.x - 1, peca_movida.grid_position.y + (direcao*1))) )))
		return true;

	}

        return false;
    }


    // valida se a jogada pode ser feita e muda o tabuleiro
    public boolean ValidarJogada(Tabuleiro tabuleiro){


        // validar as movimentações especiais do peão
        if(this.peca_movida instanceof Peao)
            return this.ValidarJogadaPeao(tabuleiro);

        // se as peças da jogada tiverem a mesma cor:
        if ((Character.isLowerCase(this.peca_movida.identificador)
        && Character.isLowerCase(this.peca_capturada.identificador)) ||
        (Character.isUpperCase(this.peca_movida.identificador)
        && Character.isUpperCase(this.peca_capturada.identificador)))
            return false;

        // movimentação do cavalo
        if (this.peca_movida instanceof Cavalo
        && peca_movida.MovimentosValidos().contains(this.peca_capturada.grid_position) )
            return true;

        // se o peão está na última posição vertical:
        if ( this.peca_movida instanceof Peao
        && peca_capturada.grid_position.y == 0)
            this.peca_movida = ((Peao) this.peca_movida).Promover();

        // se a posição da peça capturada está vazia,
        // nao existe nenhuma peça entre a movida e a capturada e
        // a peça capturada está em uma casa que a peça movida pode se movimentar:
        if (this.peca_capturada instanceof Blank
        &&  peca_movida.MovimentosValidos().contains(this.peca_capturada.grid_position)
        && !this.IsTherePecaInBetween(tabuleiro))
            return true;

        return false;

    }

    private boolean IsTherePecaInBetween(Tabuleiro tabuleiro){
        // +1 if peca_capturada.grid_position > peca_movida.grid_position
        //  0 if peca_capturada.grid_position == peca_movida.grid_position
        // -1 if peca_capturada.grid_position < peca_movida.grid_position

        int dx = Integer.compare(this.peca_capturada.grid_position.x, this.peca_movida.grid_position.x);
        int dy = Integer.compare(this.peca_capturada.grid_position.y, this.peca_movida.grid_position.y);

        int x = peca_movida.grid_position.x + dx;
        int y = peca_movida.grid_position.y + dy;

        while (! this.peca_capturada.grid_position.equals(new Pair(x, y)) ) {
            if (!(tabuleiro.GetPecaNaPosicao(x, y) instanceof Blank)) {
                return true;
            }

            x += dx;
            y += dy;
        }

        return false;
    }

}
