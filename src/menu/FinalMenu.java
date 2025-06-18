package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.Vector;

import com.raylib.Raylib.Font;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

import gui.*;
import jogo.*;

public class FinalMenu
{
    private static Texture logoTexture = LoadTexture("res/ui/logo.png");

    //Botao de voltar pro comeco
    private static Texture menuInicialTexture = LoadTexture("res/botoes/menu_inicial.png");
    private Sprite menuInicialSprite = new Sprite(menuInicialTexture, 1, 0, 0, 0, WHITE, 1);
    private Botao menuInicialBotao;

    //Botao de sair
    private static Texture sairTextura = LoadTexture("res/botoes/sair.png");
    private Sprite sairSprite = new Sprite(sairTextura, 1, 0, 0, 0, WHITE, 1);
    private Botao sairBotao;

    private int larguraTela;
    private int centroTela;

    private Font fonte;

    private int tamanhoFonte = 32;
    private int espacoFonte = 1;

    private String duracaoTexto;
    private Vector2 duracaoLargura;

    private String tempoBrancoTexto;
    private Vector2 tempoBrancoLargura;

    private String tempoPretoTexto;
    private Vector2 tempoPretoLargura;

    private String vencedorTexto;
    private Vector2 vencedorLargura;


    public FinalMenu(int larguraTela, Font fonte)
    {
        this.larguraTela = larguraTela;
        centroTela = larguraTela / 2;
        menuInicialBotao = new Botao(centroTela, 280, menuInicialSprite);
        sairBotao = new Botao(centroTela, 320, sairSprite);

        this.fonte = fonte;

    }

    //Metodo que cuida de toda a logica do menu e desenha ele
    public void LogicaFinalMenu(boolean[] paginas, Match match, OptionsMenu opcoesMenu, boolean[] vencedor, boolean[] rodandoJogo)
    {
        if (paginas[3] == true)
        {
            //Voltando pro menu inicial
            if (menuInicialBotao.MouseClick())
            {
                paginas[0] = true;
                paginas[3] = false;
            }

            //Saindo
            if (sairBotao.MouseClick())
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
        new Cor(157, 204, 102, 255).GetCor());


        //informacoes do tempo
        int tempoDifBranco = opcoesMenu.ConverteParaSegundos() - match.GetPlayerBranco().GetClock().getTempo();
        int tempoDifPreto = opcoesMenu.ConverteParaSegundos() - match.GetPlayerPreto().GetClock().getTempo();
        int tempoFinal = tempoDifBranco + tempoDifPreto;
        duracaoTexto = String.format("TEMPO TOTAL: %02d:%02d", tempoFinal / 60, tempoFinal % 60);
        tempoBrancoTexto = "TEMPO BRANCO: " + match.GetPlayerBranco().GetClock().formatarTempo();
        tempoPretoTexto = "TEMPO PRETO: " + match.GetPlayerPreto().GetClock().formatarTempo();

        duracaoLargura = MeasureTextEx(fonte, duracaoTexto, tamanhoFonte, espacoFonte);
        tempoBrancoLargura = MeasureTextEx(fonte, tempoBrancoTexto, tamanhoFonte, espacoFonte);
        tempoPretoLargura = MeasureTextEx(fonte, tempoPretoTexto, tamanhoFonte, espacoFonte);

        DrawTextEx(fonte, duracaoTexto,
        new Vector2().x(centroTela - duracaoLargura.x() / 2).y(98), tamanhoFonte, espacoFonte, WHITE);

        DrawTextEx(fonte, tempoBrancoTexto,
        new Vector2().x(centroTela - tempoBrancoLargura.x() / 2).y(137), tamanhoFonte, espacoFonte, WHITE);

        DrawTextEx(fonte, tempoPretoTexto,
        new Vector2().x(centroTela - tempoPretoLargura.x() / 2).y(175), tamanhoFonte, espacoFonte, WHITE);
    }
}
