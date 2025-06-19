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
        Match jogo = new Match(300);

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
			    jogo = new Match(opcoesMenu.ConverteParaSegundos());
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


			    // Verificação da move
			    Move move = new Move(movedPiece, destinoPiece);
			    if (move.ValidateMove(tab)){

				tab.UpdateBoard(move);
				move.GetMovedPiece().MovePiece(move);

				if(movedPiece instanceof King) {
				    ((King) movedPiece).hasMoved = true;
				}
				if(movedPiece instanceof Rook) {
				    ((Rook) movedPiece).hasMoved = true;
				}

				// Verify if the players are in check
				jogo.GetWhitePlayer().SetCheckStatus(tab.CheckCheck(tab.GetKingColor('w')));
				jogo.GetBlackPlayer().SetCheckStatus(tab.CheckCheck(tab.GetKingColor('b')));

				jogo.NextTurn();

				if (move.GetMovedPiece() instanceof Pawn) {

				    int promotionTile = 0;
				    char pawnColor = movedPiece.GetPieceColor();
				    Pair pawnPosition = movedPiece.GetBoardPosition();

				    if(pawnColor == 'w'){
					promotionTile = 0;
				    } else {
					promotionTile = 7;
				    }

				    if(pawnPosition.y == promotionTile){

					//[T]orre  [C]avalo  [B]ispo  [D]ama"
					Scanner scanner = new Scanner(System.in);
					char escolha = Character.toUpperCase(scanner.next().charAt(0));
					switch (escolha) {
					case 'T':
					    tab.SetPieceInPosition(pawnPosition, new Rook(pawnPosition, move.PromotionId(pawnColor, 'T')));
					    break;
					case 'B':
					    tab.SetPieceInPosition(pawnPosition, new Bishop(pawnPosition, move.PromotionId(pawnColor, 'B')));
					    break;
					case 'C':
					    tab.SetPieceInPosition(pawnPosition, new Knight(pawnPosition, move.PromotionId(pawnColor, 'C')));
					    break;
					case 'D':
					    tab.SetPieceInPosition(pawnPosition, new Queen(pawnPosition, move.PromotionId(pawnColor, 'D')));
					    break;
					}
				    }
				}
			    }
			    clicks = 0;
			}
		    }

		    // valida turno
		    if (movedPiece.GetPieceColor() != jogo.GetCurrentTurnPlayer().GetColorPlayer()) {
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
