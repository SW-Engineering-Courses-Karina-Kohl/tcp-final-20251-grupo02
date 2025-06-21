package game.pieces;

import org.junit.jupiter.api.Test;

import game.Move;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testMovePiece(){
        Piece whiteQueen = new Queen(3, 7, 'D', false);
        Piece blank = new Blank(7,7);
        Move move = new Move(whiteQueen,blank);
        whiteQueen.movePiece(move);
        assertEquals(7, whiteQueen.getBoardPosition().x);
        assertEquals(7, whiteQueen.getBoardPosition().y);

    } 
}
