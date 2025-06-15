package jogo;
import jogo.peca.Peca;
import java.util.ArrayList;

public class Jogador {



    ArrayList<Peca> pecas = new ArrayList<>();
    char cor;
    Relogio relogio;

    public Jogador(char cor, int tempoInicial){
        this.cor = cor;
        this.relogio = new Relogio(tempoInicial);

    }

    public char GetCorJogador(){
	return cor;
    }

    public Jogada NovaJogada(Peca pecaMovida, Peca peca_capturada, Tabuleiro tabuleiro){
        Jogada jogada = new Jogada(pecaMovida, peca_capturada);
        jogada.ValidarJogada(tabuleiro);
        return jogada;
    }

    public Relogio GetRelogio() {
        return relogio;
    }



}
