package jogo;
import jogo.peca.Peca;
import jogo.peca.Peca.Pair;

public class Jogada {
    
    Peca peca_movida;
    Pair posicao_final;
    Pair movimento;
    boolean jogada_valida;
    //Jogador jogador;

    public Jogada(Peca peca_movida, Pair posicao_final){
        this.peca_movida = peca_movida;
        this.posicao_final = posicao_final;
        this.movimento = this.peca_movida.grid_position.add(new Pair(-posicao_final.x, -posicao_final.y));
    }

    // depende de Rei.jaMovido, Peao.jaMovido, Torre.jaMovido
    //
    public void ValidarJogada(Tabuleiro tabuleiro){
        
        // se a peca movida pode ir para a posição final
        this.jogada_valida = peca_movida.MovimentosValidos().contains(this.posicao_final.add(movimento));
        
        
        /*
        // se existe uma peça na posição final do tabuleiro
        if((tabuleiro.IsTherePecaNaPosicao(posicao_final.x, posicao_final.y)))
           

        else
            posicao_final = new Pair(-1, -1);
        */
        //return this.jogada_valida;
    }
}
