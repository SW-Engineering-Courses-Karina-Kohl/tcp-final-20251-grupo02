package game;

import game.pieces.Queen;
import game.pieces.Pawn;
import game.pieces.Piece;

public class Match {

    final int  MAX_PEÇAS = 32;
    final int  MAX_JOGADORES = 2;

    public Board board;
    public Player whitePlayer;
    public Player blackPlayer;

    public Player currentTurnPlayer = whitePlayer;

    public void NovoMatch() {
        NovoMatch(300); // Standard time
    }

    public void NovoMatch(int initialTime){
        this.board = new Board();
        this.whitePlayer = new Player('w', initialTime);
        this.blackPlayer = new Player('b', initialTime);
        this.currentTurnPlayer = whitePlayer;

        this.currentTurnPlayer.GetClock().StartClock();
    }


    public void NextTurn(){
        currentTurnPlayer.GetClock().StopClock();

        if (this.currentTurnPlayer == this.whitePlayer)
            this.currentTurnPlayer = this.blackPlayer;
        else this.currentTurnPlayer = this.whitePlayer;

        currentTurnPlayer.GetClock().StartClock();
        // this.board.GirarBoard();
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
