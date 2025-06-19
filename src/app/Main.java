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

import javax.swing.text.Position;

import com.raylib.Raylib.Font;
import com.raylib.Raylib.Vector2;

public class Main{

    final static int WIDTH = 640;
    final static int HEIGHT = 360;

    final static int INITIALX = 192;
    final static int INITIALY = 61;
    final static int SCALE = 2;
    final static int PARTICLE_MARGIN = 20;

    // Menu constants
    final static int MENU = 0;
    final static int OPTIONS = 1;
    final static int GAME = 2;
    final static int FINAL = 3;


    public static void main(String[] args){

        InitWindow(WIDTH, HEIGHT, "Tabuleiro de Combate de Peças");
        SetTargetFPS(60);

        Match match = new Match(300);

        int clicks = 0;
        Piece movedPiece = new Blank(0, 0);

        BackgroundParticlesEmitter particleEmitter = new BackgroundParticlesEmitter(WIDTH, HEIGHT, PARTICLE_MARGIN);
        Font pixelFont = LoadFont("res/fonts/Pixellari.ttf");

        // The page array function as follow:
        // Each position represents one page, being a bool, telling if the page is active or not
        // It means, usually there will be only one page active at the moment.

        // The order is:
        // Main menu, options, game and final menu
        boolean[] pages = new boolean[4];
        pages[0] = true;

        // Flag that indicates to start a new match
        boolean startNewMatch = false;

        MainMenu mainMenu = new MainMenu(WIDTH);
        OptionsMenu optionsMenu = new OptionsMenu(WIDTH, pixelFont);
        FinalMenu finalMenu = new FinalMenu(WIDTH, pixelFont);
        GameMenu gameMenu = new GameMenu();

        //0 = tie; 1 = white; 2 = black;
        boolean[] winner = new boolean[3];

        //Ponteiro de novo
        boolean[] isGameRunning = new boolean[1];
        isGameRunning[0] = true;

        while (!WindowShouldClose() && isGameRunning[0]){

            BeginDrawing();
            ClearBackground(new OurColor(52, 54, 71, 255).GetColor());

            particleEmitter.SendParticle();

            startNewMatch = mainMenu.MainMenuLogic(pages, isGameRunning);

            optionsMenu.OptionsMenuLogic(pages);
            finalMenu.FinalMenuLogic(pages, match, optionsMenu, winner, isGameRunning);

            if (pages[GAME] == true){

		    gameMenu.GameMenuLogic(pages, match, winner);

		    // creating new match
		    if (startNewMatch == true){
			    match = new Match(optionsMenu.ConvertToSeconds());
			    movedPiece = new Blank(0, 0);

			    for (int i = 0; i < 3; i++){
				winner[i] = false;
			    }
			    startNewMatch = false;
			}

		    Board board = match.GetBoard();
		    board.DrawGrid(INITIALX, INITIALY, SCALE);

		    if (board.MouseClikedOnBoard(INITIALX, INITIALY, SCALE)){

			Pair pos = board.GetMousePositionOnBoard(INITIALX, INITIALY, SCALE);

			if (clicks == 0) {
			    if(board.GetPieceInPosition(pos).findPieceColor() == match.GetCurrentTurnPlayer().GetColorPlayer()){
				movedPiece = board.GetPieceInPosition(pos);
				movedPiece.validMovements(board, true);
				clicks = 1;
			    }
			} else if (clicks == 1) {

			    Piece destinePiece = board.GetPieceInPosition(pos);

			    // Verificação da move
			    Move move = new Move(movedPiece, destinePiece);

			    if(move.ValidateMove(board)){

				board.UpdateBoard(move);
				move.GetMovedPiece().movePiece(move);

				if(movedPiece instanceof King) {
				    ((King) movedPiece).hasMoved = true;
				}
				if(movedPiece instanceof Rook) {
				    ((Rook) movedPiece).hasMoved = true;
				}
				if(movedPiece instanceof Pawn) {
				    ((Pawn) movedPiece).hasMoved = true;
				}

				// Verify if the players are in check
				match.GetWhitePlayer().SetCheckStatus(board.CheckCheck(board.GetKingColor('w')));
				match.GetBlackPlayer().SetCheckStatus(board.CheckCheck(board.GetKingColor('b')));

				if (move.GetMovedPiece() instanceof Pawn) {
				    move.CheckPawnPromotion(board);
				}

				// end turn
				match.NextTurn();
				clicks = 0;

			    } else {
				// changes the piece if the players clicks on one of the same color
				if(destinePiece.findPieceColor() == movedPiece.findPieceColor()){
				    movedPiece = board.GetPieceInPosition(pos);
				    movedPiece.validMovements(board, true);
				} else {
				    movedPiece = new Blank(0,  0);
				    clicks = 0;
				}
			    }
			}
		    }

		    if (IsMouseButtonPressed(1)) clicks = 0;

		    if (clicks == 1){
			board.DrawValidMoviments(movedPiece.getMovements(), INITIALX, INITIALY, SCALE);
		    }

		    board.DrawPieces(INITIALX, INITIALY);
		    DrawTextEx(pixelFont, match.GetWhitePlayer().GetClock().formatTime(), new Vector2().x(527).y(21), 32, 2, WHITE);
		    DrawTextEx(pixelFont, match.GetBlackPlayer().GetClock().formatTime(), new Vector2().x(527).y(53), 32, 2, BLACK);
		}
	    EndDrawing();
	}
	CloseWindow();
    }
}
