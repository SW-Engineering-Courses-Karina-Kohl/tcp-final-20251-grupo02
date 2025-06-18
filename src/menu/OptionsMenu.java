package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.*;


public class OptionsMenu
{

    //Texto de doubleUp
    private static Texture opcoesTexture = LoadTexture("res/ui/opcoes.png");
    private Sprite opcoesSprite = new Sprite(opcoesTexture, 1, 0, 0, 0, WHITE, 1);

    //Texto de time
    private static Texture timeTexture = LoadTexture("res/ui/time.png");
    private Sprite timeSprite = new Sprite(timeTexture, 1, 0, 0, 0, WHITE, 1);

    //Button de voltar
    private static Texture voltarTexture = LoadTexture("res/botoes/voltar.png");
    private Sprite voltarSprite = new Sprite(voltarTexture, 1, 0, 0, 0, WHITE, 1);
    private Button voltarButton;

    //Botoes de alterar o time
    private static Texture setaUpTexture = LoadTexture("res/botoes/seta_doubleUp.png");
    private Sprite setaUpSprite = new Sprite(setaUpTexture, 1, 0, 0, 0, WHITE, 1);
    private Button[] setaUpButton = new Button[4];

    private Sprite setaDownSprite = new Sprite(setaUpTexture, 1, 180, 0, 0, WHITE, 1);
    private Button[] setaDownButton = new Button[4];

    private int larguraTela;
    private int centroTela;

    private int time = 0;
    private int[] timeSeparado = new int[4]; 
    private int[] numeros = new int[10];
    private int[] indexNumeros = new int[4];

    private Font fonte;

    public OptionsMenu(int larguraTela, Font fonte)
    {
        this.larguraTela = larguraTela;
        centroTela = larguraTela / 2;
        this.fonte = fonte;

        voltarButton = new Button(centroTela, 320, voltarSprite);
        for (int i = 0; i < 2; i++)
        {
            setaUpButton[i] = new Button(341 + (int) setaUpSprite.GetWidth() / 2 + i * 19,  82 + (int) setaUpSprite.GetHeight() / 2, setaUpSprite);
            setaDownButton[i] = new Button(341 + (int) setaDownSprite.GetWidth() / 2 + i * 19, 121, setaDownSprite);
        }

        for (int i = 2; i < 4; i++)
        {
            setaUpButton[i] = new Button(388 + (int) setaUpSprite.GetWidth() / 2 + (i - 2) * 19,  82 + (int) setaUpSprite.GetHeight() / 2, setaUpSprite);
            setaDownButton[i] = new Button(388 + (int) setaDownSprite.GetWidth() / 2 + (i - 2) * 19, 121, setaDownSprite);
        }

        for (int i = 0; i < 10; i++)
        {
            numeros[i] = i;
        }
        
        TempoInicial();
    }

    //Metodo que cuida de toda a logica do menu e desenha ele
    public void LogicaOptionsMenu(boolean[] paginas)
    {
        if (paginas[1] == true)
        {   
            opcoesSprite.DrawSpritePro(centroTela, 32);

            timeSprite.DrawSpritePro(centroTela - timeSprite.GetWidth() / 2, 104);

            //Voltando pro menu pricipal
            if (voltarButton.MouseClick())
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
            timeSeparado[i] = numeros[indexNumeros[i]];
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
        indexNumeros[i] += BoolToInt(setaUpButton[i].MouseClick());
        indexNumeros[i] -= BoolToInt(setaDownButton[i].MouseClick());

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
        DrawTextEx(fonte, String.format("%d", timeSeparado[0]),
        new Vector2().x(centroTela + 18).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, String.format("%d", timeSeparado[1]),
        new Vector2().x(centroTela + 18 + 19).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, ":",
        new Vector2().x(centroTela + 18 + 19 * 2).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, String.format("%d", timeSeparado[2]),
        new Vector2().x(centroTela + 18 + 19 * 2 + 9).y(90), 32, 1, WHITE);

        DrawTextEx(fonte, String.format("%d", timeSeparado[3]),
        new Vector2().x(centroTela + 18 + 19 * 3 + 9).y(90), 32, 1, WHITE);
    }

    public int ConverteParaSegundos()
    {
        //Minutos
        time = (timeSeparado[0] * 10 * 60) 
        + (timeSeparado[1] * 60)
        + (timeSeparado[2] * 10)
        + (timeSeparado[3]);

        return time;
    }

    public void TempoInicial()
    {
        //Colocando um time inicial
        indexNumeros[0] = 0;
        indexNumeros[1] = 5;
        indexNumeros[2] = 0;
        indexNumeros[3] = 0;

        for (int i = 0; i < 4; i++)
        {
            timeSeparado[i] = numeros[indexNumeros[i]];
        }
    }
}