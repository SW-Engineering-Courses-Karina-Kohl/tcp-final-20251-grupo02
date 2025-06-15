package jogo;

import jogo.peca.Dama;
import jogo.peca.Peao;
import jogo.peca.Peca;

public class Jogo {

    final int  MAX_PEÇAS = 32;
    final int  MAX_JOGADORES = 2;

    public Tabuleiro tabuleiro;
    Jogador jogador_branco;
    Jogador jogador_preto;

    Jogador jogador_turno_atual = jogador_branco;

    public void NovoJogo() {
        NovoJogo(300); //tempo padrão
    }

    public void NovoJogo(int tempoInicial){
        this.tabuleiro = new Tabuleiro();
        this.jogador_branco = new Jogador('b', tempoInicial);
        this.jogador_preto = new Jogador('p',tempoInicial);
        this.jogador_turno_atual = jogador_branco;

        this.jogador_turno_atual.GetRelogio().IniciarRelogio();
    }


    public void ProximoTurno(){
        jogador_turno_atual.GetRelogio().PausaRelogio();

        if (this.jogador_turno_atual == this.jogador_branco)
            this.jogador_turno_atual = this.jogador_preto;
        else this.jogador_turno_atual = this.jogador_branco;

        jogador_turno_atual.GetRelogio().IniciarRelogio();
        // this.tabuleiro.GirarTabuleiro();
    }

    public Tabuleiro GetTabuleiro() {
        return tabuleiro;
    }

    public Jogador GetJogadorBranco() {
        return jogador_branco;
    }

    public Jogador GetJogadorPreto() {
        return jogador_preto;
    }

    public Jogador GetJogadorTurnoAtual() {
        return jogador_turno_atual;
    }
     

}
