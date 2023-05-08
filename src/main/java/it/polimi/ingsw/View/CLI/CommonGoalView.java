package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;

import java.util.ArrayList;

public class CommonGoalView implements ViewElement {
    private final CommonGoalReader reader = new CommonGoalReader();
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        ArrayList<String> drawing = new ArrayList<>();
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();
        ArrayList<Integer> availablePoints = CommonGoalsRepresentation.getInstance().getAvailablePoints();
        int i = 1;
        for (String cardName : cardNames) {
            drawing.add("       COMMON GOAL #" + i);
            drawing.addAll(reader.getDrawing(cardName));
            drawing.add("     Available points: " + availablePoints.get(i++ - 1));
            drawing.add(new CommonGoalReader().getDescription(cardName));
            drawing.add("                             ");
        }

        for (int j = 0; j < Math.min(output.size(), drawing.size()); j++){
            output.set(j, output.get(j).concat(drawing.get(j)));
        }

        return output;
    }
}
