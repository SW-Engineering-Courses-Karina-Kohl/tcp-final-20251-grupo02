package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import java.util.Random;

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
    private float escala;
    private float velocidade;
    private int vidaMaxima;
    private int vidaAtual;
    private boolean gravidade;
    private float velocidadeGravidade;
    private float velocidadeX;
    private float velocidadeY;

    public Particula(Sprite sprite, int minX, int maxX, int minY, int maxY, float minDirecao, float maxDirecao, boolean rotaciona,
    float minVelocidadeRotacao, float maxVelocidadeRotacao, float minEscala, float maxEscala, float minVelocidade, float maxVelocidade,
    int minVidaAtual, int maxVidaAtual, boolean gravidade, float velocidadeGravidade)
    {
        this.sprite = sprite;
        this.rotaciona = rotaciona;
        this.gravidade = gravidade;
        this.velocidadeGravidade = velocidadeGravidade;

        x = getRandomRangeInt(minX, maxX);
        y = getRandomRangeInt(minY, maxY);
        vidaMaxima = getRandomRangeInt(minVidaAtual, maxVidaAtual);
        vidaAtual = vidaMaxima;

        direcao = getRandomRangeFloat(minDirecao, maxDirecao);
        velocidade = getRandomRangeFloat(minVelocidade, maxVelocidade);
        escala = getRandomRangeFloat(minEscala, maxEscala);
        velocidadeRotacao = getRandomRangeFloat(minVelocidadeRotacao, maxVelocidadeRotacao);

        //Pegando o angulo
        float angulo = (float) (direcao * Math.PI) / 180; 
        velocidadeX = velocidade * (float) Math.cos(angulo);
        velocidadeY = velocidade * (float) Math.sin(angulo);
    }

    public void atualizaParticula()
    {
        x += velocidadeX;
        y += velocidadeY;
    }

    public int getRandomRangeInt(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public float  getRandomRangeFloat(float min, float max)
    {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }
}
