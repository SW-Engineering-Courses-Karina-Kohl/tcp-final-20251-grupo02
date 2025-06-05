package jogo;
import jogo.peca.*;
import misc.Pair;

public class Jogada {

    public Peca peca_movida;
    public Peca peca_capturada;
    //Pair movimento;
    public boolean jogada_valida = false;
    //Jogador jogador;

    // peca_movida = tab.getPecaNaPosicao(x,y)
    // peca_capturada = tab.getPecaNaPosicao(x,y)
    public Jogada(Peca peca_movida, Peca peca_capturada){
        this.peca_movida = peca_movida;
        this.peca_capturada = peca_capturada;
        //this.movimento = this.peca_movida.grid_position.add(new Pair(-posicao_final.x, -posicao_final.y));
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
        // se a peça capturada está logo acima do peão:
        if (peca_capturada.grid_position.equals(new Pair (peca_movida.grid_position.x, peca_movida.grid_position.y - 1))){
                //this.jogada_valida = false;
                return false;
        }

        /* 
        if (// se a peça capturada está duas casas acima do peão:
        peca_capturada.grid_position.equals(
                        new Pair (peca_movida.grid_position.x, peca_movida.grid_position.y - 2))
        )
            return true;
        */

        // se a peça capturada está nas diagonais superiores do peão:
        if(((peca_capturada.grid_position.equals(
            new Pair (peca_movida.grid_position.x + 1, peca_movida.grid_position.y - 1))) ||
        (peca_capturada.grid_position.equals(
            new Pair (peca_movida.grid_position.x - 1, peca_movida.grid_position.y - 1)) ))
        )
            return true;
        
        return false;
    }


    // valida se a jogada pode ser feita e muda o tabuleiro
    public boolean ValidarJogada(Tabuleiro tabuleiro){
        // movimentação do cavalo
        if (this.peca_movida instanceof Cavalo && peca_movida.MovimentosValidos().contains(this.peca_capturada.grid_position) ){
            return true;
        }

        // se a posição da peça capturada está vazia,
        // nao existe nenhuma peça entre a movida e a capturada e
        // a peça capturada está em uma casa que a peça movida pode se movimentar:
        // (happiest case)
        if (this.peca_capturada instanceof Blank 
        &&  peca_movida.MovimentosValidos().contains(this.peca_capturada.grid_position) 
        && !this.IsTherePecaInBetween(tabuleiro)){
            return true; 
        }

        // validar as movimentações especiais do peão
        if(this.peca_movida instanceof Peao)
            return this.ValidarJogadaPeao(tabuleiro);

        // validar o roque (jogada do rei na torre)
        if(this.peca_movida instanceof Rei && this.peca_capturada instanceof Torre)
            return this.ValidarRoque(tabuleiro);

        // se as peças da jogada tiverem a mesma cor:
        if ( (Character.isLowerCase(this.peca_movida.identificador) &&  Character.isLowerCase(this.peca_capturada.identificador)) ||
            (Character.isUpperCase(this.peca_movida.identificador) && Character.isUpperCase(this.peca_capturada.identificador))
        ){
            //this.jogada_valida = false;
            return false;
        }

	    // Remover, smell, mover na main
        if (this.jogada_valida){
            
        }
    
        return false;
    }

    private boolean IsTherePecaInBetween(Tabuleiro tabuleiro){
        // 1 if posicao_final > peca_movida.grid_position.x
        // 0 if if posicao_final == peca_movida.grid_position.x
        // -1 if posicao_final > peca_movida.grid_position.x

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
