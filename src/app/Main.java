package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import gui.*;
import jogo.*;
import jogo.peca.*;
import misc.*;

import java.util.Scanner;

public class Main 
{
	final static int LARGURA = 640;
	final static int ALTURA = 360;

	final static int XINICIAL = 192;
	final static int YINICIAL = 61;
	final static int ESCALA = 2;

    public static void main(String[] args) 
	{
		InitWindow(LARGURA, ALTURA, "Main");

		SetTargetFPS(60);

		// Cria um novo jogo
        Jogo jogo = new Jogo();
        jogo.NovoJogo(300);

        System.out.println(jogo.getTabuleiro());
		
		int clicks = 0;
		Peca peca = jogo.getTabuleiro().GetPecaNaPosicao(0, 0);
		Peca peca2 = jogo.getTabuleiro().GetPecaNaPosicao(0, 0);


		while (!WindowShouldClose()) 
		{

			BeginDrawing();

				ClearBackground(new Cor(52, 54, 71, 255).GetCor());
				//DrawText("Congrats! You created your first window!", 190, 200, 20, LIGHTGRAY);
				DrawFPS(20, 20);

				jogo.getTabuleiro().DrawGrid(192, 61, 2);

				if (jogo.getTabuleiro().MouseClikedOnTabuleiro(XINICIAL, YINICIAL, ESCALA))
				{
					Pair posicao = jogo.getTabuleiro().GetMousePositionOnTabuleiro(XINICIAL, YINICIAL, ESCALA);

					if (clicks == 0)
					{
						peca = jogo.getTabuleiro().GetPecaNaPosicao(posicao.x, posicao.y);
						clicks++;
					}
					else if (clicks == 1)
					{
						peca2 = jogo.getTabuleiro().GetPecaNaPosicao(posicao.x, posicao.y);

						System.out.println("Peça movida: " + peca.identificador);
						System.out.println("Peça capturada: " + peca2.identificador);

						Jogada jogada = new Jogada(peca, peca2);

						// Válida a jogada
						if(jogada.ValidarJogada(jogo.tabuleiro))
						{
							// Se for válida, muda o tabuleiro
							jogo.tabuleiro.MudancaNoTabuleiro(jogada);

							// Atualiza as peças
							jogada.peca_movida.Mover(jogada);
							jogada.peca_capturada.DestruirPeca();

							jogo.ProximoTurno(); // atualiza o turno
						}

						System.out.println(jogo.tabuleiro);
						clicks = 0;
					}
				}

				//Desenhando as pecas
				jogo.getTabuleiro().DrawPecas(XINICIAL, YINICIAL);

			EndDrawing();
		}
		CloseWindow();

    }
}
