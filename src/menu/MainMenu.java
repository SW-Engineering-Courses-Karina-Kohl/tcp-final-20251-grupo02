package menu;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.*;


public class MainMenu
{

    private static Texture logoTexture = LoadTexture("res/ui/logo.png");
    private Sprite logoSprite = new Sprite(logoTexture, 1, 0, 0, 0, WHITE, 1);

    //Botao de novo jogo
    private static Texture novoJogoTextura = LoadTexture("res/botoes/novo_jogo.png");
    private Sprite novoJogoSprite = new Sprite(novoJogoTextura, 1, 0, 0, 0, WHITE, 1);
    private Botao novoJogoBotao;

    //Botao de opcoes
    private static Texture opcoesTextura = LoadTexture("res/botoes/opcoes.png");
    private Sprite opcoesSprite = new Sprite(opcoesTextura, 1, 0, 0, 0, WHITE, 1);
    private Botao opcoesBotao;

    //Botao de sair
    private static Texture sairTextura = LoadTexture("res/botoes/sair.png");
    private Sprite sairSprite = new Sprite(sairTextura, 1, 0, 0, 0, WHITE, 1);
    private Botao sairBotao;

    private int larguraTela;
    private int centroTela;

    public MainMenu(int larguraTela)
    {
        this.larguraTela = larguraTela;
        centroTela = larguraTela / 2;
        novoJogoBotao = new Botao(centroTela, 240, novoJogoSprite);
        opcoesBotao = new Botao(centroTela, 280, opcoesSprite);
        sairBotao = new Botao(centroTela, 320, sairSprite);
    }

    //Metodo que cuida de toda a logica do menu e desenha ele
    public boolean LogicaMainMenu(boolean[] paginas, boolean[] rodandoJogo)
    {
        boolean criarJogo = false;
        if (paginas[0] == true)
        {   
            //Iniciando um jogo novo
            if (novoJogoBotao.MouseClick())
            {
                criarJogo = true;
                paginas[0] = false;
                paginas[2] = true;
            }

            //Indo pro menu de opcoes
            if (opcoesBotao.MouseClick())
            {
                paginas[0] = false;
                paginas[1] = true;
            }

            //Saindo
            if (sairBotao.MouseClick())
            {
                rodandoJogo[0] = false;
            }

            logoSprite.DrawSpritePro(centroTela, 100);
        }
        return criarJogo;
    }
    
}