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
        jogo.NewMatch(300);

        int clicks = 0;
        Piece movedPiece = new Blank(0, 0);
	ArrayList<Pair> movimentsClicado = new ArrayList<>();

        BackgroundParticlesEmitter emissor = new BackgroundParticlesEmitter(LARGURA, ALTURA, MARGEM_PARTICULA);
        Font pixelFont = LoadFont("res/fonts/Pixellari.ttf");

        //A array de paginas funciona da seguinte maneira
        //Cada position representa uma pagina, sendo um valor bool, falando se a tela esta ativa ou nao
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

            ClearBackground(new OurColor(52, 54, 71, 255).GetColor());
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
                    jogo.NewMatch(opcoesMenu.ConverteParaSegundos());
                    movedPiece = new Blank(0, 0);

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
                        movedPiece = tab.GetPieceInPosition(pos.x, pos.y);
			movedPiece.ValidMoviments(tab, false);
			movedPiece.ValidMoviments(tab, true);
                        clicks = 1;

                    } else if (clicks == 1) {
                        Pair destino = pos;
                        Piece destinoPiece = tab.GetPieceInPosition(destino);
			System.out.println("Dentro do click 1");

                        // // Verificação do Roque
                        // if (movedPiece instanceof King
                        //         && destino.y == movedPiece.GetBoardPosition().y
                        //         && Math.abs(destino.x - movedPiece.GetBoardPosition().x) == 2
                        //         && destinoPiece instanceof Blank) {

                        //     int dir = (destino.x > movedPiece.GetBoardPosition().x) ? 1 : -1;
                        //     Pair rookPos = new Pair((dir == 1 ? 7 : 0), movedPiece.GetBoardPosition().y);
                        //     Rook rook = (Rook) tab.GetPieceInPosition(rookPos);
                        //     Move roque = new Move(movedPiece, rook);

                        //     // if (roque.ValidarRoque(tab)) {
                        //     //     // mover king
                        //     //     tab.UpdateBoard(new Move(movedPiece, new Blank(destino.x, destino.y)));
                        //     //     movedPiece.MovePiece(new Move(movedPiece, new Blank(destino.x, destino.y)));
                        //     //     ((King) movedPiece).hasMoved = true;

                        //     //     // mover rook
                        //     //     Pair rookDestino = new Pair(destino.x - dir, destino.y);
                        //     //     tab.UpdateBoard(new Move(rook, new Blank(rookDestino.x, rookDestino.y)));
                        //     //     rook.MovePiece(new Move(rook, new Blank(rookDestino.x, rookDestino.y)));
                        //     //     rook.hasMoved = true;
                        //     //     jogo.NextTurn();
                        //     // }

                        //     clicks = 0;
                        //     continue;
                        // }

                        // Verificação da move
                        Move move = new Move(movedPiece, destinoPiece);
                        if (move.ValidarMove(tab)) {
                            tab.UpdateBoard(move);
                            move.movedPiece.MovePiece(move);

                            if (movedPiece instanceof King) {
                                ((King) movedPiece).hasMoved = true;
                            }
                            if (movedPiece instanceof Rook) {
                                ((Rook) movedPiece).hasMoved = true;
                            }

			    if(tab.CheckCheck(tab.GetKingColor('w'))){
				jogo.GetWhitePlayer().emCheque = true;
				System.out.println("Brancas em cheque");
			    } else {
				System.out.println("Brancas sem cheque");
				jogo.GetWhitePlayer().emCheque = false;
			    }

			    if (tab.CheckCheck(tab.GetKingColor('b'))){
				jogo.GetBlackPlayer().emCheque = true;
				System.out.println("Pretas em cheque");
			    } else {
				System.out.println("Pretas sem cheque");
				jogo.GetBlackPlayer().emCheque = false;
			    }

                        jogo.NextTurn();
                         if (move.ValidarPromocaoPawn(tab)) {

                            Pair positionPeao = movedPiece.GetBoardPosition();
                            char color = movedPiece.GetColorPiece();
                            //[T]orre  [C]avalo  [B]ispo  [D]ama"
                            Scanner scanner = new Scanner(System.in);
                            char escolha = Character.toUpperCase(scanner.next().charAt(0));

                            Piece promocao = null;
                            switch (escolha) {
                                case 'T':
                                    char id = move.idPromocao(color, 'T');
                                    promocao = new Rook(positionPeao.x, positionPeao.y, id);
                                    break;
                                case 'B':
                                    id = move.idPromocao(color, 'T');
                                    promocao = new Bishop(positionPeao.x, positionPeao.y, id);
                                    break;
                                case 'C':
                                    id = move.idPromocao(color, 'T');
                                    promocao = new Knight(positionPeao.x, positionPeao.y, id);
                                    break;
                                case 'D':
                                    id = move.idPromocao(color, 'T');
                                    promocao = new Queen(positionPeao.x, positionPeao.y, id);
                                    break;
                            }
                            tab.SetPieceInPosition(positionPeao.x, positionPeao.y, promocao);
                         }
                        }

                        clicks = 0;
                    }
                }

                // valida turno
                if (movedPiece.GetColorPiece() != jogo.GetCurrentTurnPlayer().GetColorPlayer()) {
                    movedPiece = new Blank(0, 0);
                    clicks = 0;
                }

                if (IsMouseButtonPressed(1)) clicks = 0;

                tab.DrawPieces(XINICIAL, YINICIAL);

                if (clicks == 1){
		    tab.DrawValidMoviments(movedPiece.GetMoviments(), XINICIAL, YINICIAL, ESCALA);
		}


                DrawTextEx(pixelFont, jogo.GetWhitePlayer().GetClock().FormatTime(), new Vector2().x(527).y(21), 32, 2, WHITE);
                DrawTextEx(pixelFont, jogo.GetBlackPlayer().GetClock().FormatTime(), new Vector2().x(527).y(53), 32, 2, BLACK);
            }
            EndDrawing();
        }
        CloseWindow();
    }
}
