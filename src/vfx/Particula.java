package vfx;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import java.util.Random;

import com.raylib.Raylib.Texture;

import gui.*;

public class Particula 
{
    private Sprite sprite;
    private int x;
    private int y;
    private Cor cor;
    private float direcao;
    private float rotacao;
    private boolean rotaciona;
    private float velocidadeRotacao;
    private int escala;
    private float velocidade;
    private int vidaMaxima;
    private int vidaAtual;
    private boolean gravidade;
    private float velocidadeGravidade;
    private float velocidadeX;
    private float velocidadeY;

    public Particula(Texture imagem, int minX, int maxX, int minY, int maxY, float minDirecao, float maxDirecao, boolean rotaciona,
    float minVelocidadeRotacao, float maxVelocidadeRotacao, int minEscala, int maxEscala, float minVelocidade, float maxVelocidade,
    int minVidaAtual, int maxVidaAtual, boolean gravidade, float velocidadeGravidade, Cor cor)
    {
        this.rotaciona = rotaciona;
        this.gravidade = gravidade;
        this.velocidadeGravidade = velocidadeGravidade;

        x = getRandomRangeInt(minX, maxX);
        y = getRandomRangeInt(minY, maxY);
        vidaMaxima = getRandomRangeInt(minVidaAtual, maxVidaAtual);
        vidaAtual = vidaMaxima;

        direcao = getRandomRangeFloat(minDirecao, maxDirecao);
        velocidade = getRandomRangeFloat(minVelocidade, maxVelocidade);
        escala = getRandomRangeInt(minEscala, maxEscala);
        velocidadeRotacao = getRandomRangeFloat(minVelocidadeRotacao, maxVelocidadeRotacao);

        //Pegando o angulo
        float angulo = (float) (direcao * Math.PI) / 180; 
        velocidadeX = velocidade * (float) Math.cos(angulo);
        velocidadeY = velocidade * (float) Math.sin(angulo);

        this.cor = cor;

        sprite = new Sprite(imagem, escala, angulo, 0, 0, cor.GetCor(), 1);
    }

    public void atualizaParticula()
    {
        // Andando
        x += velocidadeX;
        y += velocidadeY;

        //Gravidade
        if (gravidade)
        {
            velocidadeY += velocidadeGravidade;
        }

        //Rotacionando
        if (rotaciona)
        {
            sprite.IncrementaAngulo(velocidadeRotacao);
        }

        //Se desenhando
        sprite.DrawSpritePro(x, y);
    }

    public int getRandomRangeInt(int min, int max)
    {
        Random random = new Random();
        if (min == max)
            return min;
        return random.nextInt(max - min) + min;
    }

    public float  getRandomRangeFloat(float min, float max)
    {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    public int GetY()
    {
        return y;
    }
}
