package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Move;

public class Rook extends Piece {

    public boolean hasMoved = false;
    private static Texture rookTexture = LoadTexture("res/pieces/rook.png");

    public Rook(int x, int y, char id){
        super(x, y, id);
	this.LoadSprite();
    }

    public Rook(Pair p, char id){
        super(p.x, p.y, id);
	this.LoadSprite();
    }

    private void LoadSprite(){
	if (GetPieceColor() == 'w')
	    this.SetSprite(new Sprite(rookTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(rookTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char color = this.GetPieceColor();

	boolean pieceUp = false;
	boolean pieceDown = false;
	boolean pieceRight = false;
	boolean pieceLeft = false;

        for(int i = 1; i < SIZE; i++) {

            Pair up = this.GetBoardPosition().add(new Pair(0, - i));
            Pair down = this.GetBoardPosition().add(new Pair(0, + i));

            Pair right = this.GetBoardPosition().add(new Pair(+ i, 0));
            Pair left = this.GetBoardPosition().add(new Pair(- i, 0));

	    if(!pieceUp && up.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(up)){
		    pieceUp = true;
		}
		if(color != board.GetPieceInPosition(up).GetPieceColor()){
		    this.CheckMoviment(board, newMovimentos, up, testingCheck);
		}
	    }

	    if(!pieceDown && down.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(down)){
		    pieceDown = true;
		}
		if(color != board.GetPieceInPosition(down).GetPieceColor()){
		    this.CheckMoviment(board, newMovimentos, down, testingCheck);
		}
	    }

	    if(!pieceRight && right.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(right)){
		    pieceRight = true;
		}
		if(color != board.GetPieceInPosition(right).GetPieceColor()){
		    this.CheckMoviment(board, newMovimentos, right, testingCheck);
		}
	    }

	    if(!pieceLeft && left.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(left)){
		    pieceLeft = true;
		}
		if(color != board.GetPieceInPosition(left).GetPieceColor()){
		    this.CheckMoviment(board, newMovimentos, left, testingCheck);
		}
	    }
        }

	if(testingCheck){
	    this.SetMoviments(newMovimentos);
	}

        return newMovimentos;
    }

    @Override
    public void MovePiece(Move move){
        super.MovePiece(move);
        this.hasMoved = true;
    }

}
