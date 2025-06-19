package game;
import game.pieces.Piece;
import java.util.ArrayList;

public class Player {

    private char color;
    private Clock relogio;
    private boolean inCheck;

    public Player(char color, int initialTime){
        this.color = color;
        this.relogio = new Clock(initialTime);
	this.inCheck = false;
    }

    public boolean IsInCheck(){
	return inCheck;
    }

    public void SetCheckStatus(boolean inCheck){
	this.inCheck = inCheck;
    }

    public char GetColorPlayer(){
	return color;
    }

    public Clock GetClock() {
        return relogio;
    }



}
