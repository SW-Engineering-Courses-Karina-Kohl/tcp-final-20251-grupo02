package jogo;
import jogo.peca.Peca;
import jogo.peca.Peca.Pair;

public class Jogada {
    
    public Peca peca_movida;
    public Pair posicao_final;
    Pair movimento;
    boolean jogada_valida;
    //Jogador jogador;

    // peca_movida = tab.getPecaNaPosicao(x,y)
    // posicao_final = vai vir do frontend
    public Jogada(Peca peca_movida, Pair posicao_final){
        this.peca_movida = peca_movida;
        this.posicao_final = posicao_final;
        this.movimento = this.peca_movida.grid_position.add(new Pair(-posicao_final.x, -posicao_final.y));
    }

    // valida se a jogada pode ser feita e muda o tabuleiro 
    // depende de Rei.jaMovido, Peao.jaMovido, Torre.jaMovido
    //
    public void ValidarJogada(Tabuleiro tabuleiro){
        
        // se a peca movida pode ir para a posição final
        this.jogada_valida = peca_movida.MovimentosValidos().contains(this.posicao_final);
        
        if (this.jogada_valida)
            tabuleiro.MudancaNoTabuleiro(this);

        /*
        // se existe uma peça na posição final do tabuleiro
        if((tabuleiro.IsTherePecaNaPosicao(posicao_final.x, posicao_final.y)))
           

        else
            posicao_final = new Pair(-1, -1);
        */
        //return this.jogada_valida;
    }
}
