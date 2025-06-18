package app;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import gui.*;
import game.*;
import game.pieces.*;
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
	//ToggleFullscreen();
        Match jogo = new Match();
        jogo.NovoMatch(300);

        int clicks = 0;
        Piece pieceMovida = new Blank(0, 0);
	ArrayList<Pair> movimentosClicado = new ArrayList<>();

        BackgroundParticlesEmitter emissor = new BackgroundParticlesEmitter(LARGURA, ALTURA, MARGEM_PARTICULA);
        Font pixelFont = LoadFont("res/fonts/Pixellari.ttf");

        //A array de paginas funciona da seguinte maneira
        //Cada posicao representa uma pagina, sendo um valor bool, falando se a tela esta ativa ou nao
        //Ou seja, normalmente averá apenas uma pagina ativa por vez

        //A ordem eh a seguinte:
        //Menu inicial, opcoes, jogo, tela final
        boolean[] paginas = new boolean[4];
        paginas[0] = true;

        //Flag que indica que eh pra criar um jogo novo
        boolean criarMatch = false;

        MainMenu mainMenu = new MainMenu(LARGURA);
        OptionsMenu opcoesMenu = new OptionsMenu(LARGURA, pixelFont);
        FinalMenu finalMenu = new FinalMenu(LARGURA, pixelFont);
        GameMenu jogoMenu = new GameMenu();

        //0 = empate; 1 = branco; 2 = preto;
        boolean[] vencedor = new boolean[3];

        //Ponteiro de novo
        boolean[] rodandoMatch = new boolean[1];
        rodandoMatch[0] = true;

        while (!WindowShouldClose() && rodandoMatch[0]) {
            BeginDrawing();

            ClearBackground(new OurColor(52, 54, 71, 255).GetOurColor());
            // DrawFPS(20, 20);

            emissor.EmitirParicula();

            criarMatch = mainMenu.LogicaMainMenu(paginas, rodandoMatch);
            opcoesMenu.LogicaOptionsMenu(paginas);
            finalMenu.LogicaFinalMenu(paginas, jogo, opcoesMenu, vencedor, rodandoMatch);

            if (paginas[2] == true)
            {
                jogoMenu.LogicaGameMenu(paginas, jogo, vencedor);
                //Criando o jogo novo
                if (criarMatch == true)
                {
                    jogo.NovoMatch(opcoesMenu.ConverteParaSegundos());
                    pieceMovida = new Blank(0, 0);

                    for (int i = 0; i < 3; i++)
                    {
                        vencedor[i] = false;
                    }
                    criarMatch = false;
                }

                Board tab = jogo.GetBoard();
                tab.DrawGrid(XINICIAL, YINICIAL, ESCALA);

                if (tab.MouseClikedOnBoard(XINICIAL, YINICIAL, ESCALA)) {
                    Pair pos = tab.GetMousePositionOnBoard(XINICIAL, YINICIAL, ESCALA);
                    if (clicks == 0) {
                        pieceMovida = tab.GetPieceInPosition(pos.x, pos.y);
			pieceMovida.MovimentosValidos(tab, false);
			pieceMovida.MovimentosValidos(tab, true);
                        clicks = 1;

                    } else if (clicks == 1) {
                        Pair destino = pos;
                        Piece destinoPiece = tab.GetPieceInPosition(destino);
			System.out.println("Dentro do click 1");

                        // // Verificação do Roque
                        // if (pieceMovida instanceof King
                        //         && destino.y == pieceMovida.posicaoBoard.y
                        //         && Math.abs(destino.x - pieceMovida.posicaoBoard.x) == 2
                        //         && destinoPiece instanceof Blank) {

                        //     int dir = (destino.x > pieceMovida.posicaoBoard.x) ? 1 : -1;
                        //     Pair torrePos = new Pair((dir == 1 ? 7 : 0), pieceMovida.posicaoBoard.y);
                        //     Rook torre = (Rook) tab.GetPieceInPosition(torrePos);
                        //     Jogada roque = new Jogada(pieceMovida, torre);

                        //     // if (roque.ValidarRoque(tab)) {
                        //     //     // mover rei
                        //     //     tab.MudancaNoBoard(new Jogada(pieceMovida, new Blank(destino.x, destino.y)));
                        //     //     pieceMovida.Mover(new Jogada(pieceMovida, new Blank(destino.x, destino.y)));
                        //     //     ((King) pieceMovida).jaMovido = true;

                        //     //     // mover torre
                        //     //     Pair torreDestino = new Pair(destino.x - dir, destino.y);
                        //     //     tab.MudancaNoBoard(new Jogada(torre, new Blank(torreDestino.x, torreDestino.y)));
                        //     //     torre.Mover(new Jogada(torre, new Blank(torreDestino.x, torreDestino.y)));
                        //     //     torre.jaMovido = true;
                        //     //     jogo.ProximoTurno();
                        //     // }

                        //     clicks = 0;
                        //     continue;
                        // }

                        // Verificação da jogada
                        Jogada jogada = new Jogada(pieceMovida, destinoPiece);
                        if (jogada.ValidarJogada(tab)) {
                            tab.MudancaNoBoard(jogada);
                            jogada.pieceMovida.Mover(jogada);
                            jogada.piece_capturada.DestruirPiece();

                            if (pieceMovida instanceof King) {
                                ((King) pieceMovida).jaMovido = true;
                            }
                            if (pieceMovida instanceof Rook) {
                                ((Rook) pieceMovida).jaMovido = true;
                            }

			    if(tab.CheckCheck(tab.GetKingColor('b'))){
				jogo.GetPlayerBranco().emCheque = true;
				System.out.println("Brancas em cheque");
			    } else {
				System.out.println("Brancas sem cheque");
				jogo.GetPlayerBranco().emCheque = false;
			    }

			    if (tab.CheckCheck(tab.GetKingColor('p'))){
				jogo.GetPlayerPreto().emCheque = true;
				System.out.println("Pretas em cheque");
			    } else {
				System.out.println("Pretas sem cheque");
				jogo.GetPlayerPreto().emCheque = false;
			    }

                        jogo.ProximoTurno();
                         if (jogada.ValidarPromocaoPawn(tab)) {

                            Pair posicaoPeao = pieceMovida.posicaoBoard;
                            char cor = pieceMovida.GetColorPiece();
                            //[T]orre  [C]avalo  [B]ispo  [D]ama"
                            Scanner scanner = new Scanner(System.in);
                            char escolha = Character.toUpperCase(scanner.next().charAt(0));

                            Piece promocao = null;
                            switch (escolha) {
                                case 'T':
                                    char id = jogada.idPromocao(cor, 'T');
                                    promocao = new Rook(posicaoPeao.x, posicaoPeao.y, id);
                                    break;
                                case 'B':
                                    id = jogada.idPromocao(cor, 'T');
                                    promocao = new Bishop(posicaoPeao.x, posicaoPeao.y, id);
                                    break;
                                case 'C':
                                    id = jogada.idPromocao(cor, 'T');
                                    promocao = new Knight(posicaoPeao.x, posicaoPeao.y, id);
                                    break;
                                case 'D':
                                    id = jogada.idPromocao(cor, 'T');
                                    promocao = new Queen(posicaoPeao.x, posicaoPeao.y, id);
                                    break;
                            }
                            tab.SetPieceNaPosition(posicaoPeao.x, posicaoPeao.y, promocao);
                         }
                        }

                        clicks = 0;
                    }
                }

                // valida turno
                if (pieceMovida.GetColorPiece() != jogo.GetPlayerTurnoAtual().GetColorPlayer()) {
                    pieceMovida = new Blank(0, 0);
                    clicks = 0;
                }

                if (IsMouseButtonPressed(1)) clicks = 0;

                tab.DrawPieces(XINICIAL, YINICIAL);

                if (clicks == 1){
		    tab.DrawMovimentosValidos(pieceMovida.GetMovimentos(), XINICIAL, YINICIAL, ESCALA);
		}


                DrawTextEx(pixelFont, jogo.GetPlayerBranco().GetClock().formatarTempo(), new Vector2().x(527).y(21), 32, 2, WHITE);
                DrawTextEx(pixelFont, jogo.GetPlayerPreto().GetClock().formatarTempo(), new Vector2().x(527).y(53), 32, 2, BLACK);
            }
            EndDrawing();
        }
        CloseWindow();
    }
}
