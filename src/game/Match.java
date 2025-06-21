package game;

public class Match {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    private Player currentTurnPlayer = whitePlayer;

    public Match(int initialTime, boolean useUI, boolean initPieces) {
        this.board = new Board(useUI, initPieces);
        this.whitePlayer = new Player('w', initialTime);
        this.blackPlayer = new Player('b', initialTime);
        this.currentTurnPlayer = whitePlayer;

        this.currentTurnPlayer.getClock().startClock();
    }

    public void nextTurn() {
        currentTurnPlayer.getClock().stopClock();

        if (this.currentTurnPlayer == this.whitePlayer) {
            this.currentTurnPlayer = this.blackPlayer;
        } else {
            this.currentTurnPlayer = this.whitePlayer;
        }

        currentTurnPlayer.getClock().startClock();
    }

    public Board getBoard() {
        return board;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }
}
