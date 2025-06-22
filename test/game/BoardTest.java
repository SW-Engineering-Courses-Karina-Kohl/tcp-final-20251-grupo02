package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.pieces.*;
import misc.Pair;

public class BoardTest {
    @Test
    public void testExecuteMovePawnAhead() {
        Board board = new Board(false, true);
        Piece pawn = board.getPieceInPosition(0, 6);
        Piece target = board.getPieceInPosition(0, 5);

        Move move = new Move(pawn, target);
        board.executeMove(move);

        assertEquals(pawn, board.getPieceInPosition(0, 5));
        assertFalse(board.isTherePieceInPosition(0, 6));
    }

    @Test
    public void testExecuteCastling() {
        Board board = new Board(false, false);
        King king = new King(4, 7, 'K', false);
        Rook rook = new Rook(7, 7, 'R', false);

        board.setPieceInPosition(4, 7, king);
        board.setPieceInPosition(7, 7, rook);

        Move castlingMove = new Move(king, rook);
        board.executeMove(castlingMove);

        // After castling, king should be at 6,7 and rook at 5,7
        assertSame(king, board.getPieceInPosition(6, 7));
        assertSame(rook, board.getPieceInPosition(5, 7));
        assertTrue(king.hasMoved());
        assertTrue(rook.hasMoved());
    }

    @Test
    public void testExecuteMoveEnPassant() {
        Board board = new Board(false, false);
        Pawn whitePawn = new Pawn(3, 4, 'P', false);
        Pawn blackPawn = new Pawn(2, 4, 'p', false);

        board.setPieceInPosition(3, 6, whitePawn);
        board.setPieceInPosition(2, 4, blackPawn);

        Move lastMove = new Move(whitePawn, board.getPieceInPosition(3, 4));
        board.executeMove(lastMove);
        board.setLastMove(lastMove);

        assertTrue(whitePawn.hasMoved());
        assertFalse(blackPawn.hasMoved());

        Move enPassantMove = new Move(blackPawn, board.getPieceInPosition(3, 5));
        board.executeMove(enPassantMove);

        assertFalse(board.isTherePieceInPosition(3, 4));
        assertEquals(blackPawn, board.getPieceInPosition(3, 5));
        assertTrue(blackPawn.hasMoved());
    }

    @Test
    public void testUpdateBoardPawnAhead() {
        Board board = new Board(false, true);
        Piece pawn = board.getPieceInPosition(0, 6);
        Piece target = board.getPieceInPosition(0, 5);

        Move move = new Move(pawn, target);
        board.updateBoard(move);

        assertEquals(pawn, board.getPieceInPosition(0, 5));
        assertFalse(board.isTherePieceInPosition(0, 6));
    }

    @Test
    public void testUpdateBoardKnight() {
        Board board = new Board(false, true);
        Piece knight = board.getPieceInPosition(1, 7);
        Piece target = board.getPieceInPosition(2, 5);

        Move move = new Move(knight, target);
        board.updateBoard(move);

        assertEquals(knight, board.getPieceInPosition(2, 5));
        assertFalse(board.isTherePieceInPosition(1, 7));
    }

    @Test
    public void testUpdateBoardCapture() {
        Board board = new Board(false, false);
        Piece whiteQueen = new Queen(4, 4, 'Q', false);
        Piece blackBishop = new Bishop(2, 2, 'b', false);

        Move move = new Move(whiteQueen, blackBishop);
        board.updateBoard(move);

        assertEquals(whiteQueen, board.getPieceInPosition(2, 2));
        assertFalse(board.isTherePieceInPosition(4, 4));
    }

    @Test
    public void testMoveLeadsToCheckExposingKing() {
        Board board = new Board(false, false);

        King whiteKing = new King(4, 7, 'K', false);
        Queen whiteQueen = new Queen(4, 6, 'Q', false);
        Rook blackRook = new Rook(4, 0, 'r', false);

        board.setPieceInPosition(4, 7, whiteKing);
        board.setPieceInPosition(4, 6, whiteQueen);
        board.setPieceInPosition(4, 0, blackRook);

        assertTrue(board.moveLeadsToCheck(whiteQueen, 'w', new Pair(2, 6)));
        assertFalse(board.moveLeadsToCheck(whiteQueen, 'w', new Pair(4, 5)));
        assertFalse(board.moveLeadsToCheck(whiteKing, 'w', new Pair(3, 7)));
    }

    @Test
    public void testMoveLeadsToCheckKingMoves() {
        Board board = new Board(false, false);

        King blackKing = new King(3, 1, 'k', false);
        Queen whiteQueen = new Queen(4, 3, 'Q', false);

        board.setPieceInPosition(blackKing.getBoardPosition(), blackKing);
        board.setPieceInPosition(whiteQueen.getBoardPosition(), whiteQueen);

        assertFalse(board.moveLeadsToCheck(blackKing, 'b', new Pair(2, 0)));
        assertFalse(board.moveLeadsToCheck(blackKing, 'b', new Pair(3, 0)));
        assertTrue(board.moveLeadsToCheck(blackKing, 'b', new Pair(4, 0)));
        assertTrue(board.moveLeadsToCheck(blackKing, 'b', new Pair(2, 1)));
        assertTrue(board.moveLeadsToCheck(blackKing, 'b', new Pair(4, 1)));
        assertFalse(board.moveLeadsToCheck(blackKing, 'b', new Pair(2, 2)));
        assertTrue(board.moveLeadsToCheck(blackKing, 'b', new Pair(3, 2)));
        assertTrue(board.moveLeadsToCheck(blackKing, 'b', new Pair(4, 2)));
    }

@Test
    public void testCheckCheckmateYes() {
        Board board = new Board(false, false);

        King blackKing = new King(0, 0, 'k', false);
        Queen whiteQueen = new Queen(1, 1, 'Q', false);
        King whiteKing = new King(2, 2, 'K', false);

        board.setPieceInPosition(0, 0, blackKing);
        board.setPieceInPosition(1, 1, whiteQueen);
        board.setPieceInPosition(2, 2, whiteKing);

        assertTrue(board.checkCheckmate('b'));
    }

    @Test
    public void testCheckCheckmateNo() {
        Board board = new Board(false, false);

        King blackKing = new King(0, 0, 'k', false);
        Queen whiteQueen = new Queen(0, 2, 'Q', false);
        King whiteKing = new King(2, 2, 'K', false);

        board.setPieceInPosition(0, 0, blackKing);
        board.setPieceInPosition(0, 2, whiteQueen);
        board.setPieceInPosition(2, 2, whiteKing);

        assertFalse(board.checkCheckmate('b'));
    }

    @Test
    public void testCheckCastlingValidRight() {
        Board board = new Board(false, false);

        King king = new King(4, 7, 'K', false);
        Rook rook = new Rook(7, 7, 'R', false);

        board.setPieceInPosition(4, 7, king);
        board.setPieceInPosition(7, 7, rook);

        assertTrue(board.checkCastling(rook, king, 'r'));
    }

    @Test
    public void testCheckCastlingValidLeft() {
        Board board = new Board(false, false);

        King king = new King(4, 7, 'K', false);
        Rook rook = new Rook(0, 7, 'R', false);

        board.setPieceInPosition(4, 7, king);
        board.setPieceInPosition(0, 7, rook);

        assertTrue(board.checkCastling(rook, king, 'l'));
    }

    @Test
    public void testCheckCastlingPathAttacked() {
        Board board = new Board(false, false);

        King blackKing = new King(4, 0, 'k', false);
        Rook blackRook = new Rook(7, 0, 'r', false);
        Rook whiteRook = new Rook(5, 5, 'R', false); 

        board.setPieceInPosition(4, 0, blackKing);
        board.setPieceInPosition(7, 0, blackRook);
        board.setPieceInPosition(5, 5, whiteRook);

        assertFalse(board.checkCastling(blackRook, blackKing, 'r'));
    }

     @Test
    public void testCheckEnPassantWhiteRight() {
        Board board = new Board(false, false);

        Pawn whitePawn = new Pawn(3, 3, 'P', false);
        Pawn blackPawn = new Pawn(4, 3, 'p', false);

        board.setPieceInPosition(3, 3, whitePawn);
        board.setPieceInPosition(4, 3, blackPawn);

        Pawn lastMovedPawn = new Pawn(4, 1, 'p', false);
        Move lastMove = new Move(lastMovedPawn, blackPawn);
        board.setLastMove(lastMove);

        // White pawn can capture en passant to the right
        assertTrue(board.checkEnPassant(whitePawn, 'r'));
    }

    @Test
    public void testCheckEnPassantWhiteLeft() {
        Board board = new Board(false, false);

        Pawn whitePawn = new Pawn(4, 3, 'P', false);
        Pawn blackPawn = new Pawn(3, 3, 'p', false);

        board.setPieceInPosition(4, 3, whitePawn);
        board.setPieceInPosition(3, 3, blackPawn);

        Pawn lastMovedPawn = new Pawn(3, 1, 'p', false);
        Move lastMove = new Move(lastMovedPawn, blackPawn);
        board.setLastMove(lastMove);

        assertTrue(board.checkEnPassant(whitePawn, 'l'));
    }

    @Test
    public void testCheckEnPassantBlackRight() {
        Board board = new Board(false, false);

        Pawn blackPawn = new Pawn(3, 4, 'p', false);
        Pawn whitePawn = new Pawn(4, 4, 'P', false);

        board.setPieceInPosition(3, 4, blackPawn);
        board.setPieceInPosition(4, 4, whitePawn);

        Pawn lastMovedPawn = new Pawn(4, 6, 'P', false);
        Move lastMove = new Move(lastMovedPawn, whitePawn);
        board.setLastMove(lastMove);

        assertTrue(board.checkEnPassant(blackPawn, 'r'));
    }

    @Test
    public void testCheckEnPassantBlackLeft() {
        Board board = new Board(false, false);

        Pawn blackPawn = new Pawn(4, 4, 'p', false);
        Pawn whitePawn = new Pawn(3, 4, 'P', false);

        board.setPieceInPosition(4, 4, blackPawn);
        board.setPieceInPosition(3, 4, whitePawn);

        Pawn lastMovedPawn = new Pawn(3, 6, 'P', false);
        Move lastMove = new Move(lastMovedPawn, whitePawn);
        board.setLastMove(lastMove);

        assertTrue(board.checkEnPassant(blackPawn, 'l'));
    }

    @Test
    public void testCheckEnPassantFalseNotDoubleStep() {
        Board board = new Board(false, false);

        Pawn whitePawn = new Pawn(3, 3, 'P', false);
        Pawn blackPawn = new Pawn(4, 3, 'p', false);

        board.setPieceInPosition(3, 3, whitePawn);
        board.setPieceInPosition(4, 3, blackPawn);

        Pawn lastMovedPawn = new Pawn(4, 2, 'p', false);
        Move lastMove = new Move(lastMovedPawn, blackPawn);
        board.setLastMove(lastMove);

        assertFalse(board.checkEnPassant(whitePawn, 'r'));
    }
}
