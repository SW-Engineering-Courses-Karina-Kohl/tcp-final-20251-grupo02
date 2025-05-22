package jogo.peca;
import jogo.Jogada;
import java.util.ArrayList;

public abstract class Peca{
    
    // pair da posição das peças
    public static class Pair{
        public int x;
        public int y;
        
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    
        public Pair add(Pair other) {
            return new Pair(this.x + other.x, this.y + other.y);
        }    
    
        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    
        // feio mas funciona
        public boolean IsPieceInsideBoard(int inf, int sup) {
            return ((this.x >= inf && this.y >= inf) &&
                    (this.x < sup && this.y < sup));
        }
    
        @Override
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Peca.Pair other = (Peca.Pair) obj;
            
            return(this.x == other.x &&
                    this.y == other.y);
        }
    
    }
    
    public final int SIZE = 8;
    public float posicao;
    public Pair grid_position;
    public String identificador;
    
    public Peca(int x, int y, String id){
        this.grid_position = new Pair(x, y);
        this.identificador = id; 
    }
  
    public ArrayList<Pair> mov = new ArrayList<>();
    
    // retorna todos os movimentos que uma peça pode 
    // se movimentar, independente se a casa está ocupada ou não
    public abstract ArrayList<Pair> MovimentosValidos();
        
    public void Mover(Jogada jogada){
        this.grid_position = jogada.peca_capturada.grid_position;  
    };
    
    public void DestruirPeca(){

    };

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
