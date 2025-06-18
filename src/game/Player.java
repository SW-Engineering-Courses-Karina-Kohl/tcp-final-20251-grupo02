package game;
import game.pieces.Piece;
import java.util.ArrayList;

public class Player {



    ArrayList<Piece> pieces = new ArrayList<>();
    char color;
    Clock relogio;
    public boolean emCheque;

    public Player(char color, int initialTime){
        this.color = color;
        this.relogio = new Clock(initialTime);
	this.emCheque = false;

    }


    public boolean IsInCheck(){
	return emCheque;
    }

    public char GetColorPlayer(){
	return color;
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
