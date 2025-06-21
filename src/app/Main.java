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

import com.raylib.Raylib.Camera2D;
import com.raylib.Raylib.Font;
import com.raylib.Raylib.Vector2;

public class Main {

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

    public static void main(String[] args) {

	InitWindow(1280, 720, "Tabuleiro de Combate de Peças");
	InitAudioDevice();
	SetTargetFPS(60);


	Match match = new Match(300);

	int clicks = 0;
	Piece movedPiece = new Blank(0, 0);

	BackgroundParticlesEmitter particleEmitter = new BackgroundParticlesEmitter(WIDTH, HEIGHT, PARTICLE_MARGIN);
	Font pixelFont = LoadFont("res/fonts/Pixellari.ttf");

	// The page array function as follow:
	// Each position represents one page, being a bool, telling if the page is
	// active or not
	// It means, usually there will be only one page active at the moment.

	// The order is:
	// Main menu, options, game and final menu
	boolean[] pages = new boolean[4];
	pages[MENU] = true;

	// Flag that indicates to start a new match
	boolean startNewMatch = false;
	boolean keepTrue = false;

	MainMenu mainMenu = new MainMenu(WIDTH);
	OptionsMenu optionsMenu = new OptionsMenu(WIDTH, pixelFont);
	FinalMenu finalMenu = new FinalMenu(WIDTH, pixelFont);
	GameMenu gameMenu = new GameMenu();

	// 0 = tie; 1 = white; 2 = black;
	boolean[] winner = new boolean[3];

	// Ponteiro de novo
	boolean[] isGameRunning = new boolean[1];
	isGameRunning[0] = true;

	//Transition
	Transition transition = new Transition(new OurColor(0, 0, 0, 255), WIDTH, HEIGHT, 10, 0);

	//Flash
	Flash flash = new Flash(new OurColor(255, 255, 255, 255), WIDTH, HEIGHT, 20);
	BloodParticlesEmitter bloodParticlesEmitter = new BloodParticlesEmitter();

	//Move para fazer a promocão
	Move movePromotion = new Move(movedPiece, movedPiece);
	boolean doPromotion = false;

	Vector2 target = new Vector2().x(640/2).y(360/2);
	Vector2 offset = new Vector2().x(1280 / 2).y(720 / 2);
	Camera2D camera2d = new Camera2D().target(target).zoom(2).offset(offset).rotation(0);

	//Carrendo a musica
	Music mainMusic = LoadMusicStream("res/musics/main.mp3");
	SetMusicVolume(mainMusic, 0.1f);
	PlayMusicStream(mainMusic);

	//Carregando sfx
	Sound moveSound = LoadSound("res/sfx/snd_move.wav");
	SetSoundVolume(moveSound, .2f);

	Sound hitSound = LoadSound("res/sfx/snd_hit.mp3");
	SetSoundVolume(hitSound, .2f);

	while (!WindowShouldClose() && isGameRunning[0]) {
		UpdateMusicStream(mainMusic);

	    BeginDrawing();
	    ClearBackground(new OurColor(52, 54, 71, 255).getColor());
	    BeginMode2D(camera2d);
	    particleEmitter.sendParticle();

	    startNewMatch = mainMenu.mainMenuLogic(pages, isGameRunning, transition, camera2d);

	    if (startNewMatch)
		keepTrue = true;

	    if (keepTrue)
		startNewMatch = true;

	    optionsMenu.optionsMenuLogic(pages, transition, camera2d);
	    finalMenu.finalMenuLogic(pages, match, optionsMenu, winner, isGameRunning, transition, camera2d);

	    if (pages[GAME] == true) {

		gameMenu.gameMenuLogic(pages, match, winner, transition, camera2d);


		// creating new match
		if (startNewMatch == true){
		    match = new Match(optionsMenu.convertToSeconds());
		    movedPiece = new Blank(0, 0);

		    for (int i = 0; i < 3; i++){
			winner[i] = false;
		    }
		    startNewMatch = false;
		    keepTrue = false;
		}

		Board board = match.getBoard();
		Player whitePlayer = match.getWhitePlayer();
		Player blackPlayer = match.getBlackPlayer();
		board.drawGrid(INITIALX, INITIALY, SCALE);

		if(whitePlayer.getClock().isTimeZero()) {
			winner[2] = true;
			whitePlayer.getClock().stopClock();
			blackPlayer.getClock().stopClock();
			transition.callTransition(GAME, FINAL);
		}
		if(blackPlayer.getClock().isTimeZero()) {
			winner[1] = true;
			whitePlayer.getClock().stopClock();
			blackPlayer.getClock().stopClock();
			transition.callTransition(GAME, FINAL);
		}

		if (board.mouseClikedOnBoard(INITIALX, INITIALY, SCALE, camera2d)) {

		    System.out.println(board.toString());

		    Pair pos = board.getMousePositionOnBoard(INITIALX, INITIALY, SCALE, camera2d);
		    if (!doPromotion)
			{
			    if (clicks == 0) {
				if (board.getPieceInPosition(pos).findPieceColor() == match.getCurrentTurnPlayer().getColor()) {
				    movedPiece = board.getPieceInPosition(pos);
				    movedPiece.validMoviments(board, true);
				    clicks = 1;
				}
			    } else if (clicks == 1) {

				Piece destinePiece = board.getPieceInPosition(pos);
				// Verificação da move
				Move move = new Move(movedPiece, destinePiece);

				if (move.validateMove(board)) {

				    Piece movimentOriginalPosition = new Blank(movedPiece.getBoardPosition().x, movedPiece.getBoardPosition().y);

				    //Chamando o flash
				    if ((destinePiece.getPieceID() != '_' && movedPiece.findPieceColor() != destinePiece.findPieceColor()) || (movedPiece instanceof Pawn && destinePiece.getBoardPosition().isEqualsTo(((Pawn) movedPiece).getEnPassantPosition())))
					{
						PlaySound(hitSound);
					    flash.callFlash();
					    OurColor colorBlood = new OurColor(255, 255, 255, 255);
					    if (destinePiece.findPieceColor() == 'b')
						colorBlood = new OurColor(0, 0, 0, 255);
					    bloodParticlesEmitter.createParticles(INITIALX + pos.x * 16 * SCALE + 16 * SCALE / 2, INITIALY + pos.y * 16 * SCALE + 16 * SCALE / 2, 20, colorBlood);
					}
					else
					{
						PlaySound(moveSound);
					}

				    board.executeMove(move);

				    // Save last move (used for en passant)
				    board.setLastMove(new Move(movimentOriginalPosition, movedPiece));

				    // Verify if the players are in check
				    whitePlayer.setCheckStatus(board.checkCheck('w'));
				    blackPlayer.setCheckStatus(board.checkCheck('b'));

				    if (move.getMovedPiece() instanceof Pawn) {
					doPromotion = move.checkPawnPromotion(board);
					movePromotion = move;
				    }

				    // end turn
				    match.nextTurn();
				    clicks = 0;

				    if(whitePlayer.isInCheck()){
					if(board.checkCheckmate('w')){
					    winner[2] = true;
					    whitePlayer.getClock().stopClock();
					    blackPlayer.getClock().stopClock();
					    transition.callTransition(GAME, FINAL);
					}
				    }

				    if(blackPlayer.isInCheck()){
					if(board.checkCheckmate('b')){
					    winner[1] = true;
					    whitePlayer.getClock().stopClock();
					    blackPlayer.getClock().stopClock();
					    transition.callTransition(GAME, FINAL);
					}
				    }

				} else {
				    // changes the piece if the players clicks on one of the same color
				    if (destinePiece.findPieceColor() == movedPiece.findPieceColor()) {
					movedPiece = board.getPieceInPosition(pos);
					movedPiece.validMoviments(board, true);
				    } else {
					movedPiece = new Blank(0, 0);
					clicks = 0;
				    }
				}
			    }
			}
		}

		if (IsMouseButtonPressed(1))
		    clicks = 0;

		if (clicks == 1) {
		    board.drawValidMoviments(movedPiece, INITIALX, INITIALY, SCALE, camera2d);
		}

		board.drawPieces(INITIALX, INITIALY, whitePlayer.isInCheck(), blackPlayer.isInCheck());
		DrawTextEx(pixelFont, whitePlayer.getClock().formatTime(), new Vector2().x(527).y(21), 32, 2,
			   WHITE);
		DrawTextEx(pixelFont, blackPlayer.getClock().formatTime(), new Vector2().x(527).y(53), 32, 2,
			   BLACK);
		bloodParticlesEmitter.updateParticles();
		if (doPromotion)
		    {
			if (movePromotion.DoPromotion(board, camera2d))
			    {
				flash.callFlash();
				doPromotion = false;
				whitePlayer.setCheckStatus(board.checkCheck('w'));
				blackPlayer.setCheckStatus(board.checkCheck('b'));
			    }
		    }
	    }
	    transition.updateTransition(pages);
	    flash.updateFlash();
	    EndMode2D();
	    EndDrawing();
	}
	CloseWindow();
    }
}
