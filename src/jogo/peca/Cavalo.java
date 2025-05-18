package jogo.peca;
import java.util.ArrayList;

public class Cavalo extends Peca {
    public Cavalo(int x, int y){
        super(x, y, "C");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        // L pra cima direita e esquerda
        Pair a = this.grid_position.add(new Pair(+ 1, - 2));
        Pair b = this.grid_position.add(new Pair(- 1, - 2));
        
        // L pra baixo direita e esquerda
        Pair c = this.grid_position.add(new Pair(+ 1, + 2));
        Pair d = this.grid_position.add(new Pair(- 1, + 2));
        
        // L pra direita cima e baixo
        Pair e = this.grid_position.add(new Pair(+ 2, - 1));
        Pair f = this.grid_position.add(new Pair(+ 2, + 1));
        
        // L pra esquerda cima e baixo
        Pair g = this.grid_position.add(new Pair(- 2, - 1));
        Pair h = this.grid_position.add(new Pair(- 2, + 1));
        
        mov.add(a);
        mov.add(b);
        mov.add(c);
        mov.add(d);
        mov.add(e);
        mov.add(f);
        mov.add(g);
        mov.add(h);
        
        return mov;
    }

}
