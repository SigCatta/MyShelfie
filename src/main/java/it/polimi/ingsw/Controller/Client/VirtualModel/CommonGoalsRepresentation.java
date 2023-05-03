package it.polimi.ingsw.Controller.Client.VirtualModel;

import it.polimi.ingsw.View.VirtualView.Messages.CommonGoalMessage;

import java.util.ArrayList;

public class CommonGoalsRepresentation implements VirtualModelSubject {
    private ArrayList<CommonGoalMessage> commonGoalMessages;

    private static CommonGoalsRepresentation instance;

    public ArrayList<String> printDrawings() {
        ArrayLisaddet<String> drawing = new ArrayList<>();
        for (CommonGoalMessage cg : commonGoalMessages) {
            drawing.add("        COMMON GOAL #" + cg.getCommonGoalNumber());
            drawing.addAll(cg.getDrawing());
            drawing.add("      Available points: " + cg.getAvailablePoints());
            drawing.add("                             ");

        }
        return drawing;
    }

    public void changeCommonGoal(CommonGoalMessage commonGoalMessage){
        for (int i = 0; i < commonGoalMessages.size(); i++){
            if (commonGoalMessages.get(i).getCommonGoalNumber() == commonGoalMessage.getCommonGoalNumber()){
                commonGoalMessages.set(i, commonGoalMessage);
                break;
            }
        }
    }

    private CommonGoalsRepresentation() {
        commonGoalMessages = new ArrayList<>();
    }

    public static CommonGoalsRepresentation getInstance() {
        if (instance == null) instance = new CommonGoalsRepresentation();
        return instance;
    }

    @Override
    public void registerObserver(VirtualModelObserver observer) {

    }

    @Override
    public void removeObserver(VirtualModelObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }

}
