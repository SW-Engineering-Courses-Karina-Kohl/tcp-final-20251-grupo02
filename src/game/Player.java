package game;
import game.pieces.Piece;
import java.util.ArrayList;

public class Player {



    ArrayList<Piece> pieces = new ArrayList<>();
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

    public Jogada NovaJogada(Piece pieceMovida, Piece piece_capturada, Board board){
        Jogada jogada = new Jogada(pieceMovida, piece_capturada);
        jogada.ValidarJogada(board);
        return jogada;
    }

    public Clock GetClock() {
        return relogio;
    }



}
