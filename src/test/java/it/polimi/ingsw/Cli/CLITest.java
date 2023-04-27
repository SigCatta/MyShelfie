package it.polimi.ingsw.Cli;

import it.polimi.ingsw.Controller.Client.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.View.CLI.BoardView;
import it.polimi.ingsw.View.CLI.ShelfView;
import it.polimi.ingsw.View.VirtualView.Messages.ShelfMessage;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import java.awt.*;
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

    @Test
    public void printShelfBigTest(){
        Player player = new Player("test");
        Shelf shelf = player.getShelf();
        shelf.setTileAtLocation(new Point(0,0), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(1,0), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(2,0), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(3,0), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(4,0), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(5,0), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(0,1), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(0,2), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(0,3), new ItemTile(Color.GREEN));
        shelf.setTileAtLocation(new Point(0,4), new ItemTile(Color.GREEN));

        ShelvesRepresentation.getInstance().updateShelf(new ShelfMessage(player));
        new ShelfView().printShelfBig(new ArrayList<>(), "test").forEach(System.out::println);
    }


    @Test
    public void printShelfandBoardTest(){
        printBoardTest();
        printShelfBigTest();
    }


    @Override
    public void update() {}
}
