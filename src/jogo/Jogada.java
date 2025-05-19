package jogo;
import jogo.peca.Peca;
import jogo.peca.Peca.Pair;

public class Jogada {
    Peca peca_movida;
    Peca peca_capturada;
    Pair movimento;

    public Jogada(Peca peca_movida, Peca peca_capturada, Pair movimento){
        this.peca_movida = peca_movida;
        this.peca_capturada = peca_capturada;
        this.movimento = movimento;
    }

    boolean ValidarJogada(){
        this.peca_movida.grid_position.add(this.movimento);
        return true;
        
        
    
    }
}
