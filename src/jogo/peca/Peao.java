package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;

import java.util.ArrayList;

import gui.Sprite;
import jogo.Jogada;

public class Peao extends Peca {

    public boolean jaMovido = false;

    public Peao(int x, int y, char id){
        super(x, y, id);
    }

    public Peca Promover(){
        char novo_id;
        if (Character.isLowerCase(this.identificador)) novo_id = 'd';
        else novo_id = 'D';
        return new Dama(this.grid_position.x, this.grid_position.y, novo_id);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){

    ArrayList<Pair> new_mov = new ArrayList<>();

	int direcao = -1;
	char cor = this.GetCorPeca();
	if(cor == 'b'){
	    direcao = -1;
	} else {
	    direcao = 1;
	}

	Pair cima = this.grid_position.add(new Pair(0, direcao * 1));
	Pair cima_duplo = this.grid_position.add(new Pair(0, direcao * 2));

        // diagonais superiores
        Pair superior_direita = this.grid_position.add(new Pair(+ 1, direcao * 1));
        Pair superior_esquerda = this.grid_position.add(new Pair(- 1, direcao * 1));

        if(cima.IsPieceInsideBoard(0, SIZE) && !(tabuleiro.IsTherePecaNaPosicao(cima))){
            new_mov.add(cima);
	    if(cima_duplo.IsPieceInsideBoard(0, SIZE) && !this.jaMovido && !(tabuleiro.IsTherePecaNaPosicao(cima_duplo))){
		new_mov.add(cima_duplo);
	    }
	}
        if(superior_direita.IsPieceInsideBoard(0, SIZE) && (tabuleiro.IsTherePecaNaPosicao(superior_direita)) && cor != tabuleiro.GetPecaNaPosicao(superior_esquerda).GetCorPeca())
            new_mov.add(superior_direita);
        if(superior_esquerda.IsPieceInsideBoard(0, SIZE) && (tabuleiro.IsTherePecaNaPosicao(superior_esquerda)) && cor != tabuleiro.GetPecaNaPosicao(superior_esquerda).GetCorPeca())
            new_mov.add(superior_esquerda);

        return new_mov;
    }

    @Override
    public void Mover(Jogada jogada){
        super.Mover(jogada);
        this.jaMovido = true;
    }

}
