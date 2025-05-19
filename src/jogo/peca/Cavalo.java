package jogo.peca;
import java.util.ArrayList;

public class Cavalo extends Peca {
    public Cavalo(int x, int y){
        super(x, y, "C");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        // L pra cima direita esquerda
        Pair cima_direita = this.grid_position.add(new Pair(+ 1, - 2));
        Pair cima_esquerda = this.grid_position.add(new Pair(- 1, - 2));
        
        // L pra baixo direita esquerda
        Pair baixo_direita = this.grid_position.add(new Pair(+ 1, + 2));
        Pair baixo_esquerda = this.grid_position.add(new Pair(- 1, + 2));
        
        // L pra direita cima baixo
        Pair direita_cima = this.grid_position.add(new Pair(+ 2, - 1));
        Pair direita_baixo = this.grid_position.add(new Pair(+ 2, + 1));
        
        // L pra esquerda cima baixo
        Pair esquerda_cima = this.grid_position.add(new Pair(- 2, - 1));
        Pair esquerda_baixo = this.grid_position.add(new Pair(- 2, + 1));
        
        mov.add(cima_direita);
        mov.add(cima_esquerda);
        mov.add(baixo_direita);
        mov.add(baixo_esquerda
);
        mov.add(direita_cima);
        mov.add(direita_baixo);
        mov.add(esquerda_cima);
        mov.add(esquerda_baixo);
        
        return mov;
    }

}
