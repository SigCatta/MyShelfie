package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Controller.Client.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;

import java.util.ArrayList;
import java.util.List;

public class CommonGoalsView implements ViewElement{
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        List<CommonGoalCard> commonGoals = CommonGoalsRepresentation.getInstance().getCommonGoalContainer().getCommonGoals();

        ArrayList<String> drawings = new ArrayList<>();

        for (CommonGoalCard c : commonGoals){
            drawings.addAll(new CommonGoalReader().getDrawing(c.getName()));
        }

        for (int i = 0; i <  output.size(); i++){
            output.set(i , output.get(i).concat(drawings.get(i)));
        }
        return output;
    }
}
