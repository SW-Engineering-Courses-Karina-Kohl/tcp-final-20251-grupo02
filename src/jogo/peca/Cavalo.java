package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;
import java.util.ArrayList;

public class Cavalo extends Peca {

    public Cavalo(int x, int y, char id){
        super(x, y, id);
    }

    @Override
    public ArrayList<Pair> MovimentosValidos(Tabuleiro tabuleiro){

        ArrayList<Pair> new_mov = new ArrayList<>();

        // L pra cima direita esquerda
        Pair cima_direita = this.grid_position.add(new Pair(+ 1, - 2));
        Pair cima_esquerda = this.grid_position.add(new Pair(- 1, - 2));

        // L pra baixo direita esquerda
        Pair baixo_direita = this.grid_position.add(new Pair(+ 1, + 2));
        Pair baixo_esquerda = this.grid_position.add(new Pair(- 1, + 2));

        // L pra direita cima baixo
        Pair direita_cima = this.grid_position.add(new Pair(+ 2, - 1));
        Pair direita_baixo = this.grid_position.add(new Pair(+ 2, + 1));

        // L pra esquerda cima baixo
        Pair esquerda_cima = this.grid_position.add(new Pair(- 2, - 1));
        Pair esquerda_baixo = this.grid_position.add(new Pair(- 2, + 1));

        if(cima_direita.IsPieceInsideBoard(0, SIZE))
            new_mov.add(cima_direita);
        if(cima_esquerda.IsPieceInsideBoard(0, SIZE))
            new_mov.add(cima_esquerda);
        if(baixo_direita.IsPieceInsideBoard(0, SIZE))
            new_mov.add(baixo_direita);
        if(baixo_esquerda.IsPieceInsideBoard(0, SIZE))
            new_mov.add(baixo_esquerda);
        if(direita_cima.IsPieceInsideBoard(0, SIZE))
            new_mov.add(direita_cima);
        if(direita_baixo.IsPieceInsideBoard(0, SIZE))
            new_mov.add(direita_baixo);
        if(esquerda_cima.IsPieceInsideBoard(0, SIZE))
            new_mov.add(esquerda_cima);
        if(esquerda_baixo.IsPieceInsideBoard(0, SIZE))
            new_mov.add(esquerda_baixo);

        mov = new_mov;
        return mov;
    }

}
