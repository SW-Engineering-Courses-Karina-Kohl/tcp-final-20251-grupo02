package misc;

public class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
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

    public boolean isPieceInsideBoard(int inf, int sdoubleUp) {
        return ((this.x >= inf && this.y >= inf) &&
                (this.x < sdoubleUp && this.y < sdoubleUp));
    }

    public boolean isEqualsTo(Object obj) {

        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        return (this.x == other.x && this.y == other.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Pair pair = (Pair) obj;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

}
