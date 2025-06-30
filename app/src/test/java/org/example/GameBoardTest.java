package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    private GameBoard board;

    @BeforeEach
    void setUp() {
        board = new GameBoard();
        board.reset();
    }

    @Test
    void testValidMove() {
        assertTrue(board.isValidMove(1));
        board.makeMove(1, 'X');
        assertFalse(board.isValidMove(1)); // already taken
    }

    @Test
    void testInvalidMoveOutOfRange() {
        assertFalse(board.isValidMove(0));
        assertFalse(board.isValidMove(10));
    }

    @Test
    void testMakeMoveUpdatesBoard() {
        board.makeMove(5, 'O');
        assertFalse(board.isValidMove(5));
    }

    @Test
    void testResetBoardClearsAllMoves() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.reset();
        assertTrue(board.isValidMove(1));
        assertTrue(board.isValidMove(2));
    }

    @Test
    void testWinnerInRow() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    void testWinnerInColumn() {
        board.makeMove(1, 'O');
        board.makeMove(4, 'O');
        board.makeMove(7, 'O');
        assertEquals('O', board.checkWinner());
    }

    @Test
    void testWinnerInDiagonal() {
        board.makeMove(1, 'X');
        board.makeMove(5, 'X');
        board.makeMove(9, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    void testDrawGame() {
        char[] moves = {
            'X', 'O', 'X',
            'X', 'O', 'O',
            'O', 'X', 'X'
        };
        for (int i = 0; i < 9; i++) {
            board.makeMove(i + 1, moves[i]);
        }
        assertEquals('-', board.checkWinner());
        assertTrue(board.isFull());
    }

    @Test
    void testBoardNotFullWhenEmpty() {
        assertFalse(board.isFull());
    }

    @Test
    void testBoardFullWhenAllFilled() {
        for (int i = 1; i <= 9; i++) {
            board.makeMove(i, 'X');
        }
        assertTrue(board.isFull());
    }
}
