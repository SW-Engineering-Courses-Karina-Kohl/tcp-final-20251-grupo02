package game.pieces;
import game.Move;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.*;

public abstract class Piece{

    public final int SIZE = 8;

    public char id;
    public float position;
    public Pair boardPosition;
    public ArrayList<Pair> moviments = new ArrayList<>();

    public Sprite sprite;

    public Piece(int x, int y, char id)
    {
        this.boardPosition = new Pair(x, y);
        this.id = id;
    }


    public abstract ArrayList<Pair> ValidMoviments(Board Board, boolean emCheque);

    public ArrayList<Pair> GetMoviments(){
	return moviments;
    }

    public char GetPieceId(){
	return id;
    }

    public void CheckMoviment(Board board, ArrayList<Pair> movs, Pair moviment, boolean testingCheck){

	if(testingCheck){
	    if(!board.MoveLeadsToCheck(this, this.GetColorPiece(), moviment)){
		movs.add(moviment);
	    }
	} else {
	    movs.add(moviment);
	}
    }

    public void MovePiece(Move move)
    {
        this.boardPosition = move.capturedPiece.boardPosition;
    }

    public char GetColorPiece(){
	if(this instanceof Blank){
	    return '_';
	}
	if(Character.isLowerCase(this.id)){
	    return 'b';
	}
	return 'w';
    }

    public void print_moviments_validos()
    {
        // this.ValidMoviments(board); //
        for (Pair p : moviments) {
           System.out.println(p);
        }
    }

    public void DrawPiece(int xInicial, int yInicial)
    {
        if (sprite != null)
            sprite.DrawSpritePro(boardPosition.x * sprite.GetWidth() + (sprite.GetWidth() / 2) + xInicial,
                                boardPosition.y * sprite.GetHeight() + (sprite.GetHeight() / 2) + yInicial);
    }

    @Override
    public String toString() {
        return this.id + " " + this.boardPosition;
    }

}
