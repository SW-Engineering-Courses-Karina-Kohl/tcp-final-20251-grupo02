package jogo.peca;
import jogo.Jogada;
import misc.Pair;
import java.util.ArrayList;
import gui.*;

public abstract class Peca{
    
    public final int SIZE = 8;
    public float posicao;
    public Pair grid_position;
    public char identificador;

    public Sprite sprite;
    
    public Peca(int x, int y, char id)
    {
        this.grid_position = new Pair(x, y);
        this.identificador = id; 
    }
  
    public ArrayList<Pair> new_mov = new ArrayList<>();
    
    // retorna todos os movimentos que uma peça pode 
    // se movimentar, independente se a casa está ocupada ou não
    public abstract ArrayList<Pair> MovimentosValidos();
        
    public void Mover(Jogada jogada){
        this.grid_position = jogada.peca_capturada.grid_position;  
    }
    
    public void DestruirPeca(){

    }

    /*
    public void print_movimentos_validos(){
        this.MovimentosValidos();
        for (Pair p : new_mov) {
           System.out.println(p);
        }
    }
     */

    public void DrawPeca(int xInicial, int yInicial)
    {
        if (sprite != null)
            sprite.DrawSpritePro(grid_position.x * sprite.GetWidth() + (sprite.GetWidth() / 2) + xInicial, 
                                grid_position.y * sprite.GetHeight() + (sprite.GetHeight() / 2) + yInicial);
    }

    @Override
    public String toString() {
        return this.identificador + " " + this.grid_position;
    }

}
