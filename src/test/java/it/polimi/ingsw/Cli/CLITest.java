package it.polimi.ingsw.Cli;

import it.polimi.ingsw.Controller.Client.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.View.CLI.BoardView;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CLITest implements VirtualModelObserver {

    @Test
    public void printBoardTest() {

        ItemTile[][] board = {
                {null, null, null, new ItemTile(Color.EMPTY), new ItemTile(Color.EMPTY), null, null, null, null},
                {null, null, null, new ItemTile(Color.EMPTY), new ItemTile(Color.BLUE), new ItemTile(Color.EMPTY), null, null, null},
                {null, null, new ItemTile(Color.EMPTY), new ItemTile(Color.GREEN), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.EMPTY), null, null},
                {null, new ItemTile(Color.EMPTY), new ItemTile(Color.BLUE), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.BLUE), new ItemTile(Color.EMPTY), new ItemTile(Color.EMPTY), null},
                {new ItemTile(Color.EMPTY), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.BLUE), new ItemTile(Color.YELLOW), new ItemTile(Color.EMPTY)},
                {new ItemTile(Color.EMPTY), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.PINK), new ItemTile(Color.GREEN), new ItemTile(Color.WHITE), new ItemTile(Color.PINK), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.EMPTY), null},
                {null, null, new ItemTile(Color.EMPTY), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.EMPTY), new ItemTile(Color.EMPTY), null, null},
                {null, null, null, new ItemTile(Color.EMPTY), new ItemTile(Color.BLUE), new ItemTile(Color.EMPTY), null, null, null},
                {null, null, null, null, new ItemTile(Color.EMPTY), new ItemTile(Color.EMPTY), null, null, null}
        };
        BoardRepresentation.getInstance().registerObserver(this);
        BoardRepresentation.getInstance().setBoard(board);
        new BoardView().printBoard(new ArrayList<>()).forEach(System.out::println);
    }

    @Override
    public void update() {}
}
