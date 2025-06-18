package game.pieces;
import game.Board;
import java.util.ArrayList;

import misc.Pair;

public class Blank extends Piece{

    public Blank(int x, int y){
        super(x, y, '_');
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Board board, boolean testingCheck){
        return movimentos;
    }

}
