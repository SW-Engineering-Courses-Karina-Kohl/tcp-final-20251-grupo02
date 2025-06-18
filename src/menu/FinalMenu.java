package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.Vector;

import com.raylib.Raylib.Font;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

import gui.*;
import game.*;

public class FinalMenu
{
    private static Texture logoTexture = LoadTexture("res/ui/logo.png");

    //Button de voltar pro comeco
    private static Texture menuInicialTexture = LoadTexture("res/botoes/menu_inicial.png");
    private Sprite menuInicialSprite = new Sprite(menuInicialTexture, 1, 0, 0, 0, WHITE, 1);
    private Button menuInicialButton;

    //Button de sair
    private static Texture sairTextura = LoadTexture("res/botoes/sair.png");
    private Sprite sairSprite = new Sprite(sairTextura, 1, 0, 0, 0, WHITE, 1);
    private Button sairButton;

    private int larguraTela;
    private int centroTela;

    private Font fonte;

    private int tamanhoFonte = 32;
    private int espacoFonte = 1;

    private String duracaoTexto;
    private Vector2 duracaoLargura;

    private String timeBrancoTexto;
    private Vector2 timeBrancoLargura;

    private String timePretoTexto;
    private Vector2 timePretoLargura;

    private String vencedorTexto;
    private Vector2 vencedorLargura;


    public FinalMenu(int larguraTela, Font fonte)
    {
        this.larguraTela = larguraTela;
        centroTela = larguraTela / 2;
        menuInicialButton = new Button(centroTela, 280, menuInicialSprite);
        sairButton = new Button(centroTela, 320, sairSprite);

        this.fonte = fonte;

    }

    //Metodo que cuida de toda a logica do menu e desenha ele
    public void LogicaFinalMenu(boolean[] paginas, Match match, OptionsMenu opcoesMenu, boolean[] vencedor, boolean[] rodandoJogo)
    {
        if (paginas[3] == true)
        {
            //Voltando pro menu inicial
            if (menuInicialButton.MouseClick())
            {
                paginas[0] = true;
                paginas[3] = false;
            }

            //Saindo
            if (sairButton.MouseClick())
            {
                rodandoJogo[0] = false;
            }

            DrawTextosFinal(match, opcoesMenu, vencedor);
        }
    }


    //Desenhando os textos
    public void DrawTextosFinal(Match match, OptionsMenu opcoesMenu, boolean[] vencedor)
    {

        if (vencedor[0])
            vencedorTexto = "EMPATE";
        else if (vencedor[1])
            vencedorTexto = "BRANCO VENCEU";
        else if (vencedor[2])
            vencedorTexto = "PRETO VENCEU";

        vencedorLargura = MeasureTextEx(fonte, vencedorTexto, tamanhoFonte, espacoFonte);
        DrawTextEx(fonte, vencedorTexto,
        new Vector2().x(centroTela - vencedorLargura.x() / 2).y(21), tamanhoFonte, espacoFonte,
        new OurColor(157, 204, 102, 255).GetColor());


        //informacoes do time
        int timeDifBranco = opcoesMenu.ConverteParaSegundos() - match.GetPlayerBranco().GetClock().GetTime();
        int timeDifPreto = opcoesMenu.ConverteParaSegundos() - match.GetPlayerPreto().GetClock().GetTime();
        int timeFinal = timeDifBranco + timeDifPreto;
        duracaoTexto = String.format("TEMPO TOTAL: %02d:%02d", timeFinal / 60, timeFinal % 60);
        timeBrancoTexto = "TEMPO BRANCO: " + match.GetPlayerBranco().GetClock().FormatTime();
        timePretoTexto = "TEMPO PRETO: " + match.GetPlayerPreto().GetClock().FormatTime();

        duracaoLargura = MeasureTextEx(fonte, duracaoTexto, tamanhoFonte, espacoFonte);
        timeBrancoLargura = MeasureTextEx(fonte, timeBrancoTexto, tamanhoFonte, espacoFonte);
        timePretoLargura = MeasureTextEx(fonte, timePretoTexto, tamanhoFonte, espacoFonte);

        DrawTextEx(fonte, duracaoTexto,
        new Vector2().x(centroTela - duracaoLargura.x() / 2).y(98), tamanhoFonte, espacoFonte, WHITE);

        DrawTextEx(fonte, timeBrancoTexto,
        new Vector2().x(centroTela - timeBrancoLargura.x() / 2).y(137), tamanhoFonte, espacoFonte, WHITE);

        DrawTextEx(fonte, timePretoTexto,
        new Vector2().x(centroTela - timePretoLargura.x() / 2).y(175), tamanhoFonte, espacoFonte, WHITE);
    }
}
