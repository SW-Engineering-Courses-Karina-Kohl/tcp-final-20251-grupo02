package menu;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.CloseWindow;
import static com.raylib.Raylib.DrawTextEx;
import static com.raylib.Raylib.LoadTexture;
import static com.raylib.Raylib.MeasureTextEx;

import com.raylib.Raylib.Font;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

import gui.Botao;
import gui.Cor;
import gui.Sprite;
import jogo.Match;

public class JogoMenu
{
    //Botao sugerir empate
    private static Texture empateTexture = LoadTexture("res/botoes/empate.png");
    private Sprite empateSprite = new Sprite(empateTexture, 1, 0, 0, 0, WHITE, 1);
    private Botao empateBotao;

    //Botao de sair
    private static Texture desistirTextura = LoadTexture("res/botoes/desistir.png");
    private Sprite desistirSprite = new Sprite(desistirTextura, 1, 0, 0, 0, WHITE, 1);
    private Botao desistirBotao;


    public JogoMenu()
    {

        empateBotao = new Botao(26 + (int) empateSprite.GetWidth() / 2, 180 + (int) empateSprite.GetHeight() / 2, empateSprite);
        desistirBotao = new Botao(26 + (int) desistirSprite.GetWidth() / 2, 141 + (int) desistirSprite.GetHeight() / 2, desistirSprite);

    }

    //Metodo que cuida de toda a logica do menu e desenha ele
    public void LogicaJogoMenu(boolean[] paginas, Match match, boolean[] vencedor)
    {
        if (paginas[2] == true)
        {
            //Botao de empate
            if (empateBotao.MouseClick())
            {
                match.GetPlayerBranco().GetClock().PausaClock();
                match.GetPlayerPreto().GetClock().PausaClock();
                paginas[2] = false;
                paginas[3] = true;

                vencedor[0] = true;
            }

            //Desistindo
            if (desistirBotao.MouseClick())
            {
                match.GetPlayerBranco().GetClock().PausaClock();
                match.GetPlayerPreto().GetClock().PausaClock();
                paginas[2] = false;
                paginas[3] = true;

                if (match.GetPlayerTurnoAtual().GetCorPlayer() == 'b')
                    vencedor[2] = true;
                else
                    vencedor[1] = true;
            }

        }
    }
}
