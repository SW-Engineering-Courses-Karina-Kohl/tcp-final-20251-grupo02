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

        Pair doubleUp = this.GetBoardPosition().add(new Pair(0, - 1));
        Pair baixo = this.GetBoardPosition().add(new Pair(0, + 1));

        Pair dikingta = this.GetBoardPosition().add(new Pair(+ 1, 0));
        Pair esquerda = this.GetBoardPosition().add(new Pair(- 1, 0));

        // diagonais
        Pair upperRight = this.GetBoardPosition().add(new Pair(+ 1, - 1));
        Pair upperLeft = this.GetBoardPosition().add(new Pair(- 1, - 1));

        Pair inferiordikingta = this.GetBoardPosition().add(new Pair(+ 1, + 1));
        Pair inferioresquerda = this.GetBoardPosition().add(new Pair(- 1, + 1));

        if(doubleUp.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(doubleUp).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, doubleUp, testingCheck);

        if(baixo.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(baixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);

        if(dikingta.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(dikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, dikingta, testingCheck);

        if(esquerda.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);

        if(upperRight.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upperRight).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, upperRight, testingCheck);

        if(upperLeft.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(upperLeft).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, upperLeft, testingCheck);

        if(inferiordikingta.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(inferiordikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, inferiordikingta, testingCheck);

        if(inferioresquerda.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(inferioresquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, inferioresquerda, testingCheck);


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
