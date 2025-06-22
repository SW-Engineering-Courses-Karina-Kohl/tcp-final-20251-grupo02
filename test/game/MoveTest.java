package game;

import game.pieces.*;
import misc.Pair;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    public void testIsEnPassantOK() {
        Board board = new Board(false, false);
        Pawn whitePawn = new Pawn(3, 4, 'P', false);
        Pawn blackPawn = new Pawn(2, 4, 'p', false);

        board.setPieceInPosition(whitePawn.getBoardPosition(), whitePawn);
        board.setPieceInPosition(blackPawn.getBoardPosition(), blackPawn);

        Move enPassantMove = new Move(blackPawn, board.getPieceInPosition(3, 5));
        assertTrue(enPassantMove.isEnPassant(board));
    }

    @Test
    public void testIsEnPassantNotOK_notPawn() {
        Board board = new Board(false, false);
        board.setPieceInPosition(3, 4, new Rook(3, 4, 'R', false));
        board.setPieceInPosition(2, 4, new Pawn(2, 4, 'p', false));
        Move move = new Move(board.getPieceInPosition(3, 4), board.getPieceInPosition(3, 5));
        assertFalse(move.isEnPassant(board));
    }

    @Test
    public void testIsEnPassantNotOK_moreThan1Column() {
        Board board = new Board(false, false);
        board.setPieceInPosition(3, 4, new Pawn(3, 4, 'P', false));
        board.setPieceInPosition(2, 4, new Pawn(2, 4, 'p', false));
        Move move = new Move(board.getPieceInPosition(2, 4), board.getPieceInPosition(4, 5));
        assertFalse(move.isEnPassant(board));
    }

    @Test
    public void testIsEnPassantNotOK_noPawnInDirection() {
        Board board = new Board(false, false);
        board.setPieceInPosition(3, 4, new Pawn(3, 4, 'P', false));
        board.setPieceInPosition(2, 4, new Pawn(2, 4, 'p', false));
        Move move = new Move(board.getPieceInPosition(2, 4), board.getPieceInPosition(1, 5));
        assertFalse(move.isEnPassant(board));
    }

    @Test
    public void testIsEnPassantNotOK_pieceAlreadyInPosition() {
        Board board = new Board(false, false);
        board.setPieceInPosition(3, 4, new Pawn(3, 4, 'P', false));
        board.setPieceInPosition(2, 4, new Pawn(2, 4, 'p', false));
        board.setPieceInPosition(3,5, new Pawn(3, 5, 'P', false));
        Move move = new Move(board.getPieceInPosition(2, 4), board.getPieceInPosition(3, 5));
        assertFalse(move.isEnPassant(board));
    }

    @Test
    public void isCastling() {
        King whiteKing = new King(4, 7, 'K', false);
        King blackKing = new King(4, 0, 'k', false);
        Rook wRook1 = new Rook(0, 7, 'R', false);
        Rook wRook2 = new Rook(7, 7, 'R', false);
        Rook bRook1 = new Rook(0, 0, 'r', false);
        Rook bRook2 = new Rook(7, 0, 'r', false);

        assertTrue(new Move(whiteKing, wRook1).isCastling());
        assertTrue(new Move(wRook2, whiteKing).isCastling());

        assertTrue(new Move(bRook1, blackKing).isCastling());
        assertTrue(new Move(blackKing, bRook2).isCastling());

        assertFalse(new Move(bRook1, bRook2).isCastling());
        assertFalse(new Move(whiteKing, bRook2).isCastling());
        assertFalse(new Move(whiteKing, blackKing).isCastling());

    }

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
