package menu;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Camera2D;
import com.raylib.Raylib.Texture;

import gui.*;
import vfx.Transition;
import game.Match;

public class GameMenu{

    // tie button
    private static Texture tieTexture = LoadTexture("res/botoes/empate.png");
    private Sprite tieSprite = new Sprite(tieTexture, 1, 0, 0, 0, WHITE, 1);
    private Button tieButton;

    // exit button
    private static Texture surrenderTexture = LoadTexture("res/botoes/desistir.png");
    private Sprite surrenderSprite = new Sprite(surrenderTexture, 1, 0, 0, 0, WHITE, 1);
    private Button surrenderButton;


    public GameMenu(){
        tieButton = new Button(26 + (int) tieSprite.GetWidth() / 2, 180 + (int) tieSprite.GetHeight() / 2, tieSprite);
        surrenderButton = new Button(26 + (int) surrenderSprite.GetWidth() / 2, 141 + (int) surrenderSprite.GetHeight() / 2, surrenderSprite);
    }

    // Do the menu logic and draws it
    public void GameMenuLogic(boolean[] pages, Match match, boolean[] winner, Transition transition, Camera2D camera2d){

        if (pages[2] == true){

            if (tieButton.MouseClick(camera2d) && !transition.GetActivated()){
                match.GetWhitePlayer().GetClock().StopClock();
                match.GetBlackPlayer().GetClock().StopClock();
                transition.CallTransition(2, 3);

                winner[0] = true;
            }

            if (surrenderButton.MouseClick(camera2d) && !transition.GetActivated()){
                match.GetWhitePlayer().GetClock().StopClock();
                match.GetBlackPlayer().GetClock().StopClock();
                transition.CallTransition(2, 3);

                if (match.GetCurrentTurnPlayer().GetColorPlayer() == 'w')
                    winner[2] = true;
                else
                    winner[1] = true;
            }
        }
    }
}
