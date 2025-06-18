package game.pieces;
import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import game.Jogada;

public class King extends Piece{

    public boolean jaMovido = false;
    private static Texture reiTexture = LoadTexture("res/pieces/rei.png");

    public King(int x, int y, char id){
        super(x, y, id);

        if (GetOurColorPiece() == 'b')
            sprite = new Sprite(reiTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(reiTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetOurColorPiece();

        Pair cima = this.posicaoBoard.add(new Pair(0, - 1));
        Pair baixo = this.posicaoBoard.add(new Pair(0, + 1));

        Pair direita = this.posicaoBoard.add(new Pair(+ 1, 0));
        Pair esquerda = this.posicaoBoard.add(new Pair(- 1, 0));

        // diagonais
        Pair superior_direita = this.posicaoBoard.add(new Pair(+ 1, - 1));
        Pair superior_esquerda = this.posicaoBoard.add(new Pair(- 1, - 1));

        Pair inferior_direita = this.posicaoBoard.add(new Pair(+ 1, + 1));
        Pair inferior_esquerda = this.posicaoBoard.add(new Pair(- 1, + 1));

        if(cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(cima).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima, testingCheck);

        if(baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(baixo).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo, testingCheck);

        if(direita.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(direita).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, direita, testingCheck);

        if(esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(esquerda).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda, testingCheck);

        if(superior_direita.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(superior_direita).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, superior_direita, testingCheck);

        if(superior_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(superior_esquerda).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, superior_esquerda, testingCheck);

        if(inferior_direita.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(inferior_direita).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, inferior_direita, testingCheck);

        if(inferior_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(inferior_esquerda).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, inferior_esquerda, testingCheck);


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
