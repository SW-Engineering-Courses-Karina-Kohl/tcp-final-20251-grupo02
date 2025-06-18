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

    /* board[y][x] = Piece(x, y) */
    private void InitializePiece(Piece piece){
        this.board[piece.boardPosition.y][piece.boardPosition.x] = piece;
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

    public void SetPieceInPosition(int x, int y, Piece piece){
        board[y][x] = piece;
    }

    public void SetPieceInPosition(Pair p, Piece piece){
	board[p.y][p.x] = piece;
    }

    public boolean IsTherePieceInPosition(int x, int y){
        if (this.board[y][x] instanceof Blank)
            return false;
        return true;
    }

    public boolean IsTherePieceInPosition(Pair p){
        if (this.board[p.y][p.x] instanceof Blank)
            return false;
        return true;
    }


    /* Changes the board based on a move */
    public void UpdateBoard(Move move){

        Piece movedPiece = move.movedPiece;
        Pair finalPosition = move.capturedPiece.boardPosition;

	// Turns null (blank) the piece previous position
	this.SetPieceInPosition(movedPiece.boardPosition, new Blank(movedPiece.boardPosition));
	// And moves the piece
	this.SetPieceInPosition(finalPosition, movedPiece);
    }


    /* Check if a move leads to a check. If yes, return true, otherwise returns false */
    public boolean MoveLeadsToCheck(Piece movedPiece, char cor, Pair movePosition){

	Board simulationBoard = new Board();

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){
		simulationBoard.SetPieceInPosition(i, j, this.GetPieceInPosition(i, j));
	    }
	}

	Piece capturedPiece = simulationBoard.GetPieceInPosition(movePosition);

	Move simulatedMove = new Move(movedPiece, capturedPiece);
	simulationBoard.UpdateBoard(simulatedMove);

	/* Theres a need for a special treatment for the king */
	if(movedPiece instanceof King){
	    if(simulationBoard.CheckCheck(new King(movePosition, movedPiece.identificador))){
		return true;
	    }
	} else if(simulationBoard.CheckCheck(simulationBoard.GetKingColor(cor))){
	    return true;
	}
	return false;
    }


    /* Check if the king passed as argument is currently in check */
    public boolean CheckCheck(King rei){

	char corKing = rei.GetColorPiece();

	// For each piece in the board board
	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){

		Piece piece =  this.GetPieceInPosition(i, j);

		if(piece.GetColorPiece() != corKing){

		    for (Pair movePostion : piece.ValidMoviments(this, false)){
			if(rei.boardPosition.IsEqualsTo(movePostion)){
			    return true;
			}
		    }
		}
	    }
	}

	// If no possible moves target the king, then it is not in check
	return false;
    }


    /* Retunr the position of the king of color "color" */
    public King GetKingColor(char color){

	for(int i = 0; i < SIZE; i++){
	    for(int j = 0; j < SIZE; j++){

		Piece piece = this.GetPieceInPosition(i, j);

		if(piece instanceof King && color == piece.GetColorPiece()){
		    return (King) piece;
		}

	    }
	}

	// It will never get here because there is also a king in the board for each color
	return null;
    }


    public Pair GetMousePositionOnBoard(int xInitial, int yInitial, int scale){

        int line = (GetMouseY() - yInitial) / (16 * scale);
        int column = (GetMouseX() - xInitial) / (16 * scale);

        Pair position = new Pair(column, line);

        return position;
    }

    public boolean MouseClikedOnBoard(int xInitial, int yInitial, int scale)    {

        if (IsMouseButtonPressed(0)){
            if (GetMouseY() >= yInitial && GetMouseY() < yInitial + (16 * scale * 8)){
                if (GetMouseX() >= xInitial && GetMouseX() < xInitial + (16 * scale * 8)){
                    return true;
                }
            }
        }

        return false;
    }

    public void DrawGrid(int xInitial, int yInitial, int scale){

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                OurColor squareColor = new OurColor(250, 245, 240, 255);
                if ((i + j) % 2 == 1){
                    squareColor = new OurColor(38, 41, 66, 255);
		}
                DrawRectangle(xInitial + j * 16 * scale, yInitial + i * 16 * scale, 16 * scale, 16 *scale, squareColor.GetColor());
            }
        }
    }


    public void DrawPieces(int xInitial, int yInitial)
    {
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                board[i][j].DrawPiece(xInitial, yInitial);
	    }
	}
    }

    /* Show visually in the board the valid moves of the piece */
    public void DrawValidMoviments(ArrayList<Pair> moviments, int xInitial, int yInitial, int scale)
    {
        for (int i = 0; i < moviments.size(); i++)
        {
            Pair mousePosition = GetMousePositionOnBoard(xInitial, yInitial, scale);

            if (mousePosition.x == moviments.get(i).x && mousePosition.y == moviments.get(i).y){
                miraVerdeSprite.SetImagemAtual(0);
                miraVermelhaSprite.SetImagemAtual(0);
            } else {
                miraVerdeSprite.SetImagemAtual(1);
                miraVermelhaSprite.SetImagemAtual(1);
            }

            if (this.GetPieceInPosition(moviments.get(i)) instanceof Blank ){
                miraVerdeSprite.DrawSpritePro(moviments.get(i).x * 16 * scale + xInitial + miraVerdeSprite.GetWidth() / 2,
					      moviments.get(i).y * 16 * scale + yInitial + miraVerdeSprite.GetHeight() / 2);
            } else {
		miraVermelhaSprite.DrawSpritePro(moviments.get(i).x * 16 * scale + xInitial + miraVermelhaSprite.GetWidth() / 2,
						 moviments.get(i).y * 16 * scale + yInitial + miraVermelhaSprite.GetHeight() / 2);
            }

        }
    }


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

}
