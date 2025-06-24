package game.pieces;

import org.junit.jupiter.api.Test;

import game.*;
import misc.Pair;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PiecesTest {

    @Test
    public void testFindPieceColorNone() {
        Piece blank = new Blank(0, 0);

        assertEquals(blank.findPieceColor(), '_');
    }

    @Test
    public void testFindPieceColorBlack() {
        Piece blackBishop = new Bishop(2, 0, 'b', false);
        Piece blackKing = new King(4, 0, 'r', false);
        Piece blackKnight = new Knight(1, 0, 'c', false);
        Piece blackPawn = new Pawn(0, 1, 'p', false);
        Piece blackQueen = new Queen(3, 0, 'd', false);
        Piece blackRook = new Rook(0, 0, 't', false);

        assertEquals(blackBishop.findPieceColor(), 'b');
        assertEquals(blackKing.findPieceColor(), 'b');
        assertEquals(blackKnight.findPieceColor(), 'b');
        assertEquals(blackPawn.findPieceColor(), 'b');
        assertEquals(blackQueen.findPieceColor(), 'b');
        assertEquals(blackRook.findPieceColor(), 'b');

    }

    @Test
    public void testFindPieceColorWhite() {
        Piece whiteBishop = new Bishop(2, 7, 'B', false);
        Piece whiteKing = new King(4, 7, 'R', false);
        Piece whiteKnight = new Knight(1, 7, 'C', false);
        Piece whitePawn = new Pawn(0, 6, 'P', false);
        Piece whiteQueen = new Queen(3, 7, 'D', false);
        Piece whiteRook = new Rook(0, 7, 'T', false);

        assertEquals(whiteBishop.findPieceColor(), 'w');
        assertEquals(whiteKing.findPieceColor(), 'w');
        assertEquals(whiteKnight.findPieceColor(), 'w');
        assertEquals(whitePawn.findPieceColor(), 'w');
        assertEquals(whiteQueen.findPieceColor(), 'w');
        assertEquals(whiteRook.findPieceColor(), 'w');

    }

    @Test
    public void testMovePiece() {
        Piece whiteQueen = new Queen(3, 7, 'D', false);
        Piece blank = new Blank(7, 7);
        Move move = new Move(whiteQueen, blank);
        whiteQueen.movePiece(move);
        assertEquals(7, whiteQueen.getBoardPosition().x);
        assertEquals(7, whiteQueen.getBoardPosition().y);

    }

    @Test
    public void testValidMovementsBishop() {
        Board board = new Board(false, false);

        board.setPieceInPosition(3, 2, new Pawn(3, 2, 'p', false));
        board.setPieceInPosition(4, 2, new Rook(4, 2, 'r', false));
        board.setPieceInPosition(5, 2, new Pawn(5, 2, 'P', false));
        board.setPieceInPosition(2, 3, new Knight(2, 3, 'k', false));
        board.setPieceInPosition(6, 3, new Rook(6, 3, 'R', false));
        board.setPieceInPosition(2, 5, new Queen(2, 5, 'Q', false));
        board.setPieceInPosition(6, 5, new Bishop(6, 5, 'b', false));
        board.setPieceInPosition(4, 6, new Knight(4, 6, 'K', false));

        Bishop blackBishop = new Bishop(4, 3, 'b', false);

        ArrayList<Pair> returnedMovs = blackBishop.validMovements(board, false);

        ArrayList<Pair> expectedMovs = new ArrayList<>();
        expectedMovs.add(new Pair(5, 2));
        expectedMovs.add(new Pair(3, 4));
        expectedMovs.add(new Pair(2, 5));
        expectedMovs.add(new Pair(5, 4));

        Set<Pair> setReturned = new HashSet<>(returnedMovs);
        Set<Pair> setExpected = new HashSet<>(expectedMovs);

        assertEquals(setExpected, setReturned);
    }

    @Test
    public void testValidMovementsBlank() {
        Board board = new Board(false, false);

        board.setPieceInPosition(3, 2, new Pawn(3, 2, 'p', false));
        board.setPieceInPosition(4, 2, new Rook(4, 2, 'r', false));
        board.setPieceInPosition(5, 2, new Pawn(5, 2, 'P', false));
        board.setPieceInPosition(2, 3, new Knight(2, 3, 'k', false));
        board.setPieceInPosition(6, 3, new Rook(6, 3, 'R', false));
        board.setPieceInPosition(2, 5, new Queen(2, 5, 'Q', false));
        board.setPieceInPosition(6, 5, new Bishop(6, 5, 'b', false));
        board.setPieceInPosition(4, 6, new Knight(4, 6, 'K', false));

        Blank blank = new Blank(4, 3);

        ArrayList<Pair> returnedMovs = blank.validMovements(board, false);

        ArrayList<Pair> expectedMovs = new ArrayList<>();

        Set<Pair> setReturned = new HashSet<>(returnedMovs);
        Set<Pair> setExpected = new HashSet<>(expectedMovs);

        assertEquals(setExpected, setReturned);
    }

    @Test
    public void testValidMovementsKing() {
        Board board = new Board(false, false);

        board.setPieceInPosition(3, 2, new Pawn(3, 2, 'p', false));
        board.setPieceInPosition(4, 2, new Rook(4, 2, 'r', false));
        board.setPieceInPosition(5, 2, new Pawn(5, 2, 'P', false));
        board.setPieceInPosition(2, 3, new Knight(2, 3, 'k', false));
        board.setPieceInPosition(6, 3, new Rook(6, 3, 'R', false));
        board.setPieceInPosition(2, 5, new Queen(2, 5, 'Q', false));
        board.setPieceInPosition(6, 5, new Bishop(6, 5, 'b', false));
        board.setPieceInPosition(4, 6, new Knight(4, 6, 'K', false));

        King blackKing = new King(4, 3, 'k', false);

        ArrayList<Pair> returnedMovs = blackKing.validMovements(board, true);

        ArrayList<Pair> expectedMovs = new ArrayList<>();
        expectedMovs.add(new Pair(4, 4));

        Set<Pair> setReturned = new HashSet<>(returnedMovs);
        Set<Pair> setExpected = new HashSet<>(expectedMovs);

        assertEquals(setExpected, setReturned);
    }

    @Test
    public void testValidMovementsKnight() {
        Board board = new Board(false, false);

        board.setPieceInPosition(3, 2, new Pawn(3, 2, 'p', false));
        board.setPieceInPosition(4, 2, new Rook(4, 2, 'r', false));
        board.setPieceInPosition(5, 2, new Pawn(5, 2, 'P', false));
        board.setPieceInPosition(2, 3, new Knight(2, 3, 'k', false));
        board.setPieceInPosition(6, 3, new Rook(6, 3, 'R', false));
        board.setPieceInPosition(2, 5, new Queen(2, 5, 'Q', false));
        board.setPieceInPosition(6, 5, new Bishop(6, 5, 'b', false));
        board.setPieceInPosition(4, 6, new Knight(4, 6, 'K', false));

        Knight whiteKnight = new Knight(4, 3, 'H', false);

        ArrayList<Pair> returnedMovs = whiteKnight.validMovements(board, false);

        ArrayList<Pair> expectedMovs = new ArrayList<>();
        expectedMovs.add(new Pair(3, 1));
        expectedMovs.add(new Pair(5, 1));
        expectedMovs.add(new Pair(2, 2));
        expectedMovs.add(new Pair(6, 2));
        expectedMovs.add(new Pair(2, 4));
        expectedMovs.add(new Pair(6, 4));
        expectedMovs.add(new Pair(3, 5));
        expectedMovs.add(new Pair(5, 5));

        Set<Pair> setReturned = new HashSet<>(returnedMovs);
        Set<Pair> setExpected = new HashSet<>(expectedMovs);

        assertEquals(setExpected, setReturned);
    }

    @Test
    public void testValidMovementsPawn() {
        Board board = new Board(false, false);

        board.setPieceInPosition(3, 2, new Pawn(3, 2, 'p', false));
        board.setPieceInPosition(4, 2, new Rook(4, 2, 'r', false));
        board.setPieceInPosition(5, 2, new Pawn(5, 2, 'P', false));
        board.setPieceInPosition(2, 3, new Knight(2, 3, 'k', false));
        board.setPieceInPosition(6, 3, new Rook(6, 3, 'R', false));
        board.setPieceInPosition(2, 5, new Queen(2, 5, 'Q', false));
        board.setPieceInPosition(6, 5, new Bishop(6, 5, 'b', false));
        board.setPieceInPosition(4, 6, new Knight(4, 6, 'K', false));

        Pawn whitePawn = new Pawn(4, 3, 'P', false);

        ArrayList<Pair> returnedMovs = whitePawn.validMovements(board, false);

        ArrayList<Pair> expectedMovs = new ArrayList<>();
        expectedMovs.add(new Pair(3, 2));

        Set<Pair> setReturned = new HashSet<>(returnedMovs);
        Set<Pair> setExpected = new HashSet<>(expectedMovs);

        assertEquals(setExpected, setReturned);
    }

    @Test
    public void testValidMovementsQueen() {
        Board board = new Board(false, false);
        board.setPieceInPosition(3, 2, new Pawn(3, 2, 'p', false));
        board.setPieceInPosition(4, 2, new Rook(4, 2, 'r', false));
        board.setPieceInPosition(5, 2, new Pawn(5, 2, 'P', false));
        board.setPieceInPosition(2, 3, new Knight(2, 3, 'k', false));
        board.setPieceInPosition(6, 3, new Rook(6, 3, 'R', false));
        board.setPieceInPosition(2, 5, new Queen(2, 5, 'Q', false));
        board.setPieceInPosition(6, 5, new Bishop(6, 5, 'b', false));
        board.setPieceInPosition(4, 6, new Knight(4, 6, 'K', false));

        Queen whiteQueen = new Queen(4, 3, 'Q', false);

        ArrayList<Pair> returnedMovs = whiteQueen.validMovements(board, false);

        ArrayList<Pair> expectedMovs = new ArrayList<>();
        expectedMovs.add(new Pair(3, 2));
        expectedMovs.add(new Pair(4, 2));
        expectedMovs.add(new Pair(2, 3));
        expectedMovs.add(new Pair(3, 3));
        expectedMovs.add(new Pair(5, 3));
        expectedMovs.add(new Pair(3, 4));
        expectedMovs.add(new Pair(4, 4));
        expectedMovs.add(new Pair(5, 4));
        expectedMovs.add(new Pair(4, 5));
        expectedMovs.add(new Pair(6, 5));

        Set<Pair> setReturned = new HashSet<>(returnedMovs);
        Set<Pair> setExpected = new HashSet<>(expectedMovs);

        assertEquals(setExpected, setReturned);
    }

    @Test
    public void testValidMovementsRook() {
        Board board = new Board(false, false);

        board.setPieceInPosition(3, 2, new Pawn(3, 2, 'p', false));
        board.setPieceInPosition(4, 2, new Rook(4, 2, 'r', false));
        board.setPieceInPosition(5, 2, new Pawn(5, 2, 'P', false));
        board.setPieceInPosition(2, 3, new Knight(2, 3, 'k', false));
        board.setPieceInPosition(6, 3, new Rook(6, 3, 'R', false));
        board.setPieceInPosition(2, 5, new Queen(2, 5, 'Q', false));
        board.setPieceInPosition(6, 5, new Bishop(6, 5, 'b', false));
        board.setPieceInPosition(4, 6, new Knight(4, 6, 'K', false));

        Rook blackRook = new Rook(4, 3, 'r', false);

        ArrayList<Pair> returnedMovs = blackRook.validMovements(board, false);

        ArrayList<Pair> expectedMovs = new ArrayList<>();
        expectedMovs.add(new Pair(3, 3));
        expectedMovs.add(new Pair(5, 3));
        expectedMovs.add(new Pair(6, 3));
        expectedMovs.add(new Pair(4, 4));
        expectedMovs.add(new Pair(4, 5));
        expectedMovs.add(new Pair(4, 6));

        Set<Pair> setReturned = new HashSet<>(returnedMovs);
        Set<Pair> setExpected = new HashSet<>(expectedMovs);

        assertEquals(setExpected, setReturned);
    }
}
