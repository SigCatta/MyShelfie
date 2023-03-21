package model.itemCreation;

import model.gameItems.Tiles.ItemTile;
import model.gameItems.Tiles.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Bag {
    private final int NUMBER_OF_COLORS;
    private final int TILES_PER_COLOR = 22;
    private int tileCount;
    private final HashMap<Color, Integer> colorNumber = new HashMap<>();

    public Bag(){
        NUMBER_OF_COLORS = Color.values().length;
        tileCount = NUMBER_OF_COLORS * TILES_PER_COLOR;
        for(Color color : Color.values()){
            colorNumber.put(color, TILES_PER_COLOR);
        }
    }



    public ArrayList<ItemTile> drawItemTiles(int numberOfTiles){

        if(numberOfTiles > tileCount){
            for(Color color : Color.values()){
                colorNumber.put(color, (numberOfTiles - tileCount)/NUMBER_OF_COLORS );
            }
        }

        ArrayList<ItemTile> itemTileList = new ArrayList<>();

        for(int i = 0; i < numberOfTiles; i++){
            itemTileList.add(new ItemTile(randomColor()));
        }

        return itemTileList;
    }

    public ItemTile drawSingleTiles(){
        return new ItemTile(randomColor());
    }

    private Color randomColor(){

        double random = Math.random() * tileCount;
        tileCount--;

        int partialCount = 0;
        for(Color color : Color.values()){

            partialCount += colorNumber.get(color);
            if(partialCount >= random){
                return color;
            }
        }

        return Color.BLUE; //TODO update after clasrifitaion of the rules
    }
}
