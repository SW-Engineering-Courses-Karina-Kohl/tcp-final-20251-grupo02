package jogo.peca;
import java.util.ArrayList;

public class Torre extends Peca {
    boolean jaMovido = false;
    
    public Torre(int x, int y){
        super(x, y, "T");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        for(int i = 1; i < 20; i++) {
            Pair cima = this.grid_position.add(new Pair(0, - i));
            Pair baixo = this.grid_position.add(new Pair(0, + i));
            
            Pair direita = this.grid_position.add(new Pair(+ i, 0));
            Pair esquerda = this.grid_position.add(new Pair(- i, 0));
            
            mov.add(cima);
            mov.add(baixo);
            mov.add(direita);
            mov.add(esquerda);
        }
        return mov;
    }

}
