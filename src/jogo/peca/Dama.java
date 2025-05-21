package jogo.peca;
import java.util.ArrayList;

public class Dama extends Peca {
    
    public Dama(int x, int y, String id){
        super(x, y, id);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        
        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.grid_position.add(new Pair(0, - i));
            Pair baixo = this.grid_position.add(new Pair(0, + i));
            
            Pair direita = this.grid_position.add(new Pair(+ i, 0));
            Pair esquerda = this.grid_position.add(new Pair(- i, 0));
            
            // diagonais
            Pair superior_direita = this.grid_position.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.grid_position.add(new Pair(- i, - i));

            Pair inferior_direita = this.grid_position.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.grid_position.add(new Pair(- i, + i));

            if(cima.IsPieceInsideBoard(0, SIZE))
                mov.add(cima);
            if(baixo.IsPieceInsideBoard(0, SIZE))
                mov.add(baixo);
            if(direita.IsPieceInsideBoard(0, SIZE))
                mov.add(direita);
            if(esquerda.IsPieceInsideBoard(0, SIZE))
                mov.add(esquerda);
            if(superior_direita.IsPieceInsideBoard(0, SIZE))
                mov.add(superior_direita);
            if(superior_esquerda.IsPieceInsideBoard(0, SIZE))
                mov.add(superior_esquerda);
            if(inferior_direita.IsPieceInsideBoard(0, SIZE))
                mov.add(inferior_direita);
            if(inferior_esquerda.IsPieceInsideBoard(0, SIZE))
                mov.add(inferior_esquerda);
        }

        return mov;
    };

}
