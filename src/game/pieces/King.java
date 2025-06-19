package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Move;

public class King extends Piece{

    public boolean hasMoved = false;
    private static Texture kingTexture = LoadTexture("res/pieces/king.png");

    public King(int x, int y, char id){
        super(x, y, id);
	this.LoadSprite();
    }

    public King(Pair p, char id){
        super(p.x, p.y, id);
	this.LoadSprite();
    }

    private void LoadSprite(){
	if (findPieceColor() == 'w')
	    this.setSprite(new Sprite(kingTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.setSprite(new Sprite(kingTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char color = this.findPieceColor();

        Pair up = this.getBoardPosition().add(new Pair(0, - 1));
        Pair down = this.getBoardPosition().add(new Pair(0, + 1));
        Pair right = this.getBoardPosition().add(new Pair(+ 1, 0));
        Pair left = this.getBoardPosition().add(new Pair(- 1, 0));
        Pair upperRight = this.getBoardPosition().add(new Pair(+ 1, - 1));
        Pair upperLeft = this.getBoardPosition().add(new Pair(- 1, - 1));
        Pair lowerRight = this.getBoardPosition().add(new Pair(+ 1, + 1));
        Pair lowerLeft = this.getBoardPosition().add(new Pair(- 1, + 1));

        if(up.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(up).findPieceColor())
	    this.checkMovement(board, newMovimentos, up, testingCheck);

        if(down.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(down).findPieceColor())
	    this.checkMovement(board, newMovimentos, down, testingCheck);

        if(right.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(right).findPieceColor())
	    this.checkMovement(board, newMovimentos, right, testingCheck);

        if(left.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(left).findPieceColor())
	    this.checkMovement(board, newMovimentos, left, testingCheck);

        if(upperRight.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(upperRight).findPieceColor())
	    this.checkMovement(board, newMovimentos, upperRight, testingCheck);

        if(upperLeft.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(upperLeft).findPieceColor())
	    this.checkMovement(board, newMovimentos, upperLeft, testingCheck);

        if(lowerRight.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(lowerRight).findPieceColor())
	    this.checkMovement(board, newMovimentos, lowerRight, testingCheck);

        if(lowerLeft.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(lowerLeft).findPieceColor())
	    this.checkMovement(board, newMovimentos, lowerLeft, testingCheck);


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
