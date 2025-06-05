package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import jogo.*;
import misc.*;
import jogo.peca.*;

import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
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

	Jogo jogo = new Jogo();
	jogo.NovoJogo();

	Scanner scanner = new Scanner(System.in);

	System.out.println(jogo.tabuleiro.toString());

	System.out.println("Coordenacas da peça e da casa para mover: ");

	int px = scanner.nextInt() - 1;
	int py = scanner.nextInt() - 1;

	int mx = scanner.nextInt() - 1;
	int my = scanner.nextInt() - 1;

	Peca peca = jogo.tabuleiro.GetPecaNaPosicao(px, py);
	Peca peca2 = jogo.tabuleiro.GetPecaNaPosicao(mx, my);

	System.out.println(peca.identificador);
	System.out.println(peca2.identificador);

	Jogada jogada = new Jogada(peca, peca2);
	jogada.ValidarJogada(jogo.tabuleiro);
	if(jogada.jogada_valida){
	    jogo.tabuleiro.MudancaNoTabuleiro(jogada);
	} else {
	    System.out.println("invalida");
	}
	//


	System.out.println(jogo.tabuleiro.toString());
	// for(int l = 0; l < 8; l++){
	//     for(int c = 0; c < 8; c++){

	//     }
	// }
    }
}
