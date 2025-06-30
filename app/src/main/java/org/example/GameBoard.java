package org.example;

public class GameBoard {
    private final char[] board = new char[9];

    public void reset() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    public boolean isValidMove(int move) {
        return move >= 1 && move <= 9 && board[move - 1] == ' ';
    }

    public void makeMove(int move, char player) {
        board[move - 1] = player;
    }

    public boolean isFull() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    public char checkWinner() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] wc : winConditions) {
            char a = board[wc[0]], b = board[wc[1]], c = board[wc[2]];
            if (a != ' ' && a == b && b == c) return a;
        }

        return '-';
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print("  " + (board[i] == ' ' ? (i + 1) : board[i]) + "  ");
            if (i % 3 != 2) System.out.print("|");
            if (i % 3 == 2 && i < 8) System.out.println("\n-----+-----+-----");
        }
        System.out.println();
    }
}
