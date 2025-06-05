package jogo.peca;
import jogo.Jogada;
import misc.Pair;
import java.util.ArrayList;

public abstract class Peca{
    
    public final int SIZE = 8;
    public float posicao;
    public Pair grid_position;
    public char identificador;
    
    public Peca(int x, int y, char id){
        this.grid_position = new Pair(x, y);
        this.identificador = id; 
    }
  
    public ArrayList<Pair> mov = new ArrayList<>();
    
    // retorna todos os movimentos que uma peça pode 
    // se movimentar, independente se a casa está ocupada ou não
    public abstract ArrayList<Pair> MovimentosValidos();
        
    public void Mover(Jogada jogada){
        this.grid_position = jogada.peca_capturada.grid_position;  
    }
    
    public void DestruirPeca(){

    }

    public void print_movimentos_validos(){
        this.MovimentosValidos();
        for (Pair p : mov) {
           System.out.println(p);
        }
    }

    @Override
    public String toString() {
        return this.identificador + " " + this.grid_position;
    }

}
