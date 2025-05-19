package jogo.peca;
import java.util.ArrayList;

public class Dama extends Peca {
    public Dama(int x, int y){
        super(x, y, "D");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        for(int i = 1; i < 8; i++) {
            Pair cima = this.grid_position.add(new Pair(0, - i));
            Pair baixo = this.grid_position.add(new Pair(0, + i));
            
            Pair direita = this.grid_position.add(new Pair(+ i, 0));
            Pair esquerda = this.grid_position.add(new Pair(- i, 0));
            
            // diagonais
            Pair superior_direita = this.grid_position.add(new Pair(+ i, + i));
            Pair superior_esquerda = this.grid_position.add(new Pair(- i, + i));

            Pair inferior_direita = this.grid_position.add(new Pair(+ i, - i));
            Pair inferior_esquerda = this.grid_position.add(new Pair(- i, - i));

            
            mov.add(cima);
            mov.add(baixo);
            mov.add(direita);
            mov.add(esquerda);
            mov.add(superior_direita);
            mov.add(superior_esquerda);
            mov.add(inferior_direita);
            mov.add(inferior_esquerda);
        }



        return mov;
    };

}
