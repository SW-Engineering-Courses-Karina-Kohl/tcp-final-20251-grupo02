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

	if (GetColorPiece() == 'w')
	    this.SetSprite(new Sprite(kingTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(kingTexture, 2, 0, 0, 1, WHITE, 2));
    }

    public King(Pair p, char id){
        super(p.x, p.y, id);

        if (GetColorPiece() == 'w')
	    this.SetSprite(new Sprite(kingTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(kingTexture, 2, 0, 0, 1, WHITE, 2));
    }


    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char color = this.GetColorPiece();

        Pair up = this.GetBoardPosition().add(new Pair(0, - 1));
        Pair down = this.GetBoardPosition().add(new Pair(0, + 1));
        Pair right = this.GetBoardPosition().add(new Pair(+ 1, 0));
        Pair left = this.GetBoardPosition().add(new Pair(- 1, 0));
        Pair upperRight = this.GetBoardPosition().add(new Pair(+ 1, - 1));
        Pair upperLeft = this.GetBoardPosition().add(new Pair(- 1, - 1));
        Pair lowerRight = this.GetBoardPosition().add(new Pair(+ 1, + 1));
        Pair lowerLeft = this.GetBoardPosition().add(new Pair(- 1, + 1));

        if(up.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(up).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, up, testingCheck);

        if(down.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(down).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, down, testingCheck);

        if(right.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(right).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, right, testingCheck);

        if(left.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(left).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, left, testingCheck);

        if(upperRight.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upperRight).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, upperRight, testingCheck);

        if(upperLeft.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upperLeft).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, upperLeft, testingCheck);

        if(lowerRight.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(lowerRight).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, lowerRight, testingCheck);

        if(lowerLeft.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(lowerLeft).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, lowerLeft, testingCheck);


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
