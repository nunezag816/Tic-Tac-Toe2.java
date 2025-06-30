package game;

import java.util.Scanner;

public class TicTacToeGame {
    private final Scanner scanner = new Scanner(System.in);
    private final GameBoard board = new GameBoard();
    private char currentPlayer = 'X';

    public void run() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playAgain;

        do {
            board.reset();
            currentPlayer = 'X';
            playGame();

            char winner = board.checkWinner();
            if (winner == 'X' || winner == 'O') {
                System.out.println("\nPlayer " + winner + " wins!");
            } else {
                System.out.println("\nIt's a draw!");
            }

            playAgain = askPlayAgain();
        } while (playAgain);

        System.out.println("Goodbye!");
    }

    private void playGame() {
        while (!board.isFull() && board.checkWinner() == '-') {
            board.display();
            System.out.print("\nWhat is your move? ");
            int move = getValidMove();
            board.makeMove(move, currentPlayer);
            if (board.checkWinner() == '-') {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
        board.display();
    }

    private int getValidMove() {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                int move = Integer.parseInt(input);
                if (board.isValidMove(move)) {
                    return move;
                }
                System.out.println("\nThat is not a valid move! Try again.");
            } catch (NumberFormatException e) {
                System.out.println("\nThat is not a valid move! Try again.");
            }

            board.display();
            System.out.print("\nWhat is your move? ");
        }
    }

    private boolean askPlayAgain() {
        while (true) {
            System.out.print("\nWould you like to play again (yes/no)? ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) return true;
            if (response.equals("no")) return false;

            System.out.println("That is not a valid entry!");
        }
    }
}
