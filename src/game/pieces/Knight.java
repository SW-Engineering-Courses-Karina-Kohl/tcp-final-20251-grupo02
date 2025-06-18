package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Knight extends Piece {

    private static Texture cavaloTexture = LoadTexture("res/pieces/cavalo.png");

    public Knight(int x, int y, char id){
        super(x, y, id);

        if (GetColorPiece() == 'w')
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetColorPiece();

        // L pra cima direita esquerda
        Pair cima_direita = this.GetBoardPosition().add(new Pair(+ 1, - 2));
        Pair cima_esquerda = this.GetBoardPosition().add(new Pair(- 1, - 2));

        // L pra baixo direita esquerda
        Pair baixo_direita = this.GetBoardPosition().add(new Pair(+ 1, + 2));
        Pair baixo_esquerda = this.GetBoardPosition().add(new Pair(- 1, + 2));

        // L pra direita cima baixo
        Pair direita_cima = this.GetBoardPosition().add(new Pair(+ 2, - 1));
        Pair direita_baixo = this.GetBoardPosition().add(new Pair(+ 2, + 1));

        // L pra esquerda cima baixo
        Pair esquerda_cima = this.GetBoardPosition().add(new Pair(- 2, - 1));
        Pair esquerda_baixo = this.GetBoardPosition().add(new Pair(- 2, + 1));

        if(cima_direita.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(cima_direita).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima_direita, testingCheck);

        if(cima_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(cima_esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima_esquerda, testingCheck);

        if(baixo_direita.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(baixo_direita).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo_direita, testingCheck);

        if(baixo_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(baixo_esquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo_esquerda, testingCheck);

        if(direita_cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(direita_cima).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, direita_cima, testingCheck);

        if(direita_baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(direita_baixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, direita_baixo, testingCheck);

        if(esquerda_cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(esquerda_cima).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda_cima, testingCheck);

        if(esquerda_baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceInPosition(esquerda_baixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda_baixo, testingCheck);

	if(testingCheck){
	    moviments = newMovimentos;
	}

        return newMovimentos;
    }

}
