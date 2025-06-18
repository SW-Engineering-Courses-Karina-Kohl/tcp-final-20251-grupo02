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

        if (GetOurColorPiece() == 'b')
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(cavaloTexture, 2, 0, 0, 1, WHITE, 2);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Board board, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();
	char cor = this.GetOurColorPiece();

        // L pra cima direita esquerda
        Pair cima_direita = this.posicaoBoard.add(new Pair(+ 1, - 2));
        Pair cima_esquerda = this.posicaoBoard.add(new Pair(- 1, - 2));

        // L pra baixo direita esquerda
        Pair baixo_direita = this.posicaoBoard.add(new Pair(+ 1, + 2));
        Pair baixo_esquerda = this.posicaoBoard.add(new Pair(- 1, + 2));

        // L pra direita cima baixo
        Pair direita_cima = this.posicaoBoard.add(new Pair(+ 2, - 1));
        Pair direita_baixo = this.posicaoBoard.add(new Pair(+ 2, + 1));

        // L pra esquerda cima baixo
        Pair esquerda_cima = this.posicaoBoard.add(new Pair(- 2, - 1));
        Pair esquerda_baixo = this.posicaoBoard.add(new Pair(- 2, + 1));

        if(cima_direita.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(cima_direita).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima_direita, testingCheck);

        if(cima_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(cima_esquerda).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, cima_esquerda, testingCheck);

        if(baixo_direita.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(baixo_direita).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo_direita, testingCheck);

        if(baixo_esquerda.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(baixo_esquerda).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, baixo_esquerda, testingCheck);

        if(direita_cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(direita_cima).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, direita_cima, testingCheck);

        if(direita_baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(direita_baixo).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, direita_baixo, testingCheck);

        if(esquerda_cima.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(esquerda_cima).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda_cima, testingCheck);

        if(esquerda_baixo.IsPieceInsideBoard(0, SIZE) && cor != board.GetPieceNaPosicao(esquerda_baixo).GetOurColorPiece())
	    this.CheckMoviment(board, newMovimentos, esquerda_baixo, testingCheck);

	if(testingCheck){
	    movimentos = newMovimentos;
	}

        return newMovimentos;
    }

}
