package jogo.peca;
import jogo.Tabuleiro;
import java.util.ArrayList;

import misc.Pair;

public class Blank extends Peca{

    public Blank(int x, int y){
        super(x, y, '_');
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){
        return mov;
    }

}
