package game.pieces;

import game.*;
import misc.Pair;
import java.util.ArrayList;
import gui.*;

public abstract class Piece {
    public final int SIZE = 8;
    private char id;
    private Pair boardPosition;
    private ArrayList<Pair> movements = new ArrayList<>();
    private Sprite sprite;

    public Piece(int x, int y, char id) {
        this.boardPosition = new Pair(x, y);
        this.id = id;
    }

    public ArrayList<Pair> getMovements() {
        return movements;
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

    public void setMovements(ArrayList<Pair> newMovements) {
        this.movements = newMovements;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setCurrentImage(int image)
    {
        this.sprite.SetCurrentImage(image);
    }

    public void movePiece(Move move) {
        this.boardPosition = move.getCapturedPiece().getBoardPosition();
    }


    public abstract ArrayList<Pair> validMovements(Board Board, boolean testingCheck);

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
    public void checkMovement(Board board, ArrayList<Pair> movs, Pair movement, boolean testingCheck) {

        if (testingCheck) {
            if (!board.moveLeadsToCheck(this, this.findPieceColor(), movement)) {
                movs.add(movement);
            }
        } else {
            movs.add(movement);
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
