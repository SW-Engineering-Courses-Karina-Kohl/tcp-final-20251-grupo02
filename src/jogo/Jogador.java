package jogo;
import jogo.peca.Peca;
import java.util.ArrayList;

public class Jogador {

    

    ArrayList<Peca> pecas = new ArrayList<>();
    String cor;

    public Jogador(String cor){
        this.cor = cor;


    }


    public Jogada NovaJogada(Peca peca_movida, Peca peca_capturada, Tabuleiro tabuleiro){
        Jogada jogada = new Jogada(peca_movida, peca_capturada);
        jogada.ValidarJogada(tabuleiro);
        return jogada;
    }

}
