package jogo.peca;
import jogo.Tabuleiro;
import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import static com.raylib.Raylib.LoadTexture;
import misc.Pair;

public class Blank extends Peca{

    private static Texture textura = LoadTexture("");

    public Blank(int x, int y){
        super(x, y, '_', textura);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){
        return movimentos;
    }

}
