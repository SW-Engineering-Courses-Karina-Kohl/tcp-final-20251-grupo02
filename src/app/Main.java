package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import jogo.*;
import jogo.peca.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1) Inicia o jogo com 5 minutos (300s)
        Jogo jogo = new Jogo();
        jogo.NovoJogo(300);

        // 2) Thread dedicada a mostrar os relógios em tempo real
        Thread display = new Thread(() -> {
            while (true) {
                String tBranco = jogo.getJogadorBranco().getRelogio().formatarTempo();
                String tPreto  = jogo.getJogadorPreto() .getRelogio().formatarTempo();
                // Só atualiza a linha dos relógios, sem pular linha
                System.out.print("\rTempo BRANCO: " + tBranco +
                                 "    Tempo PRETO: " + tPreto);
                System.out.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "Display-Relogios");
        display.setDaemon(true);
        display.start();
		System.out.print("\n");
        // 3) Loop principal de entrada e movimento
        Scanner scanner = new Scanner(System.in);

        // Imprime o tabuleiro *uma vez*, antes de começar
        System.out.println(jogo.getTabuleiro());

        while (true) {
            System.out.println("Coordenacas da peça e da casa para mover: ");
			int px = scanner.nextInt() - 1;
			int py = scanner.nextInt() - 1;

			int mx = scanner.nextInt() - 1;
			int my = scanner.nextInt() - 1;

			Peca peca = jogo.tabuleiro.GetPecaNaPosicao(px, py);
			Peca peca2 = jogo.tabuleiro.GetPecaNaPosicao(mx, my);

			System.out.println("Peça movida: " + peca.identificador);
			System.out.println("Peça capturada: " + peca2.identificador);

			Jogada jogada = new Jogada(peca, peca2);
			
			if( jogada.ValidarJogada(jogo.tabuleiro) ){
				jogo.tabuleiro.MudancaNoTabuleiro(jogada);
				jogada.peca_movida.Mover(jogada);
				jogada.peca_capturada.DestruirPeca();
			} 

			System.out.println(jogo.tabuleiro);
        }
    }
}