package jogo.peca;
import java.util.ArrayList;

public class Torre extends Peca {
    public Torre(int x, int y){
        super(x, y, "T");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        for(int i = 1; i < 20; i++) {
            // cima e baixo
            Pair a = this.grid_position.add(new Pair(0, - i));
            Pair b = this.grid_position.add(new Pair(0, + i));
            
            // direita e esquerda
            Pair c = this.grid_position.add(new Pair(+ i, 0));
            Pair d = this.grid_position.add(new Pair(- i, 0));
            
            mov.add(a);
            mov.add(b);
            mov.add(c);
            mov.add(d);
        }
        return mov;
    }

}
