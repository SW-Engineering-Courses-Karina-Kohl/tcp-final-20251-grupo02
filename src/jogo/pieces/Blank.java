package jogo.pieces;
import jogo.Tabuleiro;
import java.util.ArrayList;

import misc.Pair;

public class Blank extends Piece{

    public Blank(int x, int y){
        super(x, y, '_');
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro, boolean testingCheck){
        return movimentos;
    }

}
