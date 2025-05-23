package jogo;
import jogo.peca.*;
import misc.Pair;

public class Jogada {
    
    public Peca peca_movida;
    public Peca peca_capturada;
    //Pair movimento;
    boolean jogada_valida = false;
    //Jogador jogador;

    // peca_movida = tab.getPecaNaPosicao(x,y)
    // peca_capturada = tab.getPecaNaPosicao(x,y)
    public Jogada(Peca peca_movida, Peca peca_capturada){
        this.peca_movida = peca_movida;
        this.peca_capturada = peca_capturada;
        //this.movimento = this.peca_movida.grid_position.add(new Pair(-posicao_final.x, -posicao_final.y));
    }

    // valida se a jogada pode ser feita e muda o tabuleiro 
    public void ValidarJogada(Tabuleiro tabuleiro){
                
        // se nao existe nenhuma peça entre a movida e a capturada e 
        // a peça capturada está em uma casa que a peça movida pode se movimentar:
        if (!this.IsTherePecaInBetween(tabuleiro) && peca_movida.MovimentosValidos().contains(this.peca_capturada.grid_position)){
            jogada_valida = true;
        }
            
        // roque:
        boolean torrePodeFazerRoque = this.peca_movida instanceof Torre && !((Torre) this.peca_movida).jaMovido
                                        // torre está uma das duas posições iniciais
                                        && (this.peca_movida.grid_position.equals(new Pair(0, 7)) 
                                        || this.peca_movida.grid_position.equals(new Pair(7, 7)));
        
        boolean reiPodeFazerRoque =  this.peca_capturada instanceof Rei && !((Rei) this.peca_capturada).jaMovido
                                        // rei está na posicão inicial
                                        && this.peca_capturada.grid_position.equals(new Pair(3, 7));
                                          
        if ( reiPodeFazerRoque && torrePodeFazerRoque )
            jogada_valida = true;
        
        // se as peças da jogada tiverem a mesma cor:
        if ( (Character.isLowerCase(this.peca_movida.identificador) &&  Character.isLowerCase(this.peca_capturada.identificador)) ||
                (Character.isUpperCase(this.peca_movida.identificador) && Character.isUpperCase(this.peca_capturada.identificador)) 
        ){
            jogada_valida = false;
        }
        
        
        // movimentação especial do peão
        if (peca_movida instanceof Peao && 
            // se a peça capturada está logo acima do peão:
            peca_capturada.grid_position.equals( 
            new Pair (peca_movida.grid_position.x, peca_movida.grid_position.y - 1))
            ){
            jogada_valida = false;
            return;
        }
            
        if(peca_movida instanceof Peao &&
            // se a peça capturada está nas diagonais superiores do peão:
            ((peca_capturada.grid_position.equals(
                new Pair (peca_movida.grid_position.x + 1, peca_movida.grid_position.y - 1))) || 
            (peca_capturada.grid_position.equals( 
                new Pair (peca_movida.grid_position.x - 1, peca_movida.grid_position.y - 1)) ))
        )

            jogada_valida = true;
            

        if (this.jogada_valida){
            tabuleiro.MudancaNoTabuleiro(this);
            peca_movida.Mover(this);
            peca_capturada.DestruirPeca();
        }
            
    }
       
    public boolean IsTherePecaInBetween(Tabuleiro tabuleiro){
        // 1 if posicao_final > peca_movida.grid_position.x
        // 0 if if posicao_final == peca_movida.grid_position.x
        // -1 if posicao_final > peca_movida.grid_position.x
        
        int dx = Integer.compare(this.peca_capturada.grid_position.x, this.peca_movida.grid_position.x); 
        int dy = Integer.compare(this.peca_capturada.grid_position.y, this.peca_movida.grid_position.y); 

        int x = peca_movida.grid_position.x + dx;
        int y = peca_movida.grid_position.y + dy;

        while (! this.peca_capturada.grid_position.equals(new Pair(x, y)) ) {
            if (tabuleiro.GetPecaNaPosicao(x, y) != null) {
                return true; 
            }
            
            x += dx;
            y += dy;
        }

        return false; 
    }

}
