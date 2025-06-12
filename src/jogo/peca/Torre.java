package jogo.peca;
import misc.Pair;
import java.util.ArrayList;
import jogo.Jogada;

public class Torre extends Peca {
    
    public boolean jaMovido = false;
    
    public Torre(int x, int y, char id){
        super(x, y, id);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(){
        
        ArrayList<Pair> new_mov = new ArrayList<>();

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.grid_position.add(new Pair(0, - i));
            Pair baixo = this.grid_position.add(new Pair(0, + i));
            
            Pair direita = this.grid_position.add(new Pair(+ i, 0));
            Pair esquerda = this.grid_position.add(new Pair(- i, 0));
            
            if(cima.IsPieceInsideBoard(0, SIZE))
                new_mov.add(cima);
            if(baixo.IsPieceInsideBoard(0, SIZE) )
                new_mov.add(baixo);
            if(direita.IsPieceInsideBoard(0, SIZE))
                new_mov.add(direita);
            if(esquerda.IsPieceInsideBoard(0, SIZE))
                new_mov.add(esquerda);     
        }

        return new_mov;
    }

    @Override
    public void Mover(Jogada jogada){
        super.Mover(jogada);
        this.jaMovido = true;
    }

}
