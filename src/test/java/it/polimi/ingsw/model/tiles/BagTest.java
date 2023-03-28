package it.polimi.ingsw.model.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class BagTest {
    private Bag bag;

    @BeforeEach
    void setUp() {
        bag = new Bag();
    }

    @Test
    void drawItemTiles() {
        ArrayList<ItemTile> itemTiles = bag.drawItemTiles(10);
        Assertions.assertEquals(10, itemTiles.size());
        for(ItemTile itemTile : itemTiles){
            Assertions.assertNotNull(itemTile);
        }
    }

    @Test
    void drawSingleTile() {
        ItemTile itemTile = bag.drawSingleTile();
        Assertions.assertNotNull(itemTile);
    }

    @Test
    void randomColor() {
        int blueCount = 0;
        int greenCount = 0;
        int yellowCount = 0;
        int lightBlueCount = 0;
        int pinkCount = 0;
        int whiteCount = 0;

        for(int i = 0; i < 132; i++){
            Color color = bag.randomColor();
            switch (color){
                case BLUE:
                    blueCount++;
                    break;
                case GREEN:
                    greenCount++;
                    break;
                case YELLOW:
                    yellowCount++;
                    break;
                case LIGHTBLUE:
                    lightBlueCount++;
                    break;
                case PINK:
                    pinkCount++;
                    break;
                case WHITE:
                    whiteCount++;
                    break;
            }
        }

        Assertions.assertEquals(22, blueCount);
        Assertions.assertEquals(22, greenCount);
        Assertions.assertEquals(22, yellowCount);
        Assertions.assertEquals(22, lightBlueCount);
        Assertions.assertEquals(22, pinkCount);
        Assertions.assertEquals(22, whiteCount);

    }
}
