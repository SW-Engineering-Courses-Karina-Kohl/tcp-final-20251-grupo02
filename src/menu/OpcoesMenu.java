package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.*;


public class OpcoesMenu
{

    //Texto de cima
    private static Texture opcoesTexture = LoadTexture("res/UI/opcoes.png");
    private Sprite opcoesSprite = new Sprite(opcoesTexture, 1, 0, 0, 0, WHITE, 1);

    //Texto de tempo
    private static Texture tempoTexture = LoadTexture("res/UI/tempo.png");
    private Sprite tempoSprite = new Sprite(tempoTexture, 1, 0, 0, 0, WHITE, 1);

    //Botao de voltar
    private static Texture voltarTexture = LoadTexture("res/botoes/voltar.png");
    private Sprite voltarSprite = new Sprite(voltarTexture, 1, 0, 0, 0, WHITE, 1);
    private Botao voltarBotao;

    //Botoes de alterar o tempo
    private static Texture setaCimaTexture = LoadTexture("res/botoes/seta_cima.png");
    private Sprite setaCimaSprite = new Sprite(setaCimaTexture, 1, 0, 0, 0, WHITE, 1);
    private Botao[] setaCimaBotao = new Botao[4];

    private Sprite setaBaixoSprite = new Sprite(setaCimaTexture, 1, 180, 0, 0, WHITE, 1);
    private Botao[] setaBaixoBotao = new Botao[4];

    private int larguraTela;
    private int centroTela;

    private int tempo = 0;
    private int[] tempoSeparado = new int[4]; 
    private int[] numeros = new int[10];
    private int[] indexNumeros = new int[4];

    private Font fonte;

    public OpcoesMenu(int larguraTela, Font fonte)
    {
        this.larguraTela = larguraTela;
        centroTela = larguraTela / 2;
        this.fonte = fonte;

        voltarBotao = new Botao(centroTela, 320, voltarSprite);
        for (int i = 0; i < 2; i++)
        {
            setaCimaBotao[i] = new Botao(341 + (int) setaCimaSprite.GetWidth() / 2 + i * 19,  82 + (int) setaCimaSprite.GetHeight() / 2, setaCimaSprite);
            setaBaixoBotao[i] = new Botao(341 + (int) setaBaixoSprite.GetWidth() / 2 + i * 19, 121, setaBaixoSprite);
        }

        for (int i = 2; i < 4; i++)
        {
            setaCimaBotao[i] = new Botao(388 + (int) setaCimaSprite.GetWidth() / 2 + (i - 2) * 19,  82 + (int) setaCimaSprite.GetHeight() / 2, setaCimaSprite);
            setaBaixoBotao[i] = new Botao(388 + (int) setaBaixoSprite.GetWidth() / 2 + (i - 2) * 19, 121, setaBaixoSprite);
        }

        for (int i = 0; i < 10; i++)
        {
            numeros[i] = i;
        }
        
        TempoInicial();
    }

    //Metodo que cuida de toda a logica do menu e desenha ele
    public void LogicaOpcoesMenu(boolean[] paginas)
    {
        if (paginas[1] == true)
        {   
            opcoesSprite.DrawSpritePro(centroTela, 32);

            tempoSprite.DrawSpritePro(centroTela - tempoSprite.GetWidth() / 2, 104);

            //Voltando pro menu pricipal
            if (voltarBotao.MouseClick())
            {
                paginas[1] = false;
                paginas[0] = true;
            }

            AlterandoTempo();
        }
    }

    public void AlterandoTempo()
    {  
        LoopIndex(0, 6, 0);
        LoopIndex(0, 9, 1);
        LoopIndex(0, 5, 2);
        LoopIndex(0, 9, 3);

        //Limitando em 1h
        if (indexNumeros[0] == 6)
        {
            indexNumeros[0] = 6;
            indexNumeros[1] = 0;
            indexNumeros[2] = 0;
            indexNumeros[3] = 0;
        }

        for (int i = 0; i < 4; i++)
        {
            tempoSeparado[i] = numeros[indexNumeros[i]];
        }

        DrawTempo();
    }

    public int BoolToInt(boolean bool)
    {
        if (bool)
            return 1;
        else
            return 0;
    }

    public void LoopIndex(int min, int max, int i)
    {
        indexNumeros[i] += BoolToInt(setaCimaBotao[i].MouseClick());
        indexNumeros[i] -= BoolToInt(setaBaixoBotao[i].MouseClick());

        if (indexNumeros[i] > max)
        {
            indexNumeros[i] = min;
        }

        if (indexNumeros[i] < min)
        {
            indexNumeros[i] = max;
        }
    }

    public void DrawTempo()
    {
        DrawTextEx(fonte, String.format("%d", tempoSeparado[0]),
        new Vector2().x(centroTela + 18).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, String.format("%d", tempoSeparado[1]),
        new Vector2().x(centroTela + 18 + 19).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, ":",
        new Vector2().x(centroTela + 18 + 19 * 2).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, String.format("%d", tempoSeparado[2]),
        new Vector2().x(centroTela + 18 + 19 * 2 + 9).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, String.format("%d", tempoSeparado[3]),
        new Vector2().x(centroTela + 18 + 19 * 3 + 9).y(90), 32, 1, WHITE);
    }

    public int ConverteParaSegundos()
    {
        //Minutos
        tempo = (tempoSeparado[0] * 10 * 60) 
        + (tempoSeparado[1] * 60)
        + (tempoSeparado[2] * 10)
        + (tempoSeparado[3]);

        return tempo;
    }

    public void TempoInicial()
    {
        //Colocando um tempo inicial
        indexNumeros[0] = 0;
        indexNumeros[1] = 5;
        indexNumeros[2] = 0;
        indexNumeros[3] = 0;

        for (int i = 0; i < 4; i++)
        {
            tempoSeparado[i] = numeros[indexNumeros[i]];
        }
    }
}