package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.View.CLI.CommonGoalView;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualView.Messages.CommonGoalMTC;
import it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy.TwoColSixDiffCGS;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoColSixDiffCGSTest {
    @Test
    public void isGoalAchievedTest() {
        TwoColSixDiffCGS cg = new TwoColSixDiffCGS();
        ItemTile[][] matrix1 = {
                {new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.LIGHTBLUE), new ItemTile(Color.WHITE), new ItemTile(Color.YELLOW), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
        };
        Shelf shelf = new Shelf(matrix1);
        assertTrue(cg.isGoalAchieved(shelf));

        ItemTile[][] matrix2 = {
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.PINK), new ItemTile(Color.PINK), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
                {new ItemTile(Color.GREEN), new ItemTile(Color.GREEN), new ItemTile(Color.BLUE), new ItemTile(Color.WHITE), new ItemTile(Color.WHITE), new ItemTile(Color.GREEN)},
        };
        shelf = new Shelf(matrix2);
        assertFalse(cg.isGoalAchieved(shelf));
    }

    @Test
    public void getDrawingForCLITest(){
        new TwoColSixDiffCGS().getDrawingForCLI().forEach(System.out::println);
    }
    @Test
    public void getDescriptionTest(){
        System.out.println(new TwoColSixDiffCGS().getDescription());
    }

    @Test
    public void ViewTest(){
        ArrayList<CommonGoalCard> deck = new ArrayList<>();
        deck.add(new CommonGoalCard(new TwoColSixDiffCGS()));
        CommonGoalsRepresentation.getInstance().updateCommonGoal(new CommonGoalMTC(deck));
        CommonGoalView cgv = new CommonGoalView();
        cgv.addDescription(cgv.getPrint(new ArrayList<>())).forEach(System.out::println);
    }
}
