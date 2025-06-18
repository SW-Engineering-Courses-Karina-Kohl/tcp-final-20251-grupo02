package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Move;

public class Pawn extends Piece {

    public boolean jaMovido = false;

    private static Texture peaoTexture = LoadTexture("res/pieces/peao.png");

    public Pawn(int x, int y, char id){
        super(x, y, id);
        if (GetColorPiece() == 'w')
            sprite = new Sprite(peaoTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(peaoTexture, 2, 0, 0, 1, WHITE, 2);
    }

    public Piece Promover(){
        char novo_id;
        if (Character.isLowerCase(this.id)) novo_id = 'd';
        else novo_id = 'D';
        return new Queen(this.boardPosition.x, this.boardPosition.y, novo_id);
    }

    @Override
    public ArrayList<Pair> ValidMoviments(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();

	int direcao = -1;
	char cor = this.GetColorPiece();
	if(cor == 'w'){
	    direcao = -1;
	} else {
	    direcao = 1;
	}


	Pair cima = this.boardPosition.add(new Pair(0, direcao * 1));
	Pair cima_duplo = this.boardPosition.add(new Pair(0, direcao * 2));

        // diagonais superiores
        Pair superior_direita = this.boardPosition.add(new Pair(+ 1, direcao * 1));
        Pair superior_esquerda = this.boardPosition.add(new Pair(- 1, direcao * 1));



        if(cima.IsPieceInsideBoard(0, SIZE) && !(board.IsTherePieceInPosition(cima))){
	    this.CheckMoviment(board, newMovimentos, cima, testingCheck);
	}


	    if(cima_duplo.IsPieceInsideBoard(0, SIZE) && !this.jaMovido && !(board.IsTherePieceInPosition(cima_duplo))){
		this.CheckMoviment(board, newMovimentos, cima_duplo, testingCheck);

	    }

        if(superior_direita.IsPieceInsideBoard(0, SIZE) && (board.IsTherePieceInPosition(superior_direita)) && cor != board.GetPieceInPosition(superior_direita).GetColorPiece()){
	    this.CheckMoviment(board, newMovimentos, superior_direita, testingCheck);
	}

        if(superior_esquerda.IsPieceInsideBoard(0, SIZE) && (board.IsTherePieceInPosition(superior_esquerda)) && cor != board.GetPieceInPosition(superior_esquerda).GetColorPiece()){
	    this.CheckMoviment(board, newMovimentos, superior_esquerda, testingCheck);
	}

	if(testingCheck){
	    moviments = newMovimentos;
	}

        return newMovimentos;
    }

    @Override
    public void MovePiece(Move move){
        super.MovePiece(move);
        this.jaMovido = true;
    }

}
