package it.polimi.ingsw.model.cards.commonGoals;

import exceptions.TooManyCardsRequestedException;
import it.polimi.ingsw.model.cards.commonGoals.CommonCardDealer;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalCard;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.List;

import static org.junit.Assert.*;

public class CommonCardDealerTest {
    @Test
    public void getCardsTest() {
        List<CommonGoalStrategy> commonGoalStrategyList = CommonCardDealer.getCardStrategies();
        assertNotNull(commonGoalStrategyList);
        assertEquals(12, commonGoalStrategyList.size());
    }

    @Test
    public void pickCardsTest() throws TooManyCardsRequestedException {
        List<CommonGoalStrategy> commonGoalStrategyList = CommonCardDealer.pickCardStrategies(2);
        assertNotNull(commonGoalStrategyList);
        assertEquals(2, commonGoalStrategyList.size());
    }

    @Disabled
    @Test
    public void overflow1() {
        List<CommonGoalStrategy> commonGoalStrategyList = CommonCardDealer.pickCardStrategies(13);

        assertEquals(commonGoalStrategyList.size(), 12);
    }

    @Test
    public void overflow2() throws TooManyCardsRequestedException {
        List<CommonGoalStrategy> commonGoalStrategyList = CommonCardDealer.pickCardStrategies(-1);
        assertNotNull(commonGoalStrategyList);
        assertEquals(0, commonGoalStrategyList.size());
    }

    @Test
    public void testListHasNoDuplicates() throws TooManyCardsRequestedException {
        List<CommonGoalStrategy> commonGoalStrategyList = CommonCardDealer.pickCardStrategies(12);

        assertNotNull(commonGoalStrategyList);
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                if(i != j){
                    assertNotEquals(commonGoalStrategyList.get(i), commonGoalStrategyList.get(j));
                }
            }
        }
    }


    @Test
    public void getCards1() throws TooManyCardsRequestedException {
        List<CommonGoalCard> commonGoalCards = CommonCardDealer.pickCommonGoalCards(2);

        assertNotNull(commonGoalCards);
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                if(i != j){
                    assertNotEquals(commonGoalCards.get(i), commonGoalCards.get(j));
                }
            }
        }
    }

    @Test
    public void getCards2() throws TooManyCardsRequestedException {
        List<CommonGoalCard> commonGoalCards = CommonCardDealer.pickCommonGoalCards(6);

        assertNotNull(commonGoalCards);
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(i != j){
                    assertNotEquals(commonGoalCards.get(i), commonGoalCards.get(j));
                }
            }
        }
    }

}
