package misc;

// pair da posição das peças
public class Pair{
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

    public boolean IsPieceInsideBoard(int inf, int sup) {
        return ((this.x >= inf && this.y >= inf) &&
            (this.x < sup && this.y < sup));
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair other = (Pair) obj;
        return(this.x == other.x &&
            this.y == other.y);
    }
}
