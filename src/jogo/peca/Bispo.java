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
            // diagonais
            Pair superior_direita = this.grid_position.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.grid_position.add(new Pair(- i, - i));

            Pair inferior_direita = this.grid_position.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.grid_position.add(new Pair(- i, + i));
           
            mov.add(superior_direita);
            mov.add(superior_esquerda);
            mov.add(inferior_direita);
            mov.add(inferior_esquerda);
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
