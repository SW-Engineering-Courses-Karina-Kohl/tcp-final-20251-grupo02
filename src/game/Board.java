package game;

import static com.raylib.Colors.GREEN;
import static com.raylib.Colors.RED;
import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.DrawRectangle;
import static com.raylib.Raylib.GetMousePosition;
import static com.raylib.Raylib.GetMouseX;
import static com.raylib.Raylib.GetMouseY;
import static com.raylib.Raylib.GetScreenToWorld2D;
import static com.raylib.Raylib.IsMouseButtonPressed;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Camera2D;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

import gui.OurColor;
import gui.Sprite;
import game.pieces.*;
import misc.Pair;

public class Board {

    private static final int SIZE = 8;
    private Piece[][] board = new Piece[SIZE][SIZE];

    private static Texture greenAimTexture = LoadTexture("res/vfx/mira_verde.png");
    private static Texture redAimTexture = LoadTexture("res/vfx/mira_vermelha.png");
    private Sprite greenAimSprite;
    private Sprite redAimSprite;
    // This will be a problem in the future if we want to instanciate the board
    // again

    private Move lastMove = new Move(new Blank(0, 0), new Blank(0, 0));

    /* board[y][x] = Piece(x, y) */
    private void initializePiece(Piece piece) {
        this.board[piece.getBoardPosition().y][piece.getBoardPosition().x] = piece;
    }

    /* Class contructor: Creates a board in the view of the white pieces */
    public Board() {
        // white pieces (doubleUppercase id)
        this.initializePiece(new Pawn(0, 6, 'P'));
        this.initializePiece(new Pawn(1, 6, 'P'));
        this.initializePiece(new Pawn(2, 6, 'P'));
        this.initializePiece(new Pawn(3, 6, 'P'));
        this.initializePiece(new Pawn(4, 6, 'P'));
        this.initializePiece(new Pawn(5, 6, 'P'));
        this.initializePiece(new Pawn(6, 6, 'P'));
        this.initializePiece(new Pawn(7, 6, 'P'));

        this.initializePiece(new Rook(0, 7, 'R'));
        this.initializePiece(new Rook(7, 7, 'R'));

        this.initializePiece(new Knight(1, 7, 'H'));
        this.initializePiece(new Knight(6, 7, 'H'));

        this.initializePiece(new Bishop(2, 7, 'B'));
        this.initializePiece(new Bishop(5, 7, 'B'));

        this.initializePiece(new King(4, 7, 'K'));
        this.initializePiece(new Queen(3, 7, 'Q'));

        // black pieces (lowercase id)
        this.initializePiece(new Pawn(0, 1, 'p'));
        this.initializePiece(new Pawn(1, 1, 'p'));
        this.initializePiece(new Pawn(2, 1, 'p'));
        this.initializePiece(new Pawn(3, 1, 'p'));
        this.initializePiece(new Pawn(4, 1, 'p'));
        this.initializePiece(new Pawn(5, 1, 'p'));
        this.initializePiece(new Pawn(6, 1, 'p'));
        this.initializePiece(new Pawn(7, 1, 'p'));

        this.initializePiece(new Rook(0, 0, 'r'));
        this.initializePiece(new Rook(7, 0, 'r'));

        this.initializePiece(new Knight(1, 0, 'h'));
        this.initializePiece(new Knight(6, 0, 'h'));

        this.initializePiece(new Bishop(2, 0, 'b'));
        this.initializePiece(new Bishop(5, 0, 'b'));

        this.initializePiece(new King(4, 0, 'k'));
        this.initializePiece(new Queen(3, 0, 'q'));

        // Blank spaces
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.board[i][j] == null)
                    this.board[i][j] = new Blank(j, i);
            }
        }

        // Sprites of the "aims"
        greenAimSprite = new Sprite(greenAimTexture, 2, 0, 0, 1, WHITE, 2);
        redAimSprite = new Sprite(redAimTexture, 2, 0, 0, 1, WHITE, 2);
    }

    // ------------ Methods ---------------

    public Piece[][] getBoard() {
        return this.board;
    }

    public Piece getPieceInPosition(int x, int y) {
        return this.board[y][x];
    }

    public Piece getPieceInPosition(Pair p) {
        return this.board[p.y][p.x];
    }

    public void setPieceInPosition(int x, int y, Piece piece) {
        board[y][x] = piece;
    }

    public void setLastMove(Move move){
	this.lastMove = move;
    }

    public Move getLastMove(){
	return this.lastMove;
    }

    public void setPieceInPosition(Pair p, Piece piece) {
        board[p.y][p.x] = piece;
    }

    public boolean isTherePieceInPosition(int x, int y) {
        if (this.board[y][x] instanceof Blank)
            return false;
        return true;
    }

    public boolean isTherePieceInPosition(Pair p) {
        if (this.board[p.y][p.x] instanceof Blank)
            return false;
        return true;
    }

    /* Retunr the position of the king of color "color" */
    private King getKingColor(char color) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                Piece piece = this.getPieceInPosition(i, j);

                if (piece instanceof King && color == piece.findPieceColor()) {
                    return (King) piece;
                }

            }
        }

        // It will never get here because there is always a king in the board for each
        // color
        return null;
    }

    /* Execute the move and change the positions of the pieces */
    public void executeMove(Move move){

	Piece movedPiece = move.getMovedPiece();
	Piece capturedPiece = move.getCapturedPiece();

	// Set the pieced as move to prevent special moviments after
	if (movedPiece instanceof King) {
	    ((King) movedPiece).setHasMoved(true);
	}
	if (movedPiece instanceof Rook) {
	    ((Rook) movedPiece).setHasMoved(true);
	}
	if (movedPiece instanceof Pawn) {
	    ((Pawn) movedPiece).setHasMoved(true);
	}

	// checking for special moviments
	if(move.isCastiling()){
	    this.executeCastling(movedPiece, capturedPiece);
	} else
	if (move.isEnPassant(this)) {
	    this.executeEnPassant(movedPiece, this.getLastMove().getCapturedPiece());
	} else {
	    updateBoard(move);
	    movedPiece.movePiece(move);
	}
    }

    private void executeCastling(Piece movedPiece, Piece capturedPiece){

	Rook rook;
	King king;

	if(movedPiece instanceof King){
	    king = (King) movedPiece;
	    rook = (Rook) capturedPiece;
	} else {
	    king = (King) capturedPiece;
	    rook = (Rook) movedPiece;
	}

	int side = king.getBoardPosition().y;
	Move kingMove;
	Move rookMove;

	// left-side rook, queenside castling
	if(rook.getBoardPosition().x < king.getBoardPosition().x){
	    kingMove = new Move(king, new Blank(2, side));
	    rookMove = new Move(rook, new Blank(3, side));
	} else {
	    kingMove = new Move(king, new Blank(6, side));
	    rookMove = new Move(rook, new Blank(5, side));
	}

	updateBoard(kingMove);
	updateBoard(rookMove);
	king.movePiece(kingMove);
	rook.movePiece(rookMove);

    }

    private void executeEnPassant(Piece movedPiece, Piece capturedPiece){

	Move movedPieceMove = new Move(movedPiece, new Blank(capturedPiece.getBoardPosition().add(new Pair(0, ((Pawn) movedPiece).getMoveDirection()))));
	Move capturedPieceMove = new Move(capturedPiece, new Blank(capturedPiece.getBoardPosition()));

	updateBoard(movedPieceMove);
	movedPiece.movePiece(movedPieceMove);

	this.setPieceInPosition(capturedPiece.getBoardPosition(), new Blank(0, 0));

    }

    /* Changes the board based on a move */
    public void updateBoard(Move move) {

        Piece movedPiece = move.getMovedPiece();
	Piece capturedPiece = move.getCapturedPiece();
        Pair finalPosition = move.getCapturedPiece().getBoardPosition();

        // Turns null (blank) the piece previous position
        this.setPieceInPosition(movedPiece.getBoardPosition(), new Blank(movedPiece.getBoardPosition()));
        // And moves the piece
        this.setPieceInPosition(finalPosition, movedPiece);

    }

    /*
     * Check if a move leads to a check. If yes, return true, otherwise returns
     * false
     */
    public boolean moveLeadsToCheck(Piece movedPiece, char color, Pair movePosition) {

        Board simulationBoard = new Board();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                simulationBoard.setPieceInPosition(i, j, this.getPieceInPosition(i, j));
            }
        }

        Piece capturedPiece = simulationBoard.getPieceInPosition(movePosition);

        Move simulatedMove = new Move(movedPiece, capturedPiece);
        simulationBoard.updateBoard(simulatedMove);

        /* Theres a need for a special treatment for the king */
        if (movedPiece instanceof King) {

            if (simulationBoard.checkCheck(new King(movePosition, movedPiece.getPieceID()))) {
                return true;
            }

        } else if (simulationBoard.checkCheck(simulationBoard.getKingColor(color))) {
            return true;
        }
        return false;
    }

    /* Check if the king passed as argument is currently in check */
    public boolean checkCheck(King king) {

        char colorKing = king.findPieceColor();

        // For each piece in the board board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                Piece piece = this.getPieceInPosition(i, j);

                if (piece.findPieceColor() != colorKing) {

                    for (Pair movePostion : piece.validMovements(this, false)) {
                        if (king.getBoardPosition().isEqualsTo(movePostion)) {
                            return true;
                        }
                    }
                }
            }
        }

        // If no possible moves target the king, then it is not in check
        return false;
    }


    public boolean checkCheck(char color){
	King king = getKingColor(color);
	return this.checkCheck(king);
    }

    public boolean checkCheckmate(char color){

	for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                Piece piece = this.getPieceInPosition(i, j);

                if (piece.findPieceColor() == color) {
		    if(!piece.validMovements(this, true).isEmpty()){
			return false;
		    }
		}
	    }
	}

	return true;
    }

    /* Assuming that there no piece beetween the two pieces, can they rook? */
    public boolean checkCastling(Rook rook, King king, char rookSide){

	if(rook.hasMoved() || king.hasMoved()){
	    return false;
	}

	int direction = 0;
	// rook on the right side of the king
	if(rookSide == 'r'){
	    direction = 1;
	} else {
	    direction = -1;
	}

	// Check if the squares between them are being attacked
	for(int i = king.getBoardPosition().x; i != rook.getBoardPosition().x; i = i + direction){
	    if(this.moveLeadsToCheck(king, king.findPieceColor(), new Pair(i, king.getBoardPosition().y))){
		return false;
	    }
	}

	return true;
    }

    /* returns if the en passant can be done for the side passed as argument */
    public boolean checkEnPassant(Pawn pawn, char side){

	int direction = 0;
	if(side == 'r'){
	    direction = 1;
	} else {
	    direction = -1;
	}

	Piece lastMoveOriginalPlace = this.getLastMove().getMovedPiece();
	Piece lastMoveFinalPlace = this.getLastMove().getCapturedPiece();


	// if the last moved piece isn't a pawn, it can't be an en passant
	if(!(lastMoveFinalPlace instanceof Pawn)){
	    return false;
	}

	// if in the last move the moved pawn didn't moved two squares foward, it can't be an en passant
	if(Math.abs(lastMoveOriginalPlace.getBoardPosition().y - lastMoveFinalPlace.getBoardPosition().y) != 2){
	    return false;
	}

	// The moved pawn must be in the same row and side-by-side as our pawn to be an en passant
	if(lastMoveFinalPlace.getBoardPosition().y != pawn.getBoardPosition().y){
	    return false;
	} else {
	    if(lastMoveFinalPlace.getBoardPosition().x != pawn.getBoardPosition().x + direction){
		return false;
	    }
	}

	return true;

    }

    public Pair getMousePositionOnBoard(int xInitial, int yInitial, int scale, Camera2D camera2d){
        int x = (int) getMousePositionScreen(camera2d).x();
        int y = (int) getMousePositionScreen(camera2d).y();

        int line = (y - yInitial) / (16 * scale);
        int column = (x - xInitial) / (16 * scale);

        Pair position = new Pair(column, line);

        return position;
    }

    public boolean mouseClikedOnBoard(int xInitial, int yInitial, int scale, Camera2D camera2d)    {

        if (IsMouseButtonPressed(0)){
            int x = (int) getMousePositionScreen(camera2d).x();
            int y = (int) getMousePositionScreen(camera2d).y();

            if (y >= yInitial && y < yInitial + (16 * scale * 8)){
                if (x >= xInitial && x < xInitial + (16 * scale * 8)){
                    return true;
                }
            }
        }

        return false;
    }

    public void drawGrid(int xInitial, int yInitial, int scale) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                OurColor squareColor = new OurColor(250, 245, 240, 255);
                if ((i + j) % 2 == 1) {
                    squareColor = new OurColor(38, 41, 66, 255);
                }
                DrawRectangle(xInitial + j * 16 * scale, yInitial + i * 16 * scale, 16 * scale, 16 * scale,
                        squareColor.getColor());
            }
        }

    }

    public void drawPieces(int xInitial, int yInitial) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j].drawPiece(xInitial, yInitial);
            }
        }

    }

    /* Show visually in the board the valid moves of the piece */
    public void drawValidMoviments(ArrayList<Pair> moviments, int xInitial, int yInitial, int scale, Camera2D camera2d) {

        for (int i = 0; i < moviments.size(); i++) {

            Pair mousePosition = getMousePositionOnBoard(xInitial, yInitial, scale, camera2d);

            if (mousePosition.x == moviments.get(i).x && mousePosition.y == moviments.get(i).y) {
                greenAimSprite.SetCurrentImage(0);
                redAimSprite.SetCurrentImage(0);
            } else {
                greenAimSprite.SetCurrentImage(1);
                redAimSprite.SetCurrentImage(1);
            }

	    if (this.getPieceInPosition(moviments.get(i)).getPieceID() == '-' || !(this.getPieceInPosition(moviments.get(i)) instanceof Blank)) {
		redAimSprite.DrawSpritePro(moviments.get(i).x * 16 * scale + xInitial + redAimSprite.GetWidth() / 2,
					   moviments.get(i).y * 16 * scale + yInitial + redAimSprite.GetHeight() / 2);
	    }
	    else {
                greenAimSprite.DrawSpritePro(moviments.get(i).x * 16 * scale + xInitial + greenAimSprite.GetWidth() / 2,
                        moviments.get(i).y * 16 * scale + yInitial + greenAimSprite.GetHeight() / 2);
            }

        }

    }


    public Vector2 getMousePositionScreen(Camera2D camera2d) {
        return GetScreenToWorld2D(GetMousePosition(), camera2d);
    }

    public String toString() {
        String string = "\n";
        string = string.concat("  1 2 3 4 5 6 7 8\n");
        for (int i = 0; i < SIZE; i++) {
            string = string.concat(i + 1 + " ");
            for (int j = 0; j < SIZE; j++)
                string = string.concat(board[i][j].getPieceID() + " ");
            string = string.concat("\n");
        }
        return string;
    }
}
