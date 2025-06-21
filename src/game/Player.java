package game;

public class Player {

    private char color;
    private Clock clock;
    private boolean inCheck;

    public Player(char color, int initialTime) {
        this.color = color;
        this.clock = new Clock(initialTime);
        this.inCheck = false;
    }

    public char getColor() {
        return color;
    }

    public Clock getClock() {
        return clock;
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setCheckStatus(boolean inCheck) {
        this.inCheck = inCheck;
    }
}
