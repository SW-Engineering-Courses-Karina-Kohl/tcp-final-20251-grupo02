package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;
import java.util.ArrayList;

public class Dama extends Peca {

    public Dama(int x, int y, char id){
        super(x, y, id);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){

        movimentos = new ArrayList<>();

        for(int i = 1; i < SIZE; i++) {
            Pair cima = this.grid_position.add(new Pair(0, - i));
            Pair baixo = this.grid_position.add(new Pair(0, + i));

            Pair direita = this.grid_position.add(new Pair(+ i, 0));
            Pair esquerda = this.grid_position.add(new Pair(- i, 0));

            // diagonais
            Pair superior_direita = this.grid_position.add(new Pair(+ i, - i));
            Pair superior_esquerda = this.grid_position.add(new Pair(- i, - i));

            Pair inferior_direita = this.grid_position.add(new Pair(+ i, + i));
            Pair inferior_esquerda = this.grid_position.add(new Pair(- i, + i));

            if(cima.IsPieceInsideBoard(0, SIZE))
                movimentos.add(cima);
            if(baixo.IsPieceInsideBoard(0, SIZE))
                movimentos.add(baixo);
            if(direita.IsPieceInsideBoard(0, SIZE))
                movimentos.add(direita);
            if(esquerda.IsPieceInsideBoard(0, SIZE))
                movimentos.add(esquerda);
            if(superior_direita.IsPieceInsideBoard(0, SIZE))
                movimentos.add(superior_direita);
            if(superior_esquerda.IsPieceInsideBoard(0, SIZE))
                movimentos.add(superior_esquerda);
            if(inferior_direita.IsPieceInsideBoard(0, SIZE))
                movimentos.add(inferior_direita);
            if(inferior_esquerda.IsPieceInsideBoard(0, SIZE))
                movimentos.add(inferior_esquerda);
        }

        return movimentos;
    }

}
