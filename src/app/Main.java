package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import gui.*;
import jogo.*;
import jogo.peca.*;
import misc.*;
import vfx.*;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.Position;

public class Main
{
	final static int LARGURA = 640;
	final static int ALTURA = 360;

	final static int XINICIAL = 192;
	final static int YINICIAL = 61;
	final static int ESCALA = 2;
	final static int MARGEM_PARTICULA = 20;

    public static void main(String[] args)
	{
		InitWindow(LARGURA, ALTURA, "Main");

		SetTargetFPS(60);

		// Cria um novo jogo
        Jogo jogo = new Jogo();
        jogo.NovoJogo(300);

        System.out.println(jogo.GetTabuleiro());

		int clicks = 0;
		Peca peca_movida = new Blank(0, 0);
		Peca peca_capturada = new Blank(0, 0);

		EmissorParticulaFundo emissor = new EmissorParticulaFundo(LARGURA, ALTURA, MARGEM_PARTICULA);

		//Font
		Font pixelFont = LoadFont("res/fonts/Pixellari.ttf");

		while (!WindowShouldClose())
		{

			BeginDrawing();

				ClearBackground(new Cor(52, 54, 71, 255).GetCor());
				//DrawText("Congrats! You created your first window!", 190, 200, 20, LIGHTGRAY);
				DrawFPS(20, 20);

				//Emitindo as particulas de fundo
				emissor.EmitirParicula();

				//Desenhando o tabuleiro
				jogo.GetTabuleiro().DrawGrid(XINICIAL, YINICIAL, ESCALA);


				//Vendo o se o mouse clicou em alguma posicao do tabuleiro
				if (jogo.GetTabuleiro().MouseClikedOnTabuleiro(XINICIAL, YINICIAL, ESCALA))
				{
					Pair posicao = jogo.GetTabuleiro().GetMousePositionOnTabuleiro(XINICIAL, YINICIAL, ESCALA);

					//Primeiro click, pega o peca_movida
					if (clicks == 0)
					{
						peca_movida = jogo.GetTabuleiro().GetPecaNaPosicao(posicao.x, posicao.y);

						clicks++;
					}
					//Segundo click pega o peca_capturada e jah faz a jogada
					else if (clicks == 1)
					{
						//Coisas do Enzo
						peca_capturada = jogo.GetTabuleiro().GetPecaNaPosicao(posicao.x, posicao.y);

						System.out.println("Peça movida: " + peca_movida.identificador);
						System.out.println("Peça capturada: " + peca_capturada.identificador);

						// Isso tera que ser levemente modificado para implementar o Roque
						if(peca_movida.GetCorPeca() == peca_capturada.GetCorPeca()){
						    peca_movida = peca_capturada;
						} else {
						    Jogada jogada = new Jogada(peca_movida, peca_capturada);

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
				}

				// Validando se o click é valido para o turno atual
				if(peca_movida.GetCorPeca() != jogo.GetJogadorTurnoAtual().GetCorJogador()){
				    peca_movida = new Blank(0, 0);
				    clicks = 0;
				}

				//Parando de selecionar uma peca
				if (IsMouseButtonPressed(1))
					clicks = 0;

				//Mostrando as jogadas
				if (clicks > 0)
				    jogo.GetTabuleiro().DrawMovimentosValidos(peca_movida.MovimentosValidos(jogo.GetTabuleiro()), XINICIAL, YINICIAL, ESCALA);
				//Desenhando as pecas
				jogo.GetTabuleiro().DrawPecas(XINICIAL, YINICIAL);

				DrawTextEx(pixelFont, jogo.GetJogadorBranco().GetRelogio().formatarTempo(), new Vector2().x(527).y(21), 32, 2, WHITE);
				DrawTextEx(pixelFont, jogo.GetJogadorPreto().GetRelogio().formatarTempo(), new Vector2().x(527).y(21 + 32), 32, 2, BLACK);


			EndDrawing();
		}
		CloseWindow();

    }
}
