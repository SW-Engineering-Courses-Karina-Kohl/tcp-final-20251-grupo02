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
	if (findPieceColor() == 'w')
	    this.setSprite(new Sprite(rookTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.setSprite(new Sprite(rookTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char color = this.findPieceColor();

	boolean pieceUp = false;
	boolean pieceDown = false;
	boolean pieceRight = false;
	boolean pieceLeft = false;

        for(int i = 1; i < SIZE; i++) {

            Pair up = this.getBoardPosition().add(new Pair(0, - i));
            Pair down = this.getBoardPosition().add(new Pair(0, + i));

            Pair right = this.getBoardPosition().add(new Pair(+ i, 0));
            Pair left = this.getBoardPosition().add(new Pair(- i, 0));

	    if(!pieceUp && up.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(up)){
		    pieceUp = true;
		}
		if(color != board.GetPieceInPosition(up).findPieceColor()){
		    this.checkMovement(board, newMovimentos, up, testingCheck);
		}
	    }

	    if(!pieceDown && down.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(down)){
		    pieceDown = true;
		}
		if(color != board.GetPieceInPosition(down).findPieceColor()){
		    this.checkMovement(board, newMovimentos, down, testingCheck);
		}
	    }

	    if(!pieceRight && right.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(right)){
		    pieceRight = true;
		}
		if(color != board.GetPieceInPosition(right).findPieceColor()){
		    this.checkMovement(board, newMovimentos, right, testingCheck);
		}
	    }

	    if(!pieceLeft && left.IsPieceInsideBoard(0, SIZE)){
                if(board.IsTherePieceInPosition(left)){
		    pieceLeft = true;
		}
		if(color != board.GetPieceInPosition(left).findPieceColor()){
		    this.checkMovement(board, newMovimentos, left, testingCheck);
		}
	    }
        }

	if(testingCheck){
	    this.setMovements(newMovimentos);
	}

        return newMovimentos;
    }

    @Override
    public void movePiece(Move move){
        super.movePiece(move);
        this.hasMoved = true;
    }

}
