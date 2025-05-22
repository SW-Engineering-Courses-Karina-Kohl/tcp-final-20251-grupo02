package jogo.peca;
import java.util.ArrayList;
import jogo.Jogada;

public class Rei extends Peca{
    
    public boolean jaMovido = false;
    
    public Rei(int x, int y, String id){
        super(x, y, id);
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
  
        return mov;
    }

    @Override
    public void Mover(Jogada jogada){
        super.Mover(jogada);
        this.jaMovido = true;
    }

}
