package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualView.Messages.CommonGoalMTC;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CommonGoalViewTest {
    @Test
    public void getPrintTest1(){
        ArrayList<CommonGoalCard> deck = new ArrayList<>();
        deck.add(new CommonGoalCard(new EightTilesSameColorCGS()));
        deck.add(new CommonGoalCard(new FiveTilesDiagonalCGS()));
        deck.add(new CommonGoalCard(new FourAnglesCGS()));
        deck.add(new CommonGoalCard(new FourGroupsOfFourCGS()));
        deck.add(new CommonGoalCard(new FourRowsOfFiveCGS()));
        deck.add(new CommonGoalCard(new ScaleCGS()));
        deck.add(new CommonGoalCard(new SixGroupsOfTwoCGS()));
        deck.add(new CommonGoalCard(new ThreeMaxThreeDiffCGS()));
        deck.add(new CommonGoalCard(new TwoColSixDiffCGS()));
        deck.add(new CommonGoalCard(new TwoRowsWithFiveDiffCGS()));
        deck.add(new CommonGoalCard(new TwoSquaresCGS()));
        deck.add(new CommonGoalCard(new TwoSquaresCGS()));

        CommonGoalsRepresentation.getInstance().updateCommonGoal(new CommonGoalMTC(deck));
        CommonGoalView cgv = new CommonGoalView();
        ArrayList<String> output = cgv.getPrint(new ArrayList<>());
        output = cgv.addDescription(output);

        output.forEach(System.out::println);
    }
}
