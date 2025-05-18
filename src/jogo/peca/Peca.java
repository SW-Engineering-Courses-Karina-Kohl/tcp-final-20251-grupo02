package jogo.peca;
import java.util.ArrayList;

public abstract class Peca {
    
    public class Pair{
        public int x;
        public int y;
        
        public Pair(int fst, int snd){
            this.x = fst;
            this.y = snd;
        }
    
        public Pair add(Pair other) {
            return new Pair(this.x + other.x, this.y + other.y);
        }    
    
        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }
    
    //Float posicao;
    
    public Pair grid_position;
    public String identificador;
    
    
    public Peca(int x, int y, String id){
        this.grid_position = new Pair(x, y);
        this.identificador = id; 
    }
  
    public ArrayList<Pair> mov = new ArrayList<>();
    public abstract ArrayList<Pair> MovimentosValidos();
        
    public void DestruirPeca(){

    };

    public void print_movimentos_validos(){
        for (Pair p : mov) {
           System.out.println(p);
        }
    }
}
