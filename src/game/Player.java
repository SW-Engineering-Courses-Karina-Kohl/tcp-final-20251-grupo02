package game;
import game.pieces.Piece;
import java.util.ArrayList;

public class Player {



    ArrayList<Piece> pecas = new ArrayList<>();
    char cor;
    Clock relogio;
    public boolean emCheque;

    public Player(char cor, int tempoInicial){
        this.cor = cor;
        this.relogio = new Clock(tempoInicial);
	this.emCheque = false;

    }


    public boolean IsInCheck(){
	return emCheque;
    }

    public char GetOurColorPlayer(){
	return cor;
    }

    public Jogada NovaJogada(Piece pecaMovida, Piece peca_capturada, Board board){
        Jogada jogada = new Jogada(pecaMovida, peca_capturada);
        jogada.ValidarJogada(board);
        return jogada;
    }

    public Clock GetClock() {
        return relogio;
    }



}
