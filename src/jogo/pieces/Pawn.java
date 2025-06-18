package jogo.pieces;
import jogo.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;
import jogo.Jogada;

public class Pawn extends Piece {

    public boolean jaMovido = false;

    private static Texture peaoTexture = LoadTexture("res/pecas/peao.png");

    public Pawn(int x, int y, char id){
        super(x, y, id);
        if (GetOurColorPiece() == 'b')
            sprite = new Sprite(peaoTexture, 2, 0, 0, 0, WHITE, 2);
        else
            sprite = new Sprite(peaoTexture, 2, 0, 0, 1, WHITE, 2);
    }

    public Piece Promover(){
        char novo_id;
        if (Character.isLowerCase(this.identificador)) novo_id = 'd';
        else novo_id = 'D';
        return new Queen(this.posicaoBoard.x, this.posicaoBoard.y, novo_id);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Board tabuleiro, boolean testingCheck){

	ArrayList<Pair> newMovimentos = new ArrayList<>();

	int direcao = -1;
	char cor = this.GetOurColorPiece();
	if(cor == 'b'){
	    direcao = -1;
	} else {
	    direcao = 1;
	}


	Pair cima = this.posicaoBoard.add(new Pair(0, direcao * 1));
	Pair cima_duplo = this.posicaoBoard.add(new Pair(0, direcao * 2));

        // diagonais superiores
        Pair superior_direita = this.posicaoBoard.add(new Pair(+ 1, direcao * 1));
        Pair superior_esquerda = this.posicaoBoard.add(new Pair(- 1, direcao * 1));



        if(cima.IsPieceInsideBoard(0, SIZE) && !(tabuleiro.PosicaoOcupada(cima))){
	    this.CheckMoviment(tabuleiro, newMovimentos, cima, testingCheck);
	}


	    if(cima_duplo.IsPieceInsideBoard(0, SIZE) && !this.jaMovido && !(tabuleiro.PosicaoOcupada(cima_duplo))){
		this.CheckMoviment(tabuleiro, newMovimentos, cima_duplo, testingCheck);

	    }

        if(superior_direita.IsPieceInsideBoard(0, SIZE) && (tabuleiro.PosicaoOcupada(superior_direita)) && cor != tabuleiro.GetPieceNaPosicao(superior_direita).GetOurColorPiece()){
	    this.CheckMoviment(tabuleiro, newMovimentos, superior_direita, testingCheck);
	}

        if(superior_esquerda.IsPieceInsideBoard(0, SIZE) && (tabuleiro.PosicaoOcupada(superior_esquerda)) && cor != tabuleiro.GetPieceNaPosicao(superior_esquerda).GetOurColorPiece()){
	    this.CheckMoviment(tabuleiro, newMovimentos, superior_esquerda, testingCheck);
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
