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
	char color = this.GetColorPiece();

        // L pra doubleUp dikingta esquerda
        Pair doubleUpdikingta = this.GetBoardPosition().add(new Pair(+ 1, - 2));
        Pair doubleUpesquerda = this.GetBoardPosition().add(new Pair(- 1, - 2));

        // L pra baixo dikingta esquerda
        Pair baixodikingta = this.GetBoardPosition().add(new Pair(+ 1, + 2));
        Pair baixoesquerda = this.GetBoardPosition().add(new Pair(- 1, + 2));

        // L pra dikingta doubleUp baixo
        Pair dikingtadoubleUp = this.GetBoardPosition().add(new Pair(+ 2, - 1));
        Pair dikingtabaixo = this.GetBoardPosition().add(new Pair(+ 2, + 1));

        // L pra esquerda doubleUp baixo
        Pair esquerdadoubleUp = this.GetBoardPosition().add(new Pair(- 2, - 1));
        Pair esquerdabaixo = this.GetBoardPosition().add(new Pair(- 2, + 1));

        if(doubleUpdikingta.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(doubleUpdikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, doubleUpdikingta, testingCheck);

        if(doubleUpesquerda.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(doubleUpesquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, doubleUpesquerda, testingCheck);

        if(baixodikingta.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(baixodikingta).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixodikingta, testingCheck);

        if(baixoesquerda.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(baixoesquerda).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixoesquerda, testingCheck);

        if(dikingtadoubleUp.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(dikingtadoubleUp).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, dikingtadoubleUp, testingCheck);

        if(dikingtabaixo.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(dikingtabaixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, dikingtabaixo, testingCheck);

        if(esquerdadoubleUp.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(esquerdadoubleUp).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerdadoubleUp, testingCheck);

        if(esquerdabaixo.IsPieceInsideBoard(0, SIZE) && color != board.GetPieceInPosition(esquerdabaixo).GetColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerdabaixo, testingCheck);

	if(testingCheck){
	    this.SetMoviments(newMovimentos);

	}

        return newMovimentos;
    }

}
