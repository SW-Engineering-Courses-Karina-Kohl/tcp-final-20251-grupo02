package game.pieces;

import game.Board;
import java.util.ArrayList;

import misc.Pair;

public class Blank extends Piece {

    public Blank(int x, int y) {
        super(x, y, '_');
    }

    public Blank(Pair p) {
        super(p.x, p.y, '_');
    }

    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck) {
        return this.getMovements();
    }

}
