package game.pieces;

import game.Board;
import misc.Pair;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.LoadTexture;

import java.util.ArrayList;

import com.raylib.Raylib.Texture;

import gui.Sprite;

public class Knight extends Piece {

    private static Texture knightTexture;

   public Knight(int x, int y, char id, boolean initUI) {
		super(x, y, id);
		if(initUI)
			this.loadSprite();
	}

    public Knight(Pair p, char id, boolean initUI) {
		super(p.x, p.y, id);
		if(initUI)
			this.loadSprite();
	}

    private void loadSprite() {
        if (knightTexture == null) {
            knightTexture = LoadTexture("res/pieces/knight.png");
        }

        if (findPieceColor() == 'w')
            this.setSprite(new Sprite(knightTexture, 2, 0, 0, 0, WHITE, 2));
        else
            this.setSprite(new Sprite(knightTexture, 2, 0, 0, 1, WHITE, 2));
    }

    @Override
    public ArrayList<Pair> validMovements(Board board, boolean testingCheck) {

        ArrayList<Pair> newMovements = new ArrayList<>();
        char color = this.findPieceColor();

        Pair upRight = this.getBoardPosition().add(new Pair(+1, -2));
        Pair upLeft = this.getBoardPosition().add(new Pair(-1, -2));

        Pair downRight = this.getBoardPosition().add(new Pair(+1, +2));
        Pair downLeft = this.getBoardPosition().add(new Pair(-1, +2));

        Pair rightUp = this.getBoardPosition().add(new Pair(+2, -1));
        Pair rightDown = this.getBoardPosition().add(new Pair(+2, +1));

        Pair leftUp = this.getBoardPosition().add(new Pair(-2, -1));
        Pair leftDown = this.getBoardPosition().add(new Pair(-2, +1));

        if (upRight.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(upRight).findPieceColor())
            this.checkMovement(board, newMovements, upRight, testingCheck);

        if (upLeft.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(upLeft).findPieceColor())
            this.checkMovement(board, newMovements, upLeft, testingCheck);

        if (downRight.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(downRight).findPieceColor())
            this.checkMovement(board, newMovements, downRight, testingCheck);

        if (downLeft.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(downLeft).findPieceColor())
            this.checkMovement(board, newMovements, downLeft, testingCheck);

        if (rightUp.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(rightUp).findPieceColor())
            this.checkMovement(board, newMovements, rightUp, testingCheck);

        if (rightDown.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(rightDown).findPieceColor())
            this.checkMovement(board, newMovements, rightDown, testingCheck);

        if (leftUp.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(leftUp).findPieceColor())
            this.checkMovement(board, newMovements, leftUp, testingCheck);

        if (leftDown.isPieceInsideBoard(0, SIZE) && color != board.getPieceInPosition(leftDown).findPieceColor())
            this.checkMovement(board, newMovements, leftDown, testingCheck);

        if (testingCheck) {
            this.setMovements(newMovements);
        }

        return newMovements;
    }

}
