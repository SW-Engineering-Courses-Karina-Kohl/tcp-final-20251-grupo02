package jogo;

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
        this.jogador_branco = new Jogador("branco", tempoInicial);
        this.jogador_preto = new Jogador("preto",tempoInicial);
        this.jogador_turno_atual = jogador_branco;

        this.jogador_turno_atual.getRelogio().IniciarRelogio();
    }
    

    public void ProximoTurno(){
        jogador_turno_atual.getRelogio().PausaRelogio();

        if (this.jogador_turno_atual == this.jogador_branco)
            this.jogador_turno_atual = this.jogador_preto;
        else this.jogador_turno_atual = this.jogador_branco;

        jogador_turno_atual.getRelogio().IniciarRelogio();
        this.tabuleiro.GirarTabuleiro();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Jogador getJogadorBranco() {
        return jogador_branco;
    }

    public Jogador getJogadorPreto() {
        return jogador_preto;
    }

    public Jogador getJogadorTurnoAtual() {
        return jogador_turno_atual;
    }

}
