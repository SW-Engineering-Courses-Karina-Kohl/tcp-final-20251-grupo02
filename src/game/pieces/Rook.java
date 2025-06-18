package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Jogada;

public class Rook extends Piece {

    public boolean jaMovido = false;
	private static Texture torreTexture = LoadTexture("res/pieces/torre.png");

    public Rook(int x, int y, char id){
        super(x, y, id);

        if (GetColorPiece() == 'b')
            sprite = new Sprite(torreTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(torreTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetColorPiece();

	boolean pieceCima = false;
	boolean pieceBaixo = false;
	boolean pieceDireita = false;
	boolean pieceEsquerda = false;

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.posicaoBoard.add(new Pair(0, - i));
            Pair baixo = this.posicaoBoard.add(new Pair(0, + i));

            Pair direita = this.posicaoBoard.add(new Pair(+ i, 0));
            Pair esquerda = this.posicaoBoard.add(new Pair(- i, 0));

	    if(!pieceCima && cima.IsPieceInsideBoard(0, SIZE)){
                if(board.PositionOcupada(cima)){
		    pieceCima = true;
		}
		if(cor != board.GetPieceInPosition(cima).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, cima, testingCheck);
		}
	    }

	    if(!pieceBaixo && baixo.IsPieceInsideBoard(0, SIZE)){
                if(board.PositionOcupada(baixo)){
		    pieceBaixo = true;
		}
		if(cor != board.GetPieceInPosition(baixo).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);
		}
	    }

	    if(!pieceDireita && direita.IsPieceInsideBoard(0, SIZE)){
                if(board.PositionOcupada(direita)){
		    pieceDireita = true;
		}
		if(cor != board.GetPieceInPosition(direita).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, direita, testingCheck);
		}
	    }

	    if(!pieceEsquerda && esquerda.IsPieceInsideBoard(0, SIZE)){
                if(board.PositionOcupada(esquerda)){
		    pieceEsquerda = true;
		}
		if(cor != board.GetPieceInPosition(esquerda).GetColorPiece()){
		    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);
		}
	    }

        }

	if(testingCheck){
	    movimentos = newMovimentos;
	}

        return newMovimentos;
    }

    @Override
    public void Mover(Jogada jogada){
        super.Mover(jogada);
        this.jaMovido = true;
    }

}
