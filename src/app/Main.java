package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import gui.*;
import jogo.*;
import jogo.peca.*;
import menu.*;
import misc.*;
import vfx.*;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.Position;

import com.raylib.Raylib.Font;
import com.raylib.Raylib.Vector2;

public class Main
{

    final static int LARGURA = 640 ;
    final static int ALTURA = 360;

    final static int XINICIAL = 192;
    final static int YINICIAL = 61;
    final static int ESCALA = 2;
    final static int MARGEM_PARTICULA = 20;

    public static void main(String[] args)
    {
        InitWindow(LARGURA, ALTURA, "Main");
        SetTargetFPS(60);
	//ToggleFullscreen();
        Jogo jogo = new Jogo();
        jogo.NovoJogo(300);

        int clicks = 0;
        Piece pecaMovida = new Blank(0, 0);
	ArrayList<Pair> movimentosClicado = new ArrayList<>();

        EmissorParticulaFundo emissor = new EmissorParticulaFundo(LARGURA, ALTURA, MARGEM_PARTICULA);
        Font pixelFont = LoadFont("res/fonts/Pixellari.ttf");

        //A array de paginas funciona da seguinte maneira
        //Cada posicao representa uma pagina, sendo um valor bool, falando se a tela esta ativa ou nao
        //Ou seja, normalmente averá apenas uma pagina ativa por vez

        //A ordem eh a seguinte:
        //Menu inicial, opcoes, jogo, tela final
        boolean[] paginas = new boolean[4];
        paginas[0] = true;

        //Flag que indica que eh pra criar um jogo novo
        boolean criarJogo = false;

        MainMenu mainMenu = new MainMenu(LARGURA);
        OpcoesMenu opcoesMenu = new OpcoesMenu(LARGURA, pixelFont);
        FinalMenu finalMenu = new FinalMenu(LARGURA, pixelFont);
        JogoMenu jogoMenu = new JogoMenu();

        //0 = empate; 1 = branco; 2 = preto;
        boolean[] vencedor = new boolean[3];

        //Ponteiro de novo
        boolean[] rodandoJogo = new boolean[1];
        rodandoJogo[0] = true;

        while (!WindowShouldClose() && rodandoJogo[0]) {
            BeginDrawing();

            ClearBackground(new Cor(52, 54, 71, 255).GetCor());
            // DrawFPS(20, 20);

            emissor.EmitirParicula();

            criarJogo = mainMenu.LogicaMainMenu(paginas, rodandoJogo);
            opcoesMenu.LogicaOpcoesMenu(paginas);
            finalMenu.LogicaFinalMenu(paginas, jogo, opcoesMenu, vencedor, rodandoJogo);

            if (paginas[2] == true)
            {
                jogoMenu.LogicaJogoMenu(paginas, jogo, vencedor);
                //Criando o jogo novo
                if (criarJogo == true)
                {
                    jogo.NovoJogo(opcoesMenu.ConverteParaSegundos());
                    pecaMovida = new Blank(0, 0);

                    for (int i = 0; i < 3; i++)
                    {
                        vencedor[i] = false;
                    }
                    criarJogo = false;
                }

                Tabuleiro tab = jogo.GetTabuleiro();
                tab.DrawGrid(XINICIAL, YINICIAL, ESCALA);

                if (tab.MouseClikedOnTabuleiro(XINICIAL, YINICIAL, ESCALA)) {
                    Pair pos = tab.GetMousePositionOnTabuleiro(XINICIAL, YINICIAL, ESCALA);
                    if (clicks == 0) {
                        pecaMovida = tab.GetPieceNaPosicao(pos.x, pos.y);
			pecaMovida.MovimentosValidos(tab, false);
			pecaMovida.MovimentosValidos(tab, true);
                        clicks = 1;

                    } else if (clicks == 1) {
                        Pair destino = pos;
                        Piece destinoPiece = tab.GetPieceNaPosicao(destino);
			System.out.println("Dentro do click 1");

                        // // Verificação do Roque
                        // if (pecaMovida instanceof King
                        //         && destino.y == pecaMovida.posicaoTabuleiro.y
                        //         && Math.abs(destino.x - pecaMovida.posicaoTabuleiro.x) == 2
                        //         && destinoPiece instanceof Blank) {

                        //     int dir = (destino.x > pecaMovida.posicaoTabuleiro.x) ? 1 : -1;
                        //     Pair torrePos = new Pair((dir == 1 ? 7 : 0), pecaMovida.posicaoTabuleiro.y);
                        //     Rook torre = (Rook) tab.GetPieceNaPosicao(torrePos);
                        //     Jogada roque = new Jogada(pecaMovida, torre);

                        //     // if (roque.ValidarRoque(tab)) {
                        //     //     // mover rei
                        //     //     tab.MudancaNoTabuleiro(new Jogada(pecaMovida, new Blank(destino.x, destino.y)));
                        //     //     pecaMovida.Mover(new Jogada(pecaMovida, new Blank(destino.x, destino.y)));
                        //     //     ((King) pecaMovida).jaMovido = true;

                        //     //     // mover torre
                        //     //     Pair torreDestino = new Pair(destino.x - dir, destino.y);
                        //     //     tab.MudancaNoTabuleiro(new Jogada(torre, new Blank(torreDestino.x, torreDestino.y)));
                        //     //     torre.Mover(new Jogada(torre, new Blank(torreDestino.x, torreDestino.y)));
                        //     //     torre.jaMovido = true;
                        //     //     jogo.ProximoTurno();
                        //     // }

                        //     clicks = 0;
                        //     continue;
                        // }

                        // Verificação da jogada
                        Jogada jogada = new Jogada(pecaMovida, destinoPiece);
                        if (jogada.ValidarJogada(tab)) {
                            tab.MudancaNoTabuleiro(jogada);
                            jogada.pecaMovida.Mover(jogada);
                            jogada.peca_capturada.DestruirPiece();

                            if (pecaMovida instanceof King) {
                                ((King) pecaMovida).jaMovido = true;
                            }
                            if (pecaMovida instanceof Rook) {
                                ((Rook) pecaMovida).jaMovido = true;
                            }

			    if(tab.CheckCheck(tab.GetKingCor('b'))){
				jogo.GetJogadorBranco().emCheque = true;
				System.out.println("Brancas em cheque");
			    } else {
				System.out.println("Brancas sem cheque");
				jogo.GetJogadorBranco().emCheque = false;
			    }

			    if (tab.CheckCheck(tab.GetKingCor('p'))){
				jogo.GetJogadorPreto().emCheque = true;
				System.out.println("Pretas em cheque");
			    } else {
				System.out.println("Pretas sem cheque");
				jogo.GetJogadorPreto().emCheque = false;
			    }

                            jogo.ProximoTurno();
                        // if (jogada.ValidarPromocaoPawn(tab)) {} falta a implementação do iuri
                        }

                        clicks = 0;
                    }
                }

                // valida turno
                if (pecaMovida.GetCorPiece() != jogo.GetJogadorTurnoAtual().GetCorJogador()) {
                    pecaMovida = new Blank(0, 0);
                    clicks = 0;
                }

                if (IsMouseButtonPressed(1)) clicks = 0;

                tab.DrawPieces(XINICIAL, YINICIAL);

                if (clicks == 1){
		    tab.DrawMovimentosValidos(pecaMovida.GetMovimentos(), XINICIAL, YINICIAL, ESCALA);
		}


                DrawTextEx(pixelFont, jogo.GetJogadorBranco().GetRelogio().formatarTempo(), new Vector2().x(527).y(21), 32, 2, WHITE);
                DrawTextEx(pixelFont, jogo.GetJogadorPreto().GetRelogio().formatarTempo(), new Vector2().x(527).y(53), 32, 2, BLACK);
            }
            EndDrawing();
        }
        CloseWindow();
    }
}
