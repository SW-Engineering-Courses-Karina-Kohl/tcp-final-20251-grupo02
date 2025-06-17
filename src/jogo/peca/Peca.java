package jogo.peca;
import jogo.Jogada;
import jogo.Tabuleiro;
import misc.Pair;

import static com.raylib.Colors.WHITE;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.*;

public abstract class Peca{

    public final int SIZE = 8;
    public float posicao;
    public Pair posicaoTabuleiro;
    public char identificador;

    public Sprite sprite;

    public Peca(int x, int y, char id)
    {
        this.posicaoTabuleiro = new Pair(x, y);
        this.identificador = id;
    }

    public ArrayList<Pair> movimentos = new ArrayList<>();

    // retorna todos os movimentos que uma peça pode
    // se movimentar, independente se a casa está ocupada ou não
    public abstract ArrayList<Pair> MovimentosValidos(Tabuleiro Tabuleiro, boolean emCheque);

    public ArrayList<Pair> GetMovimentos(){
	return movimentos;
    }

    public void Mover(Jogada jogada)
    {
        this.posicaoTabuleiro = jogada.peca_capturada.posicaoTabuleiro;
    }

    public void DestruirPeca()
    {

    }

    public char GetCorPeca(){
	if(this instanceof Blank){
	    return '_';
	}
	if(Character.isLowerCase(this.identificador)){
	    return 'p';
	}
	return 'b';
    }

    public void print_movimentos_validos()
    {
        // this.MovimentosValidos(tabuleiro); //
        for (Pair p : movimentos) {
           System.out.println(p);
        }
    }

    public void DrawPeca(int xInicial, int yInicial)
    {
        if (sprite != null)
            sprite.DrawSpritePro(posicaoTabuleiro.x * sprite.GetWidth() + (sprite.GetWidth() / 2) + xInicial,
                                posicaoTabuleiro.y * sprite.GetHeight() + (sprite.GetHeight() / 2) + yInicial);
    }

    @Override
    public String toString() {
        return this.identificador + " " + this.posicaoTabuleiro;
    }

}
