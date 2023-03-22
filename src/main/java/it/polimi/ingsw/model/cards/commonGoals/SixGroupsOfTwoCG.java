package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.*;

public class SixGroupsOfTwoCG extends CommonGoal{
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return sixGrouppsFound(shelf.getShelfGrid());
    }

    public boolean sixGrouppsFound(List<Stack<ItemTile>> shelfGrid) {
        // Create a map to keep track of groups of adjacent tiles with the same color
        Map<ItemTile, Set<ItemTile>> groups = new HashMap<>();

        // Check for adjacent tiles with the same color horizontally
        for (Stack<ItemTile> stack : shelfGrid) {
            ItemTile prevTile = null;
            for (ItemTile tile : stack) {
                if (prevTile != null && prevTile.getColor() == tile.getColor()) {
                    // Found two adjacent tiles with the same color
                    if (!groups.containsKey(prevTile)) {
                        groups.put(prevTile, new HashSet<>());
                    }
                    groups.get(prevTile).add(tile);
                }
                prevTile = tile;
            }
        }

        // Check for adjacent tiles with the same color vertically
        for (int i = 0; i < shelfGrid.size() - 1; i++) {
            Stack<ItemTile> stack1 = shelfGrid.get(i);
            Stack<ItemTile> stack2 = shelfGrid.get(i+1);
            for (int j = 0; j < stack1.size(); j++) {
                if(stack1.size()<=j || stack2.size()<=j)    continue;
                ItemTile tile1 = stack1.get(j);
                ItemTile tile2 = stack2.get(j);
                if (tile1.getColor() == tile2.getColor()) {
                    // Found two adjacent tiles with the same color
                    if (!groups.containsKey(tile1)) {
                        groups.put(tile1, new HashSet<>());
                    }
                    groups.get(tile1).add(tile2);
                }
            }
        }

        // Check if there are at least six distinct groups
        int groupCount = 0;
        for (Set<ItemTile> group : groups.values()) {
            if (group.size() >= 1) {
                groupCount++;
            }
            if (groupCount >= 6) {
                return true;
            }
        }
        return false;
    }


}
