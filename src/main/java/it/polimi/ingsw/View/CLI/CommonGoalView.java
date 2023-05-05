package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Controller.Client.VirtualModel.CommonGoalsRepresentation;

import java.util.ArrayList;
import java.util.Map;

public class CommonGoalView implements ViewElement {
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        if (output.size() == 0) output = new ArrayList<>();

        Map<String, Integer> commonGoalDrawing = CommonGoalsRepresentation.getInstance().getNameToPoints();
//
//        for (int i = 0; i < commonGoalDrawing.size(); i++) {
//            output.set(i, output.get(i).concat(commonGoalDrawing.get(i)));
//        }

        return output;
    }

    private void printDrawings() {
        //    public ArrayList<String> printDrawings() {
//        ArrayLisaddet<String> drawing = new ArrayList<>();
//        for (CommonGoalMessage cg : commonGoalMessages) {
//            drawing.add("        COMMON GOAL #" + cg.getCommonGoalNumber());
//            drawing.addAll(cg.getDrawing());
//            drawing.add("      Available points: " + cg.getAvailablePoints());
//            drawing.add("                             ");
//
//        }
//        return drawing;

//    }
    }

}
