package gui;
import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Color;
import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

public class Sprite 
{
    private Texture textura;
    private float escala, angulo;
    private OurRectangle corte;
    private int imagem_velocidade;
    private int imagem_atual;
    private int contador = 0;
    private Color cor;
    private int qtd_imagens;

    public Sprite(Texture textura, float escala, float angulo, int imagem_velocidade, int imagem_atual, Color cor, int qtd_imagens)
    {
        this.textura = textura;
        this.escala = escala;
        this.angulo = angulo;
        this.imagem_velocidade = imagem_velocidade;
        this.imagem_atual = imagem_atual;
        this.cor = cor;

        //corte = new Rectangle().x(0).y(0).width(this.textura.width() / qtd_imagens).height(this.textura.height());
        corte = new OurRectangle(0, 0, this.textura.width() / qtd_imagens, this.textura.height());
    }


    public void DrawSpritePro(float x, float y)
    {
        // Atualizando a sprite
        // Rodando o timer
        if (imagem_velocidade != 0)
        {
            contador++;

            if (contador >= (60 / imagem_velocidade))
            {
                // Mudando a imagem
                imagem_atual = (imagem_atual + 1) % (int)(textura.width() / Math.abs(corte.GetWidth()));
                corte.SetX((float)imagem_atual * (float)(textura.width() / (textura.width() / Math.abs(corte.GetWidth()))));
                contador = 0;
            }
        }
        else
        {
            corte.SetX((float) imagem_atual * (float)(textura.width() / (textura.width() / Math.abs(corte.GetWidth()))));
        }
        

        Rectangle rec_sprite = new Rectangle()
        .x(x)
        .y(y)
        .width(GetWidth())
        .height(GetHeight());

        Vector2 pivot = new Vector2()
        .x((float) (textura.width() / (textura.width() / Math.abs(corte.GetWidth())) / 2) * escala)
        .y((float) textura.height() * escala / 2);
        
        // Desenhando
        DrawTexturePro(textura, corte.GetOurRectangle(), rec_sprite, pivot, angulo, cor);
    }

    public float GetWidth()
    {
        return (float) (textura.width() / (textura.width() / Math.abs(corte.GetWidth()))) * escala;
    }

    public float GetHeight()
    {
        return (float) textura.height() * escala;
    }

    public float GetEscala()
    {
        return escala;
    }

    public void SetEscala(float novaEscala)
    {
        escala = novaEscala;
    }

    // Calculate linear interpolation between two floats
    public float Lerp(float inicio, float fim, float quantia)
    {
        float resultado = inicio + quantia*(fim - inicio);

        return resultado;
    }

    public void SetCurrentImage(int novaImagemAtual)
    {
        imagem_atual = novaImagemAtual;
    }

    public void SetOurColor(Color novaOurColor)
    {
        cor = novaOurColor;
    }

    public void SetAngulo(float novoAngulo)
    {
        angulo = novoAngulo;
    }

    public void IncrementaAngulo(float incrmento)
    {
        angulo += incrmento;
    }
}