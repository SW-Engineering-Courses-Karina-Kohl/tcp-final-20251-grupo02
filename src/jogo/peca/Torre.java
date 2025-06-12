package jogo.peca;
import jogo.Tabuleiro;
import misc.Pair;
import java.util.ArrayList;
import jogo.Jogada;

public class Torre extends Peca {

    public boolean jaMovido = false;

    public Torre(int x, int y, char id){
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

            if(cima.IsPieceInsideBoard(0, SIZE))
                movimentos.add(cima);
            if(baixo.IsPieceInsideBoard(0, SIZE) )
                movimentos.add(baixo);
            if(direita.IsPieceInsideBoard(0, SIZE))
                movimentos.add(direita);
            if(esquerda.IsPieceInsideBoard(0, SIZE))
                movimentos.add(esquerda);
        }

        return movimentos;
    }

    @Override
    public void Mover(Jogada jogada){
        super.Mover(jogada);
        this.jaMovido = true;
    }

}
