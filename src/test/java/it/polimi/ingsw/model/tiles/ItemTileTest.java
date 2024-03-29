package it.polimi.ingsw.model.tiles;

import it.polimi.ingsw.Enum.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTileTest {

    private static ItemTile itemTile;

    @Test
    public void string() {
        System.out.println(Color.GREEN);
    }

    @Test
    public void testGreen() {
        itemTile = new ItemTile(Color.GREEN);
        assertEquals(Color.GREEN, itemTile.getColor());
    }

    @Test
    public void testYellow() {
        itemTile = new ItemTile(Color.YELLOW);
        assertEquals(Color.YELLOW, itemTile.getColor());
    }

    @Test
    public void testBlue() {
        itemTile = new ItemTile(Color.BLUE);
        assertEquals(Color.BLUE, itemTile.getColor());
    }

    @Test
    public void testLightblue() {
        itemTile = new ItemTile(Color.LIGHTBLUE);
        assertEquals(Color.LIGHTBLUE, itemTile.getColor());
    }

    @Test
    public void testPink() {
        itemTile = new ItemTile(Color.PINK);
        assertEquals(Color.PINK, itemTile.getColor());
    }

    @Test
    public void testWhite() {
        itemTile = new ItemTile(Color.WHITE);
        assertEquals(Color.WHITE, itemTile.getColor());
    }
}