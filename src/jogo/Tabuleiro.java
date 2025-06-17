package jogo;
import static com.raylib.Colors.GREEN;
import static com.raylib.Colors.RED;
import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.DrawRectangle;
import static com.raylib.Raylib.GetMouseX;
import static com.raylib.Raylib.GetMouseY;
import static com.raylib.Raylib.IsMouseButtonPressed;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Color;
import com.raylib.Raylib.Texture;

import gui.Cor;
import gui.Sprite;
import jogo.peca.*;
import misc.Pair;

public class Tabuleiro
{

    private int PecasNoTabuleiro = 32;
    private static final int SIZE = 8;

    //Dando load nas imagens
    //Isso será um problema no futuro se a gente quiser inicializar novamente o tabuleiro
    private static Texture miraVerdeTexture = LoadTexture("res/vfx/mira_verde.png");
    private static Texture miraVermelhaTexture = LoadTexture("res/vfx/mira_vermelha.png");
    private Sprite miraVerdeSprite;
    private Sprite miraVermelhaSprite;

    // tabuleiro[y][x] = Peca(x, y)
    private Peca[][] tabuleiro = new Peca[SIZE][SIZE];

    // tabuleiro[y][x] = Peca(x, y)
    private void InitializePeca(Peca peca){
        this.tabuleiro[peca.posicaoTabuleiro.y][peca.posicaoTabuleiro.x] = peca;
    }

    // cria o tabuleiro da visão das brancas

    public Tabuleiro(Tabuleiro copy){
	this.tabuleiro = copy.GetTabuleiro();
    }

    public Tabuleiro()
    {
        // pecas brancas (id maiúsculo)
        this.InitializePeca(new Peao(0, 6, 'P'));
        this.InitializePeca(new Peao(1, 6, 'P'));
        this.InitializePeca(new Peao(2, 6, 'P'));
        this.InitializePeca(new Peao(3, 6, 'P'));
        this.InitializePeca(new Peao(4, 6, 'P'));
        this.InitializePeca(new Peao(5, 6, 'P'));
        this.InitializePeca(new Peao(6, 6, 'P'));
        this.InitializePeca(new Peao(7, 6, 'P'));

        this.InitializePeca(new Torre(0, 7, 'T'));
        this.InitializePeca(new Torre(7, 7, 'T'));

        this.InitializePeca(new Cavalo(1, 7, 'C'));
        this.InitializePeca(new Cavalo(6, 7, 'C'));

        this.InitializePeca(new Bispo(2, 7, 'B'));
        this.InitializePeca(new Bispo(5, 7, 'B'));

        this.InitializePeca(new Rei(4,7,'R'));
        this.InitializePeca(new Dama(3,7, 'D'));

        // pecas pretas (id minúsculo)
        this.InitializePeca(new Peao(0, 1, 'p'));
        this.InitializePeca(new Peao(1, 1, 'p'));
        this.InitializePeca(new Peao(2, 1, 'p'));
        this.InitializePeca(new Peao(3, 1, 'p'));
        this.InitializePeca(new Peao(4, 1, 'p'));
        this.InitializePeca(new Peao(5, 1, 'p'));
        this.InitializePeca(new Peao(6, 1, 'p'));
        this.InitializePeca(new Peao(7, 1, 'p'));

        this.InitializePeca(new Torre(0, 0, 't'));
        this.InitializePeca(new Torre(7, 0, 't'));

        this.InitializePeca(new Cavalo(1, 0, 'c'));
        this.InitializePeca(new Cavalo(6, 0, 'c'));

        this.InitializePeca(new Bispo(2, 0, 'b'));
        this.InitializePeca(new Bispo(5, 0, 'b'));

        this.InitializePeca(new Rei(4, 0, 'r'));
        this.InitializePeca(new Dama(3, 0, 'd'));

        for(int i = 0 ; i < SIZE; i ++){
            for (int j = 0; j < SIZE;j ++){
                if (this.tabuleiro[i][j] == null)
                    this.tabuleiro[i][j] = new Blank(j, i);
            }
        }

        //Sprites das miras
        miraVerdeSprite = new Sprite(miraVerdeTexture, 2, 0, 0, 1, WHITE, 2);
        miraVermelhaSprite = new Sprite(miraVermelhaTexture, 2, 0, 0, 1, WHITE, 2);
    }

    public Peca[][] GetTabuleiro(){
	return this.tabuleiro;
    }

    // checa qual peça está na posicao (x,y)
    public Peca GetPecaNaPosicao(int x, int y){
        return this.tabuleiro[y][x];
    }

    public Peca GetPecaNaPosicao(Pair p){
	return this.tabuleiro[p.y][p.x];
    }

    public void SetPecaNaPosicao(int x, int y, Peca peca){
        tabuleiro[y][x] = peca;
    }

    public void GetPecaNaPosicao(Pair p, Peca peca){
	tabuleiro[p.y][p.x] = peca;
    }

    public boolean PosicaoOcupada(int x, int y){
        if (this.tabuleiro[y][x] != null)
            return true;
        return false;
    }

    public boolean PosicaoOcupada(Pair p){
        if (this.tabuleiro[p.y][p.x] instanceof Blank)
            return false;
        return true;
    }


    // muda o tabuleiro de acordo com a jogada
    public void MudancaNoTabuleiro(Jogada jogada){
        Pair pecaMovida = jogada.pecaMovida.posicaoTabuleiro;
        Pair posicaoFinal = jogada.peca_capturada.posicaoTabuleiro;

        // move peça e anula posição anterior
        this.tabuleiro[posicaoFinal.y][posicaoFinal.x] = jogada.pecaMovida;
        this.tabuleiro[pecaMovida.y][pecaMovida.x] = new Blank(pecaMovida.x, pecaMovida.y);;
    }


    public boolean MoveLeadsToCheck(Peca pecaMovida, char cor, Pair mov){


	Tabuleiro simulacao = new Tabuleiro();

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){
		simulacao.SetPecaNaPosicao(i, j, this.GetPecaNaPosicao(i, j));
	    }
	}

	Peca pecaCapturada = simulacao.GetPecaNaPosicao(mov);

	Jogada jogadaSimulada = new Jogada(pecaMovida, pecaCapturada);
	simulacao.MudancaNoTabuleiro(jogadaSimulada);

	System.out.println(this.toString());
	System.out.println(simulacao.toString());

	// Se o movimento gerar um check
	if(pecaMovida instanceof Rei){
	    if(simulacao.CheckCheck(new Rei(mov.x, mov.y, pecaMovida.identificador))){
		System.out.println("Leva a check");
		return true;
	    }
	} else if(simulacao.CheckCheck(simulacao.GetReiCor(cor))){
	    System.out.println("Leva a check");
	    return true;
	}
	return false;
    }

    public boolean CheckCheck(Rei rei){

	char corRei = rei.GetCorPeca();

	// Para cada peça no tabuleiro
	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){

		Peca pecaVerificada =  this.GetPecaNaPosicao(i, j);

		// Se for inimiga
		if(pecaVerificada.GetCorPeca() != corRei){

		    // Se os movimentos possíveis capturam o rei
		    for (Pair mov : pecaVerificada.MovimentosValidos(this, false)){
			if(rei.posicaoTabuleiro.equals(mov)){
			    System.out.println(rei.posicaoTabuleiro.toString() + " Leva a check");
			    return true; // retorna check = true
			}
		    }
		}
	    }
	}

	return false;
    }

    public Rei GetReiCor(char cor){

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){
		Peca pecaVerificada = this.GetPecaNaPosicao(i, j);
		if(pecaVerificada instanceof Rei && cor == pecaVerificada.GetCorPeca()){
		    return (Rei) pecaVerificada;
		}
	    }
	}

	return null;
    }

    public void GirarTabuleiro(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int i_espelhado = SIZE - 1 - i;
                int j_espelhado = SIZE - 1 - j;

                if (i < i_espelhado || (i == i_espelhado && j < j_espelhado)) {
                    // espelha verticalmente e horizontalmente
                    Peca temp = this.tabuleiro[i][j];
                    this.tabuleiro[i][j] = this.tabuleiro[i_espelhado][j_espelhado];
                    this.tabuleiro[i_espelhado][j_espelhado] = temp;
                }
            }
        }
    }

    @Override
    public String toString(){
        String string = "\n";
	    string = string.concat("  1 2 3 4 5 6 7 8\n");
        for(int i = 0; i < SIZE; i ++){
	        string = string.concat(i + 1 + " ");
            for(int j = 0; j < SIZE; j++)
                string = string.concat(tabuleiro[i][j].identificador + " ");
            string = string.concat("\n");
        }
        return string;
    }

    public void DrawGrid(int xInicial, int yInicial, int escala)
    {
        int contadorAlteraCor = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                Cor quadradoCor = new Cor(250, 245, 240, 255);
                if (contadorAlteraCor % 2 == 1)
                    quadradoCor = new Cor(38, 41, 66, 255);
                contadorAlteraCor++;
                DrawRectangle(xInicial + j * 16 * escala, yInicial + i * 16 * escala, 16 * escala, 16 *escala, quadradoCor.GetCor());
            }
            contadorAlteraCor++;
        }
    }

    public Pair GetMousePositionOnTabuleiro(int xInicial, int yInicial, int escala)
    {
        int linha = (int)((GetMouseY() - yInicial) / (16 * escala));
        int coluna = (int)((GetMouseX() - xInicial) / (16 * escala));

        Pair posicao = new Pair(coluna, linha);

        return posicao;
    }

    public boolean MouseClikedOnTabuleiro(int xInicial, int yInicial, int escala)
    {
        boolean clicou = false;
        if (IsMouseButtonPressed(0))
        {
            //Vendo se o mouse tá dentro do limite
            if (GetMouseY() >= yInicial && GetMouseY() < yInicial + (16 * escala * 8))
            {
                if (GetMouseX() >= xInicial && GetMouseX() < xInicial + (16 * escala * 8))
                {
                    clicou = true;
                }
            }
        }

        return clicou;
    }

    public void DrawPecas(int xInicial, int yInicial)
    {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                tabuleiro[i][j].DrawPeca(xInicial, yInicial);
    }

    public void DrawMovimentosValidos(ArrayList<Pair> movimentos, int xInicial, int yInicial, int escala)
    {
        for (int i = 0; i < movimentos.size(); i++)
        {
            //Mudando a sprite
            Pair mousePosition = GetMousePositionOnTabuleiro(xInicial, yInicial, escala);
            if (mousePosition.x == movimentos.get(i).x && mousePosition.y == movimentos.get(i).y)
            {
                miraVerdeSprite.SetImagemAtual(0);
                miraVermelhaSprite.SetImagemAtual(0);
            }
            else
            {
                miraVerdeSprite.SetImagemAtual(1);
                miraVermelhaSprite.SetImagemAtual(1);
            }

            if (this.GetPecaNaPosicao(movimentos.get(i).x, movimentos.get(i).y) instanceof Blank )
            {
                /*DrawRectangle(movimentos.get(i).x * 16 * escala + xInicial,
                movimentos.get(i).y * 16 * escala + yInicial, 16 * escala, 16 * escala, GREEN);*/
                miraVerdeSprite.DrawSpritePro(movimentos.get(i).x * 16 * escala + xInicial + miraVerdeSprite.GetWidth() / 2,
                movimentos.get(i).y * 16 * escala + yInicial + miraVerdeSprite.GetHeight() / 2);
            }

            else
            {
                /*DrawRectangle(movimentos.get(i).x * 16 * escala + xInicial,
                movimentos.get(i).y * 16 * escala + yInicial, 16 * escala, 16 * escala, RED);*/
                miraVermelhaSprite.DrawSpritePro(movimentos.get(i).x * 16 * escala + xInicial + miraVermelhaSprite.GetWidth() / 2,
                movimentos.get(i).y * 16 * escala + yInicial + miraVermelhaSprite.GetHeight() / 2);
            }
        }
    }
}
