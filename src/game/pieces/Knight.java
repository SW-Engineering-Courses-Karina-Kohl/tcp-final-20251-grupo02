package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Knight extends Piece {

    private static Texture knightTexture = LoadTexture("res/pieces/knight.png");

    public Knight(int x, int y, char id){
        super(x, y, id);
	this.LoadSprite();
    }

    public Knight(Pair p, char id){
        super(p.x, p.y, id);
	this.LoadSprite();
    }

    private void LoadSprite(){
	if (findPieceColor() == 'w')
	    this.setSprite(new Sprite(knightTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.setSprite(new Sprite(knightTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char color = this.findPieceColor();

        Pair upRight = this.getBoardPosition().add(new Pair(+ 1, - 2));
        Pair upLeft = this.getBoardPosition().add(new Pair(- 1, - 2));

        Pair downRight = this.getBoardPosition().add(new Pair(+ 1, + 2));
        Pair downLeft = this.getBoardPosition().add(new Pair(- 1, + 2));

        Pair rightUp = this.getBoardPosition().add(new Pair(+ 2, - 1));
        Pair rightDown = this.getBoardPosition().add(new Pair(+ 2, + 1));

        Pair leftUp = this.getBoardPosition().add(new Pair(- 2, - 1));
        Pair leftDown = this.getBoardPosition().add(new Pair(- 2, + 1));

        if(upRight.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upRight).findPieceColor())
	    this.checkMovement(board, newMovimentos, upRight, testingCheck);

        if(upLeft.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upLeft).findPieceColor())
	    this.checkMovement(board, newMovimentos, upLeft, testingCheck);

        if(downRight.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(downRight).findPieceColor())
	    this.checkMovement(board, newMovimentos, downRight, testingCheck);

        if(downLeft.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(downLeft).findPieceColor())
	    this.checkMovement(board, newMovimentos, downLeft, testingCheck);

        if(rightUp.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(rightUp).findPieceColor())
	    this.checkMovement(board, newMovimentos, rightUp, testingCheck);

        if(rightDown.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(rightDown).findPieceColor())
	    this.checkMovement(board, newMovimentos, rightDown, testingCheck);

        if(leftUp.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(leftUp).findPieceColor())
	    this.checkMovement(board, newMovimentos, leftUp, testingCheck);

        if(leftDown.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(leftDown).findPieceColor())
	    this.checkMovement(board, newMovimentos, leftDown, testingCheck);

	if(testingCheck){
	    this.setMovements(newMovimentos);
	}

        return newMovimentos;
    }

}
