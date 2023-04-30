package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Controller.Client.VirtualModel.CommonGoalsRepresentation;

import java.util.ArrayList;

public class CommonGoalView {

    public ArrayList<String> printCommonGoal(ArrayList<String> output) {
        if (output.size() == 0) output = new ArrayList<>();

        ArrayList<String> commonGoalDrawing = CommonGoalsRepresentation.getInstance().printDrawings();

        for(int i = 0; i < commonGoalDrawing.size(); i++){
            output.set(i, output.get(i).concat(commonGoalDrawing.get(i)));
        }

        return output;
    }
}
