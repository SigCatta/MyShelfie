package it.polimi.ingsw.model.cards.personalGoals;

import it.polimi.ingsw.exceptions.NullPlayersException;
import it.polimi.ingsw.exceptions.TooManyPlayersException;
import it.polimi.ingsw.model.player.Player;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class tests {@link PersonalCardDealer}
 */

public class PersonalCardDealerTest {

    private static int numOfCards;

    @BeforeAll
    public static void init() {
        File personalCardsDirectory = new File("src/data/personal_cards");
        numOfCards = Objects.requireNonNull(personalCardsDirectory.list()).length - 1; // not counting points.json
    }

    @Test
    public void tooManyTest() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numOfCards + 1; i++) {
            players.add(new Player("player"));
        }

        assertThrows(TooManyPlayersException.class, () -> PersonalCardDealer.getCards(players));
    }

    @Test
    public void allCardsTest() throws IOException, ParseException, TooManyPlayersException, NullPlayersException {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numOfCards; i++) {
            players.add(new Player("player"));
        }

        PersonalCardDealer.getCards(players);

        HashSet<PersonalGoal> personalGoals = new HashSet<>();
        for (Player player : players) {
            personalGoals.add(player.getPersonalGoal());
        }

        // if the cards are all different, personalGoals will contain exactly numOfCards cards
        assertEquals(numOfCards, personalGoals.size());
    }
}
