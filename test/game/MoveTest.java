package game;

import game.pieces.*;
import misc.Pair;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    public void testValidateMoveOK() {

        Board board = new Board(false, true);

        Piece blackPawn = board.getPieceInPosition(0, 1);
        Piece spaceAhead = board.getPieceInPosition(0, 2);

        Move moveAhead = new Move(blackPawn, spaceAhead);

        ArrayList<Pair> movementAhead = new ArrayList<Pair>();
        movementAhead.add(new Pair(0, 2));

        blackPawn.setMovements(movementAhead);

        assertTrue(moveAhead.validateMove(board));

    }

    @Test
    public void testValidateMoveNotOK() {
        Board board = new Board(false, true);

        Piece blackPawn = board.getPieceInPosition(0, 1);
        Piece spaceDiagonal = board.getPieceInPosition(0, 2);

        Move moveAhead = new Move(blackPawn, spaceDiagonal);

        assertFalse(moveAhead.validateMove(board));
    }

    @Test
    public void testCheckPawnPromotion() {
        Board board = new Board(false, true);

        Piece whitePawn = board.getPieceInPosition(7, 6);
        Move whiteNotPromotion = new Move(whitePawn, board.getPieceInPosition(7, 4));
        board.updateBoard(whiteNotPromotion);
        whitePawn.movePiece(whiteNotPromotion);
        assertFalse(whiteNotPromotion.checkPawnPromotion(board));

        Move whitePromotion = new Move(whitePawn, board.getPieceInPosition(7, 0));
        board.updateBoard(whitePromotion);
        whitePawn.movePiece(whitePromotion);
        assertTrue(whitePromotion.checkPawnPromotion(board));

        Piece blackPawn = board.getPieceInPosition(0, 1);
        Move blackNotPromotion = new Move(blackPawn, board.getPieceInPosition(0, 3));
        board.updateBoard(blackNotPromotion);
        blackPawn.movePiece(blackNotPromotion);
        assertFalse(blackNotPromotion.checkPawnPromotion(board));

        Move blackPromotion = new Move(blackPawn, board.getPieceInPosition(0, 7));
        board.updateBoard(blackPromotion);
        blackPawn.movePiece(blackPromotion);
        assertTrue(blackPromotion.checkPawnPromotion(board));
        assertFalse(false);
    }

    @Test
    public void testPromoteBishop() {
        Board board = new Board(false, true);

        Piece whitePawn = board.getPieceInPosition(7, 6);
        Move whitePromotion = new Move(whitePawn, board.getPieceInPosition(7, 0));
        board.updateBoard(whitePromotion);
        whitePawn.movePiece(whitePromotion);

        ByteArrayInputStream input = new ByteArrayInputStream("B\n1".getBytes());
        System.setIn(input);
        assertTrue(whitePromotion.promote(board, null, false));

        assertTrue(board.getPieceInPosition(7, 0) instanceof Bishop);
    }

    @Test
    public void testPromoteKnight() {
        Board board = new Board(false, true);

        Piece whitePawn = board.getPieceInPosition(7, 6);
        Move whitePromotion = new Move(whitePawn, board.getPieceInPosition(7, 0));
        board.updateBoard(whitePromotion);
        whitePawn.movePiece(whitePromotion);

        ByteArrayInputStream input = new ByteArrayInputStream("H\n1".getBytes());
        System.setIn(input);
        assertTrue(whitePromotion.promote(board, null, false));

        assertTrue(board.getPieceInPosition(7, 0) instanceof Knight);
    }

    @Test
    public void testPromoteQueen() {
        Board board = new Board(false, true);

        Piece whitePawn = board.getPieceInPosition(7, 6);
        Move whitePromotion = new Move(whitePawn, board.getPieceInPosition(7, 0));
        board.updateBoard(whitePromotion);
        whitePawn.movePiece(whitePromotion);

        ByteArrayInputStream input = new ByteArrayInputStream("Q\n1".getBytes());
        System.setIn(input);
        assertTrue(whitePromotion.promote(board, null, false));

        assertTrue(board.getPieceInPosition(7, 0) instanceof Queen);
    }

    @Test
    public void testPromoteRook() {
        Board board = new Board(false, true);

        Piece whitePawn = board.getPieceInPosition(7, 6);
        Move whitePromotion = new Move(whitePawn, board.getPieceInPosition(7, 0));
        board.updateBoard(whitePromotion);
        whitePawn.movePiece(whitePromotion);

        ByteArrayInputStream input = new ByteArrayInputStream("R\n1".getBytes());
        System.setIn(input);
        assertTrue(whitePromotion.promote(board, null, false));

        assertTrue(board.getPieceInPosition(7, 0) instanceof Rook);
    }
}
