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
