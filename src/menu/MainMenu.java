package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import com.raylib.Raylib.Camera2D;
import com.raylib.Raylib.Texture;

import gui.*;
import vfx.Transition;


public class MainMenu{

    private static Texture logoTexture = LoadTexture("res/ui/logo.png");
    private Sprite logoSprite = new Sprite(logoTexture, 1, 0, 0, 0, WHITE, 1);

    // new game button
    private static Texture newGameTexture = LoadTexture("res/botoes/novo_jogo.png");
    private Sprite newGameSprite = new Sprite(newGameTexture, 1, 0, 0, 0, WHITE, 1);
    private Button newGameButton;

    // options button
    private static Texture optionsTexture = LoadTexture("res/botoes/opcoes.png");
    private Sprite optionsSprite = new Sprite(optionsTexture, 1, 0, 0, 0, WHITE, 1);
    private Button optionsButton;

    // exit button
    private static Texture exitTexture = LoadTexture("res/botoes/sair.png");
    private Sprite exitSprite = new Sprite(exitTexture, 1, 0, 0, 0, WHITE, 1);
    private Button exitButton;

    private int screenCenter;

    public MainMenu(int screenWidth){
        screenCenter = screenWidth / 2;
        newGameButton = new Button(screenCenter, 240, newGameSprite);
        optionsButton = new Button(screenCenter, 280, optionsSprite);
        exitButton = new Button(screenCenter, 320, exitSprite);
    }

    // Do the menu logic and draws it
    public boolean MainMenuLogic(boolean[] pages, boolean[] isGameRunning, Transition transition, Camera2D camera2d){

        boolean startNewMatch = false;
        if (pages[0] == true){

            // Creating a new game
            if (newGameButton.MouseClick(camera2d) && !transition.GetActivated()){
                startNewMatch = true;
                transition.CallTransition(0, 2);
            }

            // Going to options menu
            if (optionsButton.MouseClick(camera2d) && !transition.GetActivated()){
                transition.CallTransition(0, 1);
            }

            // Exiting
            if (exitButton.MouseClick(camera2d)){
                isGameRunning[0] = false;
            }

            logoSprite.DrawSpritePro(screenCenter, 100);
        }
        System.err.println(startNewMatch);
        return startNewMatch;
    }

}
