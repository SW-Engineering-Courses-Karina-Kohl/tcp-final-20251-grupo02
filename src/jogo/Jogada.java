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

    // valida se a jogada pode ser feita e muda o tabuleiro
    public boolean ValidarJogada(Tabuleiro tabuleiro){

	for (Pair p : this.peca_movida.MovimentosValidos(tabuleiro)) {
	    if(p.x == this.peca_capturada.grid_position.x && p.y == this.peca_capturada.grid_position.y){
		return true;
	    }
        }

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
