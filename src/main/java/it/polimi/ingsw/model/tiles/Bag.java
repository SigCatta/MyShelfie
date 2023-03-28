package it.polimi.ingsw.model.tiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Bag {

    private final int NUMBER_OF_COLORS;
    private final int TILES_PER_COLOR = 22;
    private int tilesLeft;
    private final HashMap<Color, Integer> colorNumber = new HashMap<>();

    public Bag(){

        NUMBER_OF_COLORS = Color.values().length;
        tilesLeft = NUMBER_OF_COLORS * TILES_PER_COLOR;

        for(Color color : Color.values()){
            colorNumber.put(color, TILES_PER_COLOR);
        }

    }


    /**
     * Draws a specified number of item tiles from the bag, returning them in an ArrayList.
     * It uses a method that generates a random color with a probability proportional
     * to the number of tiles in the bag
     *
     *      @param numberOfTiles the number of item tiles to draw from the bag
     *      @return an ArrayList containing the drawn item tiles
     */
    public ArrayList<ItemTile> drawItemTiles(int numberOfTiles){


        //in case of running out of tiles
        if(numberOfTiles > tilesLeft){
            for(Color color : Color.values()){
                colorNumber.put(color, (numberOfTiles - tilesLeft)/NUMBER_OF_COLORS );
            }
        }

        ArrayList<ItemTile> itemTileList = new ArrayList<>();

        for(int i = 0; i < numberOfTiles; i++){
            itemTileList.add(new ItemTile(randomColor()));
        }

        return itemTileList;
    }

    public ItemTile drawSingleTile(){
        return new ItemTile(randomColor());
    }


    /**
     * this method uses a random variable to get a random number from 0 to tilesLeft
     * partialCount is used to iterate through the map until the sum of the values
     * reaches the random variable, in that case it returns the current color
     * @return random color
     */
    Color randomColor(){

        double random = Math.random() * tilesLeft;
        tilesLeft--;

        int partialCount = 0;
        for(Color color : Color.values()){

            partialCount += colorNumber.get(color);

            if(partialCount >= random){
                colorNumber.replace(color, colorNumber.get(color) - 1);
                return color;
            }
        }

        return Color.BLUE; //TODO update after clarification of the rules
    }
}
