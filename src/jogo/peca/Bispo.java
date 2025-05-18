package jogo.peca;
import java.util.ArrayList;

public class Bispo extends Peca {
    
    private static final int SIZE = 8;

    
    public Bispo(int x, int y){
        super(x, y, "B");
    } 

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        
        for(int i = 1; i < 20; i++) {
            // diagonal superior direita e esquerda
            Pair a = this.grid_position.add(new Pair(+ i, - i));
            Pair b = this.grid_position.add(new Pair(- i, - i));

            // diagonal inferior direita e esquerda
            Pair c = this.grid_position.add(new Pair(+ i, + i));
            Pair d = this.grid_position.add(new Pair(- i, + i));
           
            mov.add(a);
            mov.add(b);
            mov.add(c);
            mov.add(d);
        }

        return mov;
    }

    @Override
    public void print_movimentos_validos(){
        for (Pair p : mov) {
           System.out.println(p);
        }
    }



}
