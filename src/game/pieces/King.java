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

    public boolean jaMovido = false;
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
	char cor = this.GetColorPiece();

        Pair cima = this.GetBoardPosition().add(new Pair(0, - 1));
        Pair baixo = this.GetBoardPosition().add(new Pair(0, + 1));

        Pair dikingta = this.GetBoardPosition().add(new Pair(+ 1, 0));
        Pair esquerda = this.GetBoardPosition().add(new Pair(- 1, 0));

        // diagonais
        Pair superior_dikingta = this.GetBoardPosition().add(new Pair(+ 1, - 1));
        Pair superior_esquerda = this.GetBoardPosition().add(new Pair(- 1, - 1));

        Pair inferior_dikingta = this.GetBoardPosition().add(new Pair(+ 1, + 1));
        Pair inferior_esquerda = this.GetBoardPosition().add(new Pair(- 1, + 1));

        if(cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(cima).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima, testingCheck);

        if(baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(baixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);

        if(dikingta.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(dikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, dikingta, testingCheck);

        if(esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);

        if(superior_dikingta.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(superior_dikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, superior_dikingta, testingCheck);

        if(superior_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(superior_esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, superior_esquerda, testingCheck);

        if(inferior_dikingta.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(inferior_dikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, inferior_dikingta, testingCheck);

        if(inferior_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(inferior_esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, inferior_esquerda, testingCheck);


	if(testingCheck){
	    this.SetMoviments(newMovimentos);
	}

        return newMovimentos;
    }

    @Override
    public void MovePiece(Move move){
        super.MovePiece(move);
        this.jaMovido = true;
    }

}
