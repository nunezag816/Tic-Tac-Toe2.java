package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameTest {
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void simulateInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testPlayerXWins() {
        // Player X wins on top row
        String simulatedInput = String.join("\n", new String[]{
            "1",  // X
            "4",  // O
            "2",  // X
            "5",  // O
            "3",  // X wins
            "no"  // don't play again
        });

        simulateInput(simulatedInput);

        TicTacToeGame game = new TicTacToeGame();
        game.run();

        String output = outContent.toString();
        assertTrue(output.contains("Player X wins!"));
        assertTrue(output.contains("Would you like to play again"));
    }

    @Test
    void testDrawGame() {
        String simulatedInput = String.join("\n", new String[]{
            "1", "2", "3", "5", "4", "6", "8", "7", "9",  // draw
            "no"
        });

        simulateInput(simulatedInput);

        TicTacToeGame game = new TicTacToeGame();
        game.run();

        String output = outContent.toString();
        assertTrue(output.contains("It's a draw!"));
    }

    @Test
    void testInvalidMoveAndRetry() {
        String simulatedInput = String.join("\n", new String[]{
            "1",   // X
            "1",   // O tries invalid move
            "2",   // O valid move
            "3", "4", "5", "6", "7", "8", "9",  // rest of game
            "no"
        });

        simulateInput(simulatedInput);

        TicTacToeGame game = new TicTacToeGame();
        game.run();

        String output = outContent.toString();
        assertTrue(output.contains("That is not a valid move! Try again."));
    }

    @Test
    void testInvalidReplayInput() {
        String simulatedInput = String.join("\n", new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9",  // draw
            "foobar", "yes",  // invalid input then replay
            "1", "2", "3", "4", "5", "6", "7", "8", "9",  // draw
            "no"
        });

        simulateInput(simulatedInput);

        TicTacToeGame game = new TicTacToeGame();
        game.run();

        String output = outContent.toString();
        assertTrue(output.contains("That is not a valid entry!"));
        assertTrue(output.contains("Would you like to play again"));
    }
}
