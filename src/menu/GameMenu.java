package menu;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.CloseWindow;
import static com.raylib.Raylib.DrawTextEx;
import static com.raylib.Raylib.LoadTexture;
import static com.raylib.Raylib.MeasureTextEx;

import com.raylib.Raylib.Font;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

import gui.Button;
import gui.OurColor;
import gui.Sprite;
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
    public void GameMenuLogic(boolean[] pages, Match match, boolean[] winner){

        if (pages[2] == true){

            if (tieButton.MouseClick()){
                match.getWhitePlayer().getClock().stopClock();
                match.getBlackPlayer().getClock().stopClock();
                pages[2] = false;
                pages[3] = true;

                winner[0] = true;
            }

            if (surrenderButton.MouseClick()){
                match.getWhitePlayer().getClock().stopClock();
                match.getBlackPlayer().getClock().stopClock();
                pages[2] = false;
                pages[3] = true;

                if (match.getCurrentTurnPlayer().getColor() == 'w')
                    winner[2] = true;
                else
                    winner[1] = true;
            }
        }
    }
}
