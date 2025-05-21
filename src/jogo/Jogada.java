package jogo;
import jogo.peca.*;
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
        //this.jogada_valida = peca_movida.MovimentosValidos().contains(this.posicao_final);
        // implementar logica de nao ter peça no caminho

        // roque:
        Peca pecaNaPosicaoFinal = tabuleiro.GetPecaNaPosicao(this.posicao_final.x, this.posicao_final.y);
        if (
            this.peca_movida instanceof Torre
            && (this.peca_movida.grid_position.equals(new Pair(0, 7)) || this.peca_movida.grid_position.equals(new Pair(7, 7)))
            && pecaNaPosicaoFinal instanceof Rei
            && pecaNaPosicaoFinal.grid_position.equals(new Pair(3, 7)) &&
            
            (
            (!tabuleiro.IsTherePecaNaPosicao(1, 7) && !tabuleiro.IsTherePecaNaPosicao(2, 7))
            || !tabuleiro.IsTherePecaNaPosicao(4, 7) && !tabuleiro.IsTherePecaNaPosicao(5, 7) && !tabuleiro.IsTherePecaNaPosicao(6, 7))
            
            )
            
            jogada_valida = true;
        
            


        if (this.jogada_valida)
            tabuleiro.MudancaNoTabuleiro(this);
    }
       
    /* public boolean IsTherePecaInBetween(Tabuleiro tabuleiro){
        for(Pair : peca_movida.MovimentosValidos()){

        }



    } */


}
