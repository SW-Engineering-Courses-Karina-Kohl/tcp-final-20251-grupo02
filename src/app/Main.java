package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import jogo.*;
import jogo.peca.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // InitWindow(800, 450, "Main");

        // SetTargetFPS(60);


        // while (!WindowShouldClose())
        // {
        //     BeginDrawing();

        //         ClearBackground(RAYWHITE);
        //         DrawText("Congrats! You created your first window!", 190, 200, 20, LIGHTGRAY);
        //         DrawFPS(20, 20);

        //     EndDrawing();
        // }
        // CloseWindow();

	// Cria um novo jogo
        Jogo jogo = new Jogo();
        jogo.NovoJogo(300);


        Scanner scanner = new Scanner(System.in);

        System.out.println(jogo.getTabuleiro());

        while (true) {
            String tBranco = jogo.getJogadorBranco().getRelogio().formatarTempo();
	    String tPreto  = jogo.getJogadorPreto().getRelogio().formatarTempo();
	    // Só atualiza a linha dos relógios, sem pular linha
	    System.out.println("\rTempo BRANCO: " + tBranco +
			       "    Tempo PRETO: " + tPreto);
            System.out.println("Coordenacas da peça e da casa para mover: ");

	    // Le as peças do teclado
	    int px = scanner.nextInt() - 1;
	    int py = scanner.nextInt() - 1;

	    int mx = scanner.nextInt() - 1;
	    int my = scanner.nextInt() - 1;

	    // pega as peças nas posições lidas
	    Peca peca = jogo.tabuleiro.GetPecaNaPosicao(px, py);
	    Peca peca2 = jogo.tabuleiro.GetPecaNaPosicao(mx, my);

	    System.out.println("Peça movida: " + peca.identificador);
	    System.out.println("Peça capturada: " + peca2.identificador);

	    // Cria uma jogada com as peças
	    Jogada jogada = new Jogada(peca, peca2);

	    // Válida a jogada
	    if( jogada.ValidarJogada(jogo.tabuleiro) ){

		// Se for válida, muda o tabuleiro
		jogo.tabuleiro.MudancaNoTabuleiro(jogada);

		// Atualiza as peças
		jogada.peca_movida.Mover(jogada);
		jogada.peca_capturada.DestruirPeca();

                jogo.ProximoTurno(); // atualiza o turno
	    }

	    System.out.println(jogo.tabuleiro);
        }
    }
}
