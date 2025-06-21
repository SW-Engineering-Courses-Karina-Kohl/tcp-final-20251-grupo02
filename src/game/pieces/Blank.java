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

    public Blank(int x, int y, boolean isForEnPassantHighlight){
	super(x, y, '-');
    }

    public Blank(Pair p, boolean isForEnPassantHighlight){
	super(p.x, p.y, '-');
    }


    @Override
    public ArrayList<Pair> validMoviments(Board board, boolean testingCheck) {
        return this.getMoviments();
    }

}
