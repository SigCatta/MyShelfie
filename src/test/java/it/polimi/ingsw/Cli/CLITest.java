package it.polimi.ingsw.Cli;

import it.polimi.ingsw.View.CLI.Printer;
import it.polimi.ingsw.model.tiles.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CLITest {
    private Printer printer;

    @BeforeEach
    public void init() {
        printer = new Printer(true);
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
        printer.printBoard(new ArrayList<>()).forEach(System.out::println);
    }

    @Test
    public void printMainShelfTest() {
        Color[][] mainShelf = {
                {Color.BLUE, Color.WHITE, Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.PINK, Color.YELLOW, Color.PINK, Color.EMPTY},
                {Color.YELLOW, Color.PINK, Color.LIGHTBLUE, Color.BLUE, Color.EMPTY},
                {Color.YELLOW, Color.PINK, Color.GREEN, Color.EMPTY, Color.GREEN},
                {Color.WHITE, Color.BLUE, Color.GREEN, Color.EMPTY, Color.EMPTY},
                {Color.LIGHTBLUE, Color.LIGHTBLUE, Color.LIGHTBLUE, Color.LIGHTBLUE, Color.LIGHTBLUE},
        };

        printer.setMainShelf(mainShelf);
        printer.printMainShelf(new ArrayList<>()).forEach(System.out::println);
    }

    @Test
    public void printBoardShelfTest() { // tests the ability to build objects on top of each other
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
        ArrayList<String> boardPrint = printer.printBoard(new ArrayList<>());


        Color[][] mainShelf = {
                {Color.BLUE, Color.WHITE, Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.PINK, Color.YELLOW, Color.PINK, Color.EMPTY},
                {Color.YELLOW, Color.PINK, Color.LIGHTBLUE, Color.BLUE, Color.EMPTY},
                {Color.YELLOW, Color.PINK, Color.GREEN, Color.EMPTY, Color.GREEN},
                {Color.WHITE, Color.BLUE, Color.GREEN, Color.EMPTY, Color.EMPTY},
                {Color.LIGHTBLUE, Color.LIGHTBLUE, Color.LIGHTBLUE, Color.LIGHTBLUE, Color.LIGHTBLUE},
        };

        printer.setMainShelf(mainShelf);
        printer.printMainShelf(boardPrint).forEach(System.out::println);
    }

}
