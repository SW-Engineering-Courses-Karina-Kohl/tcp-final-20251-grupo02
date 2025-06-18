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
    public float posicao;
    public Pair boardPosition;
    public char identificador;

    public Sprite sprite;

    public Piece(int x, int y, char id)
    {
        this.boardPosition = new Pair(x, y);
        this.identificador = id;
    }

    public ArrayList<Pair> movimentos = new ArrayList<>();

    // retorna todos os movimentos que uma peça pode
    // se movimentar, independente se a casa está ocupada ou não
    public abstract ArrayList<Pair> ValidMoviments(Board Board, boolean emCheque);

    public ArrayList<Pair> GetMovimentos(){
	return movimentos;
    }

    public char GetPieceId(){
	return identificador;
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

    public void Mover(Move move)
    {
        this.boardPosition = move.capturedPiece.boardPosition;
    }

    public void DestruirPiece()
    {

    }

    public char GetColorPiece(){
	if(this instanceof Blank){
	    return '_';
	}
	if(Character.isLowerCase(this.identificador)){
	    return 'p';
	}
	return 'b';
    }

    public void print_movimentos_validos()
    {
        // this.ValidMoviments(board); //
        for (Pair p : movimentos) {
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
        return this.identificador + " " + this.boardPosition;
    }

}
