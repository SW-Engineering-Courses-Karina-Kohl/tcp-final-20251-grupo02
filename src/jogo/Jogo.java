package jogo;

public class Jogo {

    final int  MAX_PEÇAS = 32;
    final int  MAX_JOGADORES = 2;

    public Tabuleiro tabuleiro;
    Jogador jogador_branco;
    Jogador jogador_preto;

    Jogador jogador_turno_atual = jogador_branco;

    public void NovoJogo(){
        this.tabuleiro = new Tabuleiro();
        this.jogador_branco = new Jogador("branco");
        this.jogador_preto = new Jogador("preto");
        this.jogador_turno_atual = jogador_branco;
    }

    public void ProximoTurno(){
        if (this.jogador_turno_atual == this.jogador_branco)
            this.jogador_turno_atual = this.jogador_preto;
        else this.jogador_turno_atual = this.jogador_branco;

        this.tabuleiro.GirarTabuleiro();
    }

}
