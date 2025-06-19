package game;

import game.pieces.Queen;
import game.pieces.Pawn;
import game.pieces.Piece;

public class Match {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    private Player currentTurnPlayer = whitePlayer;

    public Match(int initialTime){
        this.board = new Board();
        this.whitePlayer = new Player('w', initialTime);
        this.blackPlayer = new Player('b', initialTime);
        this.currentTurnPlayer = whitePlayer;

        this.currentTurnPlayer.getClock().startClock();
    }


    public void NextTurn(){
        currentTurnPlayer.getClock().stopClock();

        if (this.currentTurnPlayer == this.whitePlayer){
            this.currentTurnPlayer = this.blackPlayer;
	} else {
	    this.currentTurnPlayer = this.whitePlayer;
	}

        currentTurnPlayer.getClock().startClock();
    }

    public Board GetBoard() {
        return board;
    }

    public Player GetWhitePlayer() {
        return whitePlayer;
    }

    public Player GetBlackPlayer() {
        return blackPlayer;
    }

    public Player GetCurrentTurnPlayer() {
        return currentTurnPlayer;
    }
}
