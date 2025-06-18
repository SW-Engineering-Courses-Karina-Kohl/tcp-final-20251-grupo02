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
	if (GetPieceColor() == 'w')
	    this.SetSprite(new Sprite(knightTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(knightTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char color = this.GetPieceColor();

        Pair upRight = this.GetBoardPosition().add(new Pair(+ 1, - 2));
        Pair upLeft = this.GetBoardPosition().add(new Pair(- 1, - 2));

        Pair downRight = this.GetBoardPosition().add(new Pair(+ 1, + 2));
        Pair downLeft = this.GetBoardPosition().add(new Pair(- 1, + 2));

        Pair rightUp = this.GetBoardPosition().add(new Pair(+ 2, - 1));
        Pair rightDown = this.GetBoardPosition().add(new Pair(+ 2, + 1));

        Pair leftUp = this.GetBoardPosition().add(new Pair(- 2, - 1));
        Pair leftDown = this.GetBoardPosition().add(new Pair(- 2, + 1));

        if(upRight.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upRight).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, upRight, testingCheck);

        if(upLeft.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upLeft).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, upLeft, testingCheck);

        if(downRight.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(downRight).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, downRight, testingCheck);

        if(downLeft.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(downLeft).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, downLeft, testingCheck);

        if(rightUp.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(rightUp).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, rightUp, testingCheck);

        if(rightDown.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(rightDown).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, rightDown, testingCheck);

        if(leftUp.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(leftUp).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, leftUp, testingCheck);

        if(leftDown.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(leftDown).GetPieceColor())
	    this.CheckMoviment(board, newMovimentos, leftDown, testingCheck);

	if(testingCheck){
	    this.SetMoviments(newMovimentos);
	}

        return newMovimentos;
    }

}
