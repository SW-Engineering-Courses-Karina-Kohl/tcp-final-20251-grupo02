package vfx;

import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Cor;

public class EmissorParticulaFundo 
{
    private static Texture imagem = LoadTexture("res/vfx/particula3.png");
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private float direcao;
    private int minEscala;
    private int maxEscala;
    private float minVelocidade;
    private float maxVelocidade;
    private int larguraTela;
    private int alturaTela;
    private int margem;

    private ArrayList <Particula> listaParticulas = new ArrayList <Particula>();

    public EmissorParticulaFundo(int larguraTela, int alturaTela, int margem)
    {
        this.alturaTela = alturaTela;
        this.larguraTela = larguraTela;
        this.margem = margem;
        minX = margem;
        maxX = larguraTela - margem;
        minY = alturaTela + 10;
        maxY = alturaTela * 2;
        direcao = 270;
        minVelocidade = 1f;
        maxVelocidade = 3f;
        minEscala = 1;
        maxEscala = 4;

        for (int i = 0; i < 10; i++)
        {
            Particula particula = new Particula(imagem, minX, maxX, minY, maxY, direcao, direcao, false, 0, 0, minEscala, maxEscala
            , minVelocidade, maxVelocidade, 100, 100, false, 0, new Cor(255, 255, 255, 255));
            listaParticulas.add(particula);
        }

        
    }

    public void EmitirParicula()
    {
        if (listaParticulas.size() > 0)
        {
            for (int i = 0; i < listaParticulas.size(); i++)
            {
                listaParticulas.get(i).atualizaParticula();
                if (listaParticulas.get(i).GetY() <  -10)
                {
                    Particula particula = new Particula(imagem, minX, maxX, minY, maxY, direcao, direcao, false, 0, 0, minEscala, maxEscala
                    , minVelocidade, maxVelocidade, 100, 100, false, 0, new Cor(255, 255, 255, 255));
                    
                    listaParticulas.set(i, particula);
                }
            }
        }
    }
}
