package jogo;
import jogo.peca.Piece;
import java.util.ArrayList;

public class Jogador {



    ArrayList<Piece> pecas = new ArrayList<>();
    char cor;
    Relogio relogio;
    public boolean emCheque;

    public Jogador(char cor, int tempoInicial){
        this.cor = cor;
        this.relogio = new Relogio(tempoInicial);
	this.emCheque = false;

    }


    public boolean IsInCheck(){
	return emCheque;
    }

    public char GetCorJogador(){
	return cor;
    }

    public Jogada NovaJogada(Piece pecaMovida, Piece peca_capturada, Tabuleiro tabuleiro){
        Jogada jogada = new Jogada(pecaMovida, peca_capturada);
        jogada.ValidarJogada(tabuleiro);
        return jogada;
    }

    public Relogio GetRelogio() {
        return relogio;
    }



}
