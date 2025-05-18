package jogo.peca;
import java.util.ArrayList;

public class Rei extends Peca{
    public Rei(int x, int y){
        super(x, y, "R");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        // cima e baixo
        Pair a = this.grid_position.add(new Pair(0, - 1));
        Pair b = this.grid_position.add(new Pair(0, + 1));
        
        // direita e esquerda
        Pair c = this.grid_position.add(new Pair(+ 1, 0));
        Pair d = this.grid_position.add(new Pair(- 1, 0));
        
        // diagonal superior direita e esquerda
        Pair e = this.grid_position.add(new Pair(+ 1, - 1));
        Pair f = this.grid_position.add(new Pair(- 1, - 1));
        
        // diagonal inferior direita e esquerda
        Pair g = this.grid_position.add(new Pair(+ 1, + 1));
        Pair h = this.grid_position.add(new Pair(- 1, + 1));
        
        
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
