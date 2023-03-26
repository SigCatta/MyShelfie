package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class represents a buffer for ItemTiles that may be used to stock several tiles before adding
 * them to a shelf. The tiles may be ordered according to their color.
 */
public class ShelfBuffer {

    /**
     * The list of ItemTiles to be inserted.
     */
    private List<ItemTile> tilesToBeInserted ;

    /**
     * The status of the list. If ordered is true, the list is ordered, otherwise it's not.
     */
    private boolean ordered;

    public ShelfBuffer() {
        tilesToBeInserted = null;
    }

    /**
     * @return the list of ItemTiles to be inserted.
     */
    public List<ItemTile> getTiles() {
        return tilesToBeInserted;
    }

    /**
     * This method sets the list of ItemTiles to be inserted.
     * @param tilesToBeInserted the list of ItemTiles to be inserted.
     */
    public void setTiles(List<ItemTile> tilesToBeInserted) {
        this.tilesToBeInserted = new ArrayList<>(tilesToBeInserted);
        ordered = false;
    }

    /**
     * This method sorts the list of ItemTiles according to a list of colors.
     * @param sortedColor the list of colors to use as sorting criteria.
     */
    public void sortTiles(List<Color> sortedColor) {
        if (sortedColor.size() != tilesToBeInserted.size()) {
            return;
        }

        // Create a Comparator based on the sorted colors
        Comparator<ItemTile> colorComparator = (tile1, tile2) -> {
            int index1 = sortedColor.indexOf(tile1.getColor());
            int index2 = sortedColor.indexOf(tile2.getColor());
            return Integer.compare(index1, index2);
        };

        // Sort the tiles using the Comparator
        tilesToBeInserted.sort(colorComparator);

        ordered = true;
    }

    /**
     * @return true if the list is ordered, false otherwise.
     */
    public boolean isOrdered() {
        return ordered;
    }
}