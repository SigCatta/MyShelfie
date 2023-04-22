package it.polimi.ingsw.Cli;

import it.polimi.ingsw.View.CLI.Printer;
import it.polimi.ingsw.model.tiles.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CLITest {
    private Printer printer;

    @BeforeEach
    public void init() {
        printer = new Printer();
    }

    @Test
    public void printBoardTest() {
        Color[][] board = {
                {null, null, null, Color.EMPTY, Color.EMPTY, null, null, null, null},
                {null, null, null, Color.EMPTY, Color.BLUE, Color.EMPTY, null, null, null},
                {null, null, Color.EMPTY, Color.GREEN, Color.YELLOW, Color.BLUE, Color.EMPTY, null, null},
                {null, Color.EMPTY, Color.BLUE, Color.BLUE, Color.YELLOW, Color.BLUE, Color.EMPTY, Color.EMPTY, null},
                {Color.EMPTY, Color.BLUE, Color.LIGHTBLUE, Color.GREEN, Color.WHITE, Color.LIGHTBLUE, Color.BLUE, Color.YELLOW, Color.EMPTY},
                {Color.EMPTY, Color.LIGHTBLUE, Color.PINK, Color.GREEN, Color.WHITE, Color.PINK, Color.LIGHTBLUE, Color.EMPTY, null},
                {null, null, Color.EMPTY, Color.PINK, Color.BLUE, Color.EMPTY, Color.EMPTY, null, null},
                {null, null, null, Color.EMPTY, Color.BLUE, Color.EMPTY, null, null, null},
                {null, null, null, null, Color.EMPTY, Color.EMPTY, null, null, null}
        };
        printer.setBoard(board);
        printer.printBoard();
    }

}
