package vfx;

import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.OurColor;

public class BackgroundParticlesEmitter 
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

    private ArrayList <Particle> listaParticles = new ArrayList <Particle>();

    public BackgroundParticlesEmitter(int larguraTela, int alturaTela, int margem)
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
            Particle particula = new Particle(imagem, minX, maxX, minY, maxY, direcao, direcao, false, 0, 0, minEscala, maxEscala
            , minVelocidade, maxVelocidade, 100, 100, false, 0, new OurColor(255, 255, 255, 255));
            listaParticles.add(particula);
        }

        
    }

    public void EmitirParicula()
    {
        if (listaParticles.size() > 0)
        {
            for (int i = 0; i < listaParticles.size(); i++)
            {
                listaParticles.get(i).atualizaParticle();
                if (listaParticles.get(i).GetY() <  -10)
                {
                    Particle particula = new Particle(imagem, minX, maxX, minY, maxY, direcao, direcao, false, 0, 0, minEscala, maxEscala
                    , minVelocidade, maxVelocidade, 100, 100, false, 0, new OurColor(255, 255, 255, 255));
                    
                    listaParticles.set(i, particula);
                }
            }
        }
    }
}
