package jogo.peca;
import java.util.ArrayList;

public class Rei extends Peca{
    
    boolean jaMovido = false;
    
    public Rei(int x, int y){
        super(x, y, "R");
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        Pair cima = this.grid_position.add(new Pair(0, - 1));
        Pair baixo = this.grid_position.add(new Pair(0, + 1));
        
        Pair direita = this.grid_position.add(new Pair(+ 1, 0));
        Pair esquerda = this.grid_position.add(new Pair(- 1, 0));
        
        // diagonais
        Pair superior_direita = this.grid_position.add(new Pair(+ 1, - 1));
        Pair superior_esquerda = this.grid_position.add(new Pair(- 1, - 1));
        
        Pair inferior_direita = this.grid_position.add(new Pair(+ 1, + 1));
        Pair inferior_esquerda = this.grid_position.add(new Pair(- 1, + 1));
        
        mov.add(cima);
        mov.add(baixo);
        mov.add(direita);
        mov.add(esquerda);
        mov.add(superior_direita);
        mov.add(superior_esquerda);
        mov.add(inferior_direita);
        mov.add(inferior_esquerda);
        
        return mov;
    }
}
