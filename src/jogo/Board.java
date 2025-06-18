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

import gui.OurColor;
import gui.Sprite;
import jogo.pieces.*;
import misc.Pair;

public class Board
{

    private int PiecesNoBoard = 32;
    private static final int SIZE = 8;

    //Dando load nas imagens
    //Isso será um problema no futuro se a gente quiser inicializar novamente o tabuleiro
    private static Texture miraVerdeTexture = LoadTexture("res/vfx/mira_verde.png");
    private static Texture miraVermelhaTexture = LoadTexture("res/vfx/mira_vermelha.png");
    private Sprite miraVerdeSprite;
    private Sprite miraVermelhaSprite;

    // tabuleiro[y][x] = Piece(x, y)
    private Piece[][] tabuleiro = new Piece[SIZE][SIZE];

    // tabuleiro[y][x] = Piece(x, y)
    private void InitializePiece(Piece peca){
        this.tabuleiro[peca.posicaoBoard.y][peca.posicaoBoard.x] = peca;
    }

    // cria o tabuleiro da visão das brancas

    public Board(Board copy){
	this.tabuleiro = copy.GetBoard();
    }

    public Board()
    {
        // pecas brancas (id maiúsculo)
        this.InitializePiece(new Pawn(0, 6, 'P'));
        this.InitializePiece(new Pawn(1, 6, 'P'));
        this.InitializePiece(new Pawn(2, 6, 'P'));
        this.InitializePiece(new Pawn(3, 6, 'P'));
        this.InitializePiece(new Pawn(4, 6, 'P'));
        this.InitializePiece(new Pawn(5, 6, 'P'));
        this.InitializePiece(new Pawn(6, 6, 'P'));
        this.InitializePiece(new Pawn(7, 6, 'P'));

        this.InitializePiece(new Rook(0, 7, 'T'));
        this.InitializePiece(new Rook(7, 7, 'T'));

        this.InitializePiece(new Knight(1, 7, 'C'));
        this.InitializePiece(new Knight(6, 7, 'C'));

        this.InitializePiece(new Bishop(2, 7, 'B'));
        this.InitializePiece(new Bishop(5, 7, 'B'));

        this.InitializePiece(new King(4,7,'R'));
        this.InitializePiece(new Queen(3,7, 'D'));

        // pecas pretas (id minúsculo)
        this.InitializePiece(new Pawn(0, 1, 'p'));
        this.InitializePiece(new Pawn(1, 1, 'p'));
        this.InitializePiece(new Pawn(2, 1, 'p'));
        this.InitializePiece(new Pawn(3, 1, 'p'));
        this.InitializePiece(new Pawn(4, 1, 'p'));
        this.InitializePiece(new Pawn(5, 1, 'p'));
        this.InitializePiece(new Pawn(6, 1, 'p'));
        this.InitializePiece(new Pawn(7, 1, 'p'));

        this.InitializePiece(new Rook(0, 0, 't'));
        this.InitializePiece(new Rook(7, 0, 't'));

        this.InitializePiece(new Knight(1, 0, 'c'));
        this.InitializePiece(new Knight(6, 0, 'c'));

        this.InitializePiece(new Bishop(2, 0, 'b'));
        this.InitializePiece(new Bishop(5, 0, 'b'));

        this.InitializePiece(new King(4, 0, 'r'));
        this.InitializePiece(new Queen(3, 0, 'd'));

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

    public Piece[][] GetBoard(){
	return this.tabuleiro;
    }

    // checa qual peça está na posicao (x,y)
    public Piece GetPieceNaPosicao(int x, int y){
        return this.tabuleiro[y][x];
    }

    public Piece GetPieceNaPosicao(Pair p){
	return this.tabuleiro[p.y][p.x];
    }

    public void SetPieceNaPosicao(int x, int y, Piece peca){
        tabuleiro[y][x] = peca;
    }

    public void GetPieceNaPosicao(Pair p, Piece peca){
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
    public void MudancaNoBoard(Jogada jogada){
        Pair pecaMovida = jogada.pecaMovida.posicaoBoard;
        Pair posicaoFinal = jogada.peca_capturada.posicaoBoard;

        // move peça e anula posição anterior
        this.tabuleiro[posicaoFinal.y][posicaoFinal.x] = jogada.pecaMovida;
        this.tabuleiro[pecaMovida.y][pecaMovida.x] = new Blank(pecaMovida.x, pecaMovida.y);;
    }


    public boolean MoveLeadsToCheck(Piece pecaMovida, char cor, Pair mov){


	Board simulacao = new Board();

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){
		simulacao.SetPieceNaPosicao(i, j, this.GetPieceNaPosicao(i, j));
	    }
	}

	Piece pecaCapturada = simulacao.GetPieceNaPosicao(mov);

	Jogada jogadaSimulada = new Jogada(pecaMovida, pecaCapturada);
	simulacao.MudancaNoBoard(jogadaSimulada);

	System.out.println(this.toString());
	System.out.println(simulacao.toString());

	// Se o movimento gerar um check
	if(pecaMovida instanceof King){
	    if(simulacao.CheckCheck(new King(mov.x, mov.y, pecaMovida.identificador))){
		System.out.println("Leva a check");
		return true;
	    }
	} else if(simulacao.CheckCheck(simulacao.GetKingOurColor(cor))){
	    System.out.println("Leva a check");
	    return true;
	}
	return false;
    }

    public boolean CheckCheck(King rei){

	char corKing = rei.GetOurColorPiece();

	// Para cada peça no tabuleiro
	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){

		Piece pecaVerificada =  this.GetPieceNaPosicao(i, j);

		// Se for inimiga
		if(pecaVerificada.GetOurColorPiece() != corKing){

		    // Se os movimentos possíveis capturam o rei
		    for (Pair mov : pecaVerificada.MovimentosValidos(this, false)){
			if(rei.posicaoBoard.equals(mov)){
			    System.out.println(rei.posicaoBoard.toString() + " Leva a check");
			    return true; // retorna check = true
			}
		    }
		}
	    }
	}

	return false;
    }

    public King GetKingOurColor(char cor){

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){
		Piece pecaVerificada = this.GetPieceNaPosicao(i, j);
		if(pecaVerificada instanceof King && cor == pecaVerificada.GetOurColorPiece()){
		    return (King) pecaVerificada;
		}
	    }
	}

	return null;
    }

    public void GirarBoard(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int i_espelhado = SIZE - 1 - i;
                int j_espelhado = SIZE - 1 - j;

                if (i < i_espelhado || (i == i_espelhado && j < j_espelhado)) {
                    // espelha verticalmente e horizontalmente
                    Piece temp = this.tabuleiro[i][j];
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
        int contadorAlteraOurColor = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                OurColor quadradoOurColor = new OurColor(250, 245, 240, 255);
                if (contadorAlteraOurColor % 2 == 1)
                    quadradoOurColor = new OurColor(38, 41, 66, 255);
                contadorAlteraOurColor++;
                DrawRectangle(xInicial + j * 16 * escala, yInicial + i * 16 * escala, 16 * escala, 16 *escala, quadradoOurColor.GetOurColor());
            }
            contadorAlteraOurColor++;
        }
    }

    public Pair GetMousePositionOnBoard(int xInicial, int yInicial, int escala)
    {
        int linha = (int)((GetMouseY() - yInicial) / (16 * escala));
        int coluna = (int)((GetMouseX() - xInicial) / (16 * escala));

        Pair posicao = new Pair(coluna, linha);

        return posicao;
    }

    public boolean MouseClikedOnBoard(int xInicial, int yInicial, int escala)
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

    public void DrawPieces(int xInicial, int yInicial)
    {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                tabuleiro[i][j].DrawPiece(xInicial, yInicial);
    }

    public void DrawMovimentosValidos(ArrayList<Pair> movimentos, int xInicial, int yInicial, int escala)
    {
        for (int i = 0; i < movimentos.size(); i++)
        {
            //Mudando a sprite
            Pair mousePosition = GetMousePositionOnBoard(xInicial, yInicial, escala);
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

            if (this.GetPieceNaPosicao(movimentos.get(i).x, movimentos.get(i).y) instanceof Blank )
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
