package jogo.peca;
import java.util.ArrayList;

public class Bispo extends Peca {
    
    public Bispo(int x, int y, String id){
        super(x, y, id);
    } 

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        
        for(int i = 1; i < SIZE; i++) {
            // diagonais
            Pair superior_direita = this.grid_position.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.grid_position.add(new Pair(- i, - i));

            Pair inferior_direita = this.grid_position.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.grid_position.add(new Pair(- i, + i));
             
            if(superior_direita.IsPieceInsideBoard(0, SIZE))
                mov.add(superior_direita);
            if(superior_esquerda.IsPieceInsideBoard(0, SIZE) )
                mov.add(superior_esquerda);
            if(inferior_direita.IsPieceInsideBoard(0, SIZE))
                mov.add(inferior_direita);
            if(inferior_esquerda.IsPieceInsideBoard(0, SIZE))
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
