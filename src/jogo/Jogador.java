package jogo;
import jogo.peca.Peca;
import java.util.ArrayList;

public class Jogador {

    

    ArrayList<Peca> pecas = new ArrayList<>();
    String cor;
    Relogio relogio;

    public Jogador(String cor, int tempoInicial){
        this.cor = cor;
        this.relogio = new Relogio(tempoInicial);

    }


    public Jogada NovaJogada(Peca peca_movida, Peca peca_capturada, Tabuleiro tabuleiro){
        Jogada jogada = new Jogada(peca_movida, peca_capturada);
        jogada.ValidarJogada(tabuleiro);
        return jogada;
    }

    public Relogio getRelogio() {
        return relogio;
    }

   

}
