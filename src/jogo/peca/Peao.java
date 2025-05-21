package jogo.peca;
import java.util.ArrayList;

public class Peao extends Peca {
    
    boolean jaMovido = false;

    public Peao(int x, int y, String id){
        super(x, y, id);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        
        Pair cima = this.grid_position.add(new Pair(0, - 1)); 
        
        // diagonais superiores
        Pair superior_direita = this.grid_position.add(new Pair(+ 1, - 1)); 
        Pair superior_esquerda = this.grid_position.add(new Pair(- 1, - 1)); 
        
        if(cima.IsPieceInsideBoard(0, SIZE))
            mov.add(cima);
        if(superior_direita.IsPieceInsideBoard(0, SIZE))
            mov.add(superior_direita);
        if(superior_esquerda.IsPieceInsideBoard(0, SIZE))
            mov.add(superior_esquerda);
        
        return mov;
    }

}
