package jogo;

import jogo.pieces.Queen;
import jogo.pieces.Pawn;
import jogo.pieces.Piece;

public class Match {

    final int  MAX_PEÇAS = 32;
    final int  MAX_JOGADORES = 2;

    public Board tabuleiro;
    Player jogador_branco;
    Player jogador_preto;

    Player jogador_turno_atual = jogador_branco;

    public void NovoMatch() {
        NovoMatch(300); //tempo padrão
    }

    public void NovoMatch(int tempoInicial){
        this.tabuleiro = new Board();
        this.jogador_branco = new Player('b', tempoInicial);
        this.jogador_preto = new Player('p',tempoInicial);
        this.jogador_turno_atual = jogador_branco;

        this.jogador_turno_atual.GetClock().IniciarClock();
    }


    public void ProximoTurno(){
        jogador_turno_atual.GetClock().PausaClock();

        if (this.jogador_turno_atual == this.jogador_branco)
            this.jogador_turno_atual = this.jogador_preto;
        else this.jogador_turno_atual = this.jogador_branco;

        jogador_turno_atual.GetClock().IniciarClock();
        // this.tabuleiro.GirarBoard();
    }

    public Board GetBoard() {
        return tabuleiro;
    }

    public Player GetPlayerBranco() {
        return jogador_branco;
    }

    public Player GetPlayerPreto() {
        return jogador_preto;
    }

    public Player GetPlayerTurnoAtual() {
        return jogador_turno_atual;
    }
     

}
