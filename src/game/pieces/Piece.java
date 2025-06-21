package game.pieces;

import game.*;
import misc.Pair;
import java.util.ArrayList;
import gui.*;

public abstract class Piece {
    public final int SIZE = 8;
    private char id;
    private Pair boardPosition;
    private ArrayList<Pair> moviments = new ArrayList<>();
    private Sprite sprite;

    public Piece(int x, int y, char id) {
        this.boardPosition = new Pair(x, y);
        this.id = id;
    }

    public ArrayList<Pair> getMoviments() {
        return moviments;
    }

    public Pair getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(Pair p) {
	boardPosition = p;
    }

    public char getPieceID() {
        return id;
    }

    public void setMovements(ArrayList<Pair> newMoviments) {
        this.moviments = newMoviments;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void movePiece(Move move) {
        this.boardPosition = move.getCapturedPiece().getBoardPosition();
    }


    public abstract ArrayList<Pair> validMoviments(Board Board, boolean testingCheck);

    public char findPieceColor() {

        if (this instanceof Blank) {
            return '_';
        }

        if (Character.isLowerCase(this.getPieceID())) {
            return 'b';
        }

        return 'w';

    }

    /*
     * add the moviment to the movs list only if this moviment doesn't lead to a
     * check
     */
    public void checkMoviment(Board board, ArrayList<Pair> movs, Pair moviment, boolean testingCheck) {

        if (testingCheck) {
            if (!board.moveLeadsToCheck(this, this.findPieceColor(), moviment)) {
                movs.add(moviment);
            }
        } else {
            movs.add(moviment);
        }

    }

    public void drawPiece(int xInitial, int yInitial) {
        if (sprite != null)
            sprite.DrawSpritePro(getBoardPosition().x * sprite.GetWidth() + (sprite.GetWidth() / 2) + xInitial,
                    getBoardPosition().y * sprite.GetHeight() + (sprite.GetHeight() / 2) + yInitial);
    }

    @Override
    public String toString() {
        return this.getPieceID() + " " + this.getBoardPosition();
    }

}
