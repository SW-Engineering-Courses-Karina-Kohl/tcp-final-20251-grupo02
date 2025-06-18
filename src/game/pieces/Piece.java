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

    private char id;
    private Pair boardPosition;
    private ArrayList<Pair> moviments = new ArrayList<>();

    private Sprite sprite;

    public Piece(int x, int y, char id)
    {
        this.boardPosition = new Pair(x, y);
        this.id = id;
    }

    public abstract ArrayList<Pair> ValidMoviments(Board Board, boolean testingCheck);



    public ArrayList<Pair> GetMoviments(){
	return moviments;
    }

    public void SetMoviments(ArrayList<Pair> newMoviments){
	this.moviments = newMoviments;
    }

    public Pair GetBoardPosition(){
	return boardPosition;
    }

    public char GetPieceId(){
	return id;
    }

    public char GetColorPiece(){

	if(this instanceof Blank){
	    return '_';
	}
	if(Character.isLowerCase(this.GetPieceId())){
	    return 'b';
	}
	return 'w';

    }

    public void SetSprite(Sprite sprite){
	this.sprite = sprite;
    }

    public void MovePiece(Move move){
        this.boardPosition = move.capturedPiece.GetBoardPosition();
    }

    /* Add the moviment to the movs list only if this moviment doesn't lead to a check */
    public void CheckMoviment(Board board, ArrayList<Pair> movs, Pair moviment, boolean testingCheck){

	if(testingCheck){
	    if(!board.MoveLeadsToCheck(this, this.GetColorPiece(), moviment)){
		movs.add(moviment);
	    }
	} else {
	    movs.add(moviment);
	}

    }

    public void DrawPiece(int xInitial, int yInitial){
        if (sprite != null)
            sprite.DrawSpritePro(GetBoardPosition().x * sprite.GetWidth() + (sprite.GetWidth() / 2) + xInitial,
                                GetBoardPosition().y * sprite.GetHeight() + (sprite.GetHeight() / 2) + yInitial);
    }

    @Override
    public String toString() {
        return this.GetPieceId() + " " + this.GetBoardPosition();
    }

}
