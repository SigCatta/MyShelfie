package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.View.CLI.Elements.Views.CommonGoalView;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualView.Messages.CommonGoalMTC;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CommonGoalViewTest {
    @Test
    public void getPrintTest1() {
        ArrayList<CommonGoalCard> deck = new ArrayList<>();
        deck.add(new CommonGoalCard(new EightTilesSameColorCGS(), 4));
        deck.add(new CommonGoalCard(new FiveTilesDiagonalCGS(), 4));
        deck.add(new CommonGoalCard(new FourAnglesCGS(), 4));
        deck.add(new CommonGoalCard(new FourGroupsOfFourCGS(), 4));
        deck.add(new CommonGoalCard(new FourRowsOfFiveCGS(), 4));
        deck.add(new CommonGoalCard(new ScaleCGS(), 4));
        deck.add(new CommonGoalCard(new SixGroupsOfTwoCGS(), 4));
        deck.add(new CommonGoalCard(new ThreeMaxThreeDiffCGS(), 4));
        deck.add(new CommonGoalCard(new TwoColSixDiffCGS(), 4));
        deck.add(new CommonGoalCard(new TwoRowsWithFiveDiffCGS(), 4));
        deck.add(new CommonGoalCard(new TwoSquaresCGS(), 4));
        deck.add(new CommonGoalCard(new TwoSquaresCGS(), 4));

        CommonGoalsRepresentation.getInstance().updateCommonGoal(new CommonGoalMTC(deck));
        CommonGoalView cgv = CommonGoalView.getInstance();
        ArrayList<String> output = cgv.getPrint(new ArrayList<>());
        output = cgv.addDescription(output);

        output.forEach(System.out::println);
    }
}
