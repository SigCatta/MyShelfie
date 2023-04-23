package it.polimi.ingsw.virtualModel;

import it.polimi.ingsw.model.tiles.Color;

public class Shelf {

    private Color[][] shelf;

    public Shelf(){
        shelf= new Color[5][6];
    }

    public Color[][] getShelf() {
        return shelf;
    }
}
