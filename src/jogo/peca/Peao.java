package jogo.peca;
import java.util.ArrayList;

public class Peao extends Peca {
    
    boolean jaMovido = false;

    public Peao(int x, int y){
        super(x, y, "P");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        Pair cima = this.grid_position.add(new Pair(0, - 1)); 
        mov.add(cima);
        return mov;
    }

    @Override
    public void print_movimentos_validos(){
        for (Pair p : mov) {
           System.out.println(p);
        }
    }

}
