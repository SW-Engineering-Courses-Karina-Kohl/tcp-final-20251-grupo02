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

public class GameMenu
{
    //Button sugerir empate
    private static Texture empateTexture = LoadTexture("res/botoes/empate.png");
    private Sprite empateSprite = new Sprite(empateTexture, 1, 0, 0, 0, WHITE, 1);
    private Button empateButton;

    //Button de sair
    private static Texture desistirTextura = LoadTexture("res/botoes/desistir.png");
    private Sprite desistirSprite = new Sprite(desistirTextura, 1, 0, 0, 0, WHITE, 1);
    private Button desistirButton;


    public GameMenu()
    {

        empateButton = new Button(26 + (int) empateSprite.GetWidth() / 2, 180 + (int) empateSprite.GetHeight() / 2, empateSprite);
        desistirButton = new Button(26 + (int) desistirSprite.GetWidth() / 2, 141 + (int) desistirSprite.GetHeight() / 2, desistirSprite);

    }

    //Metodo que cuida de toda a logica do menu e desenha ele
    public void LogicaGameMenu(boolean[] paginas, Match match, boolean[] vencedor)
    {
        if (paginas[2] == true)
        {
            //Button de empate
            if (empateButton.MouseClick())
            {
                match.GetPlayerBranco().GetClock().StopClock();
                match.GetPlayerPreto().GetClock().StopClock();
                paginas[2] = false;
                paginas[3] = true;

                vencedor[0] = true;
            }

            //Desistindo
            if (desistirButton.MouseClick())
            {
                match.GetPlayerBranco().GetClock().StopClock();
                match.GetPlayerPreto().GetClock().StopClock();
                paginas[2] = false;
                paginas[3] = true;

                if (match.GetPlayerTurnoAtual().GetColorPlayer() == 'w')
                    vencedor[2] = true;
                else
                    vencedor[1] = true;
            }

        }
    }
}
