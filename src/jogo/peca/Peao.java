package jogo.peca;
import java.util.ArrayList;

public class Peao extends Peca {
    public ArrayList<Pair> mov = new ArrayList<>();
    
    public Peao(int x, int y){
        super(x, y, "P");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        mov.add(this.grid_position.add(new Pair(0, - 1))); 
        return mov;
    }

    @Override
    public void print_movimentos_validos(){
        for (Pair p : mov) {
           System.out.println(p);
        }
    }

}
