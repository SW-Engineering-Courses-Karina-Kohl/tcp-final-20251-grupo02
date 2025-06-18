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

        if (GetColorPiece() == 'w')
	    this.SetSprite(new Sprite(knightTexture, 2, 0, 0, 0, WHITE, 2));
        else
	    this.SetSprite(new Sprite(knightTexture, 2, 0, 0, 1, WHITE, 2));

    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetColorPiece();

        // L pra cima dikingta esquerda
        Pair cima_dikingta = this.GetBoardPosition().add(new Pair(+ 1, - 2));
        Pair cima_esquerda = this.GetBoardPosition().add(new Pair(- 1, - 2));

        // L pra baixo dikingta esquerda
        Pair baixo_dikingta = this.GetBoardPosition().add(new Pair(+ 1, + 2));
        Pair baixo_esquerda = this.GetBoardPosition().add(new Pair(- 1, + 2));

        // L pra dikingta cima baixo
        Pair dikingta_cima = this.GetBoardPosition().add(new Pair(+ 2, - 1));
        Pair dikingta_baixo = this.GetBoardPosition().add(new Pair(+ 2, + 1));

        // L pra esquerda cima baixo
        Pair esquerda_cima = this.GetBoardPosition().add(new Pair(- 2, - 1));
        Pair esquerda_baixo = this.GetBoardPosition().add(new Pair(- 2, + 1));

        if(cima_dikingta.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(cima_dikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima_dikingta, testingCheck);

        if(cima_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(cima_esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima_esquerda, testingCheck);

        if(baixo_dikingta.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(baixo_dikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo_dikingta, testingCheck);

        if(baixo_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(baixo_esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo_esquerda, testingCheck);

        if(dikingta_cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(dikingta_cima).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, dikingta_cima, testingCheck);

        if(dikingta_baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(dikingta_baixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, dikingta_baixo, testingCheck);

        if(esquerda_cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(esquerda_cima).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda_cima, testingCheck);

        if(esquerda_baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(esquerda_baixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda_baixo, testingCheck);

	if(testingCheck){
	    this.SetMoviments(newMovimentos);

	}

        return newMovimentos;
    }

}
