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

    public char GetColorPlayer(){
	return cor;
    }

    public Move NovaMove(Piece movedPiece, Piece capturedPiece, Board board){
        Move move = new Move(movedPiece, capturedPiece);
        move.ValidarMove(board);
        return move;
    }

    public Clock GetClock() {
        return relogio;
    }



}
