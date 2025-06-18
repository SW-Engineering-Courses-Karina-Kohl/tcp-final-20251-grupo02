package game;

import game.pieces.Queen;
import game.pieces.Pawn;
import game.pieces.Piece;

public class Match {

    final int  MAX_PEÇAS = 32;
    final int  MAX_JOGADORES = 2;

    public Board board;
    Player jogador_branco;
    Player jogador_preto;

    Player jogador_turno_atual = jogador_branco;

    public void NovoMatch() {
        NovoMatch(300); //time padrão
    }

    public void NovoMatch(int timeInicial){
        this.board = new Board();
        this.jogador_branco = new Player('w', timeInicial);
        this.jogador_preto = new Player('b',timeInicial);
        this.jogador_turno_atual = jogador_branco;

        this.jogador_turno_atual.GetClock().StartClock();
    }


    public void ProximoTurno(){
        jogador_turno_atual.GetClock().StopClock();

        if (this.jogador_turno_atual == this.jogador_branco)
            this.jogador_turno_atual = this.jogador_preto;
        else this.jogador_turno_atual = this.jogador_branco;

        jogador_turno_atual.GetClock().StartClock();
        // this.board.GirarBoard();
    }

    public Board GetBoard() {
        return board;
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
