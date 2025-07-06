package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.Vector;

import com.raylib.Raylib.Camera2D;
import com.raylib.Raylib.Font;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

import gui.*;
import vfx.Transition;
import game.*;

public class FinalMenu{

    private static Texture logoTexture = LoadTexture("res/ui/logo.png");

    // Go to main menu button
    private static Texture mainMenuTexture = LoadTexture("res/botoes/menu_inicial.png");
    private Sprite mainMenuSprite = new Sprite(mainMenuTexture, 1, 0, 0, 0, WHITE, 1);
    private Button mainMenuButton;

    // exit button
    private static Texture exitTexture = LoadTexture("res/botoes/sair.png");
    private Sprite exitSprite = new Sprite(exitTexture, 1, 0, 0, 0, WHITE, 1);
    private Button exitButton;

    private int screenCenter;

    private Font font;

    private int fontSize = 32;
    private int fontSpace = 1;

    private String textDuration;
    private Vector2 widthDuration;

    private String whitePlayerText;
    private Vector2 whitePlayerWidth;

    private String blackPlayerText;
    private Vector2 blackPlayerWidth;

    private String winnerText;
    private Vector2 winnerWidth;


    public FinalMenu(int screenWidth, Font font){
        screenCenter = screenWidth / 2;
        mainMenuButton = new Button(screenCenter, 280, mainMenuSprite);
        exitButton = new Button(screenCenter, 320, exitSprite);

        this.font = font;
    }

    // Do the menu logic and draws it
    public void finalMenuLogic(boolean[] pages, Match match, OptionsMenu optionsMenu, boolean[] winner, boolean[] isGameRunning, Transition transition, Camera2D camera2d){

        if (pages[3] == true){

            // Going back to main menu
            if (mainMenuButton.mouseClick(camera2d) && !transition.getActivated()){
                transition.callTransition(3, 0);
            }

            // Exiting
            if (exitButton.mouseClick(camera2d)){
                isGameRunning[0] = false;
            }

            drawFinalTexts(match, optionsMenu, winner);
        }
    }


    // Drawing the texts
    private void drawFinalTexts(Match match, OptionsMenu optionsMenu, boolean[] winner){

        if (winner[0])
            winnerText = "EMPATE";
        else if (winner[1])
            winnerText = "BRANCO VENCEU";
        else if (winner[2])
            winnerText = "PRETO VENCEU";

        winnerWidth = MeasureTextEx(font, winnerText, fontSize, fontSpace);
        DrawTextEx(font, winnerText,
        new Vector2().x(screenCenter - winnerWidth.x() / 2).y(21), fontSize, fontSpace,
        new OurColor(157, 204, 102, 255).getColor());


        // player info
        int timeDiffWhite = optionsMenu.convertToSeconds() - match.getWhitePlayer().getClock().getTime();
        int timeDiffBlack = optionsMenu.convertToSeconds() - match.getBlackPlayer().getClock().getTime();
        int finalTime = timeDiffWhite + timeDiffBlack;
        textDuration = String.format("TEMPO TOTAL: %02d:%02d", finalTime / 60, finalTime % 60);
        whitePlayerText = "TEMPO BRANCO: " + match.getWhitePlayer().getClock().formatTime();
        blackPlayerText = "TEMPO PRETO: " + match.getBlackPlayer().getClock().formatTime();

        widthDuration = MeasureTextEx(font, textDuration, fontSize, fontSpace);
        whitePlayerWidth = MeasureTextEx(font, whitePlayerText, fontSize, fontSpace);
        blackPlayerWidth = MeasureTextEx(font, blackPlayerText, fontSize, fontSpace);

        DrawTextEx(font, textDuration,
        new Vector2().x(screenCenter - widthDuration.x() / 2).y(98), fontSize, fontSpace, WHITE);

        DrawTextEx(font, whitePlayerText,
        new Vector2().x(screenCenter - whitePlayerWidth.x() / 2).y(137), fontSize, fontSpace, WHITE);

        DrawTextEx(font, blackPlayerText,
        new Vector2().x(screenCenter - blackPlayerWidth.x() / 2).y(175), fontSize, fontSpace, WHITE);
    }
}
