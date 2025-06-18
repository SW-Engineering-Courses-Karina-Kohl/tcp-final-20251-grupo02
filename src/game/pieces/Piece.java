package game.pieces;
import game.Jogada;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.*;

public abstract class Piece{

    public final int SIZE = 8;
    public float posicao;
    public Pair posicaoBoard;
    public char identificador;

    public Sprite sprite;

    public Piece(int x, int y, char id)
    {
        this.posicaoBoard = new Pair(x, y);
        this.identificador = id;
    }

    public ArrayList<Pair> movimentos = new ArrayList<>();

    // retorna todos os movimentos que uma peça pode
    // se movimentar, independente se a casa está ocupada ou não
    public abstract ArrayList<Pair> MovimentosValidos(Board Board, boolean emCheque);

    public ArrayList<Pair> GetMovimentos(){
	return movimentos;
    }

    public void CheckMoviment(Board tabuleiro, ArrayList<Pair> movs, Pair moviment, boolean testingCheck){

	if(testingCheck){
	    if(!tabuleiro.MoveLeadsToCheck(this, this.GetOurColorPiece(), moviment)){
		movs.add(moviment);
	    }
	} else {
	    movs.add(moviment);
	}
    }

    public void Mover(Jogada jogada)
    {
        this.posicaoBoard = jogada.peca_capturada.posicaoBoard;
    }

    public void DestruirPiece()
    {

    }

    public char GetOurColorPiece(){
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
        // this.MovimentosValidos(tabuleiro); //
        for (Pair p : movimentos) {
           System.out.println(p);
        }
    }

    public void DrawPiece(int xInicial, int yInicial)
    {
        if (sprite != null)
            sprite.DrawSpritePro(posicaoBoard.x * sprite.GetWidth() + (sprite.GetWidth() / 2) + xInicial,
                                posicaoBoard.y * sprite.GetHeight() + (sprite.GetHeight() / 2) + yInicial);
    }

    @Override
    public String toString() {
        return this.identificador + " " + this.posicaoBoard;
    }

}
