package jogo.peca;
import java.util.ArrayList;

public class Dama extends Peca {
    public Dama(int x, int y){
        super(x, y, "D");
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
            
            // diagonal superior direita e esquerda
            Pair e = this.grid_position.add(new Pair(+ i, + i));
            Pair f = this.grid_position.add(new Pair(- i, + i));

            // diagonal inferior direita e esquerda
            Pair g = this.grid_position.add(new Pair(+ i, - i));
            Pair h = this.grid_position.add(new Pair(- i, - i));


            mov.add(a);
            mov.add(b);
            mov.add(c);
            mov.add(d);
            mov.add(e);
            mov.add(f);
            mov.add(g);
            mov.add(h);
        }



        return mov;
    };

}
