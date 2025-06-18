package game;
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
import game.pieces.*;
import misc.Pair;

public class Board
{

    private int PiecesOnBoard = 32;
    private static final int SIZE = 8;
    private Piece[][] board = new Piece[SIZE][SIZE];

    private static Texture miraVerdeTexture = LoadTexture("res/vfx/mira_verde.png");
    private static Texture miraVermelhaTexture = LoadTexture("res/vfx/mira_vermelha.png");
    private Sprite miraVerdeSprite;
    private Sprite miraVermelhaSprite;
    // This will be a problem in the future if we want to instanciate the board again

    // board[y][x] = Piece(x, y)
    private void InitializePiece(Piece piece){
        this.board[piece.posicaoBoard.y][piece.posicaoBoard.x] = piece;
    }

    /* Class contructor: Creates a board in the view of the white pieces */
    public Board()
    {
        // white pieces (uppercase id)
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

        // black pieces (lowercase id)
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

	// Blank spaces
        for(int i = 0 ; i < SIZE; i ++){
            for (int j = 0; j < SIZE;j ++){
                if (this.board[i][j] == null)
                    this.board[i][j] = new Blank(j, i);
            }
        }

        //Sprites of the "aims"
        miraVerdeSprite = new Sprite(miraVerdeTexture, 2, 0, 0, 1, WHITE, 2);
        miraVermelhaSprite = new Sprite(miraVermelhaTexture, 2, 0, 0, 1, WHITE, 2);
    }


    // ------------ Methods ---------------

    public Piece[][] GetBoard(){
	return this.board;
    }

    public Piece GetPieceInPosition(int x, int y){
        return this.board[y][x];
    }

    public Piece GetPieceInPosition(Pair p){
	return this.board[p.y][p.x];
    }

    public void SetPieceNaPosition(int x, int y, Piece piece){
        board[y][x] = piece;
    }

    public void SetPieceInPosition(Pair p, Piece piece){
	board[p.y][p.x] = piece;
    }

    public boolean PositionOcupada(int x, int y){
        if (this.board[y][x] != null)
            return true;
        return false;
    }

    public boolean PositionOcupada(Pair p){
        if (this.board[p.y][p.x] instanceof Blank)
            return false;
        return true;
    }


    // muda o board de acordo com a jogada
    public void MudancaNoBoard(Jogada jogada){
        Pair pieceMovida = jogada.pieceMovida.posicaoBoard;
        Pair posicaoFinal = jogada.piece_capturada.posicaoBoard;

        // move peça e anula posição anterior
        this.board[posicaoFinal.y][posicaoFinal.x] = jogada.pieceMovida;
        this.board[pieceMovida.y][pieceMovida.x] = new Blank(pieceMovida.x, pieceMovida.y);;
    }


    public boolean MoveLeadsToCheck(Piece pieceMovida, char cor, Pair mov){


	Board simulacao = new Board();

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){
		simulacao.SetPieceNaPosition(i, j, this.GetPieceInPosition(i, j));
	    }
	}

	Piece pieceCapturada = simulacao.GetPieceInPosition(mov);

	Jogada jogadaSimulada = new Jogada(pieceMovida, pieceCapturada);
	simulacao.MudancaNoBoard(jogadaSimulada);

	System.out.println(this.toString());
	System.out.println(simulacao.toString());

	// Se o movimento gerar um check
	if(pieceMovida instanceof King){
	    if(simulacao.CheckCheck(new King(mov.x, mov.y, pieceMovida.identificador))){
		System.out.println("Leva a check");
		return true;
	    }
	} else if(simulacao.CheckCheck(simulacao.GetKingColor(cor))){
	    System.out.println("Leva a check");
	    return true;
	}
	return false;
    }

    public boolean CheckCheck(King rei){

	char corKing = rei.GetColorPiece();

	// Para cada peça no board
	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){

		Piece pieceVerificada =  this.GetPieceInPosition(i, j);

		// Se for inimiga
		if(pieceVerificada.GetColorPiece() != corKing){

		    // Se os movimentos possíveis capturam o rei
		    for (Pair mov : pieceVerificada.MovimentosValidos(this, false)){
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

    public King GetKingColor(char cor){

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){
		Piece pieceVerificada = this.GetPieceInPosition(i, j);
		if(pieceVerificada instanceof King && cor == pieceVerificada.GetColorPiece()){
		    return (King) pieceVerificada;
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
                    Piece temp = this.board[i][j];
                    this.board[i][j] = this.board[i_espelhado][j_espelhado];
                    this.board[i_espelhado][j_espelhado] = temp;
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
                string = string.concat(board[i][j].identificador + " ");
            string = string.concat("\n");
        }
        return string;
    }

    public void DrawGrid(int xInicial, int yInicial, int escala)
    {
        int contadorAlteraColor = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                OurColor quadradoColor = new OurColor(250, 245, 240, 255);
                if (contadorAlteraColor % 2 == 1)
                    quadradoColor = new OurColor(38, 41, 66, 255);
                contadorAlteraColor++;
                DrawRectangle(xInicial + j * 16 * escala, yInicial + i * 16 * escala, 16 * escala, 16 *escala, quadradoColor.GetOurColor());
            }
            contadorAlteraColor++;
        }
    }

    public Pair GetMousePositionOnBoard(int xInicial, int yInicial, int escala)
    {
        int linha = (GetMouseY() - yInicial) / (16 * escala);
        int coluna = (GetMouseX() - xInicial) / (16 * escala);

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
                board[i][j].DrawPiece(xInicial, yInicial);
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

            if (this.GetPieceInPosition(movimentos.get(i).x, movimentos.get(i).y) instanceof Blank )
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
