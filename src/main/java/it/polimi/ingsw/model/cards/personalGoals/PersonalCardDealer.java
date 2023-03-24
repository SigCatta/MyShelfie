package it.polimi.ingsw.model.cards.personalGoals;

import it.polimi.ingsw.ReadFromJSONFile;
import it.polimi.ingsw.model.player.Player;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PersonalCardDealer {

    private static final File personalCardsDirectory = new File("src/data/personal_cards");

    /**
     * Assigns <strong>different</strong> personal goals to a list of players,
     * the method is intended to be executed at the beginning of each game
     *
     * @param players a list of the players whom will be assigned a personal goal card
     * @throws IOException    if the file does not exist or, for any other reason cannot be opened for reading
     * @throws ParseException if an incorrect JSON is being parsed
     * @author Luca Cattani
     */
    public static void getCards(List<Player> players) throws IOException, ParseException {
        int numOfFiles = Objects.requireNonNull(personalCardsDirectory.list()).length - 1; // not counting points.json
        if (numOfFiles < players.size()) {
            //TODO throw exception
        }

        HashSet<String> cards = new HashSet<>();
        do {
            cards.add(String.valueOf(Math.random() * numOfFiles));
        } while (cards.size() < numOfFiles);

        ReadFromJSONFile JSONreader = new ReadFromJSONFile();
        Stack<Integer> points = JSONreader.getPointStack();
        int i = 0;
        for (String card : cards) {
            Player player = players.get(i);
            PersonalGoal personalGoal = new PersonalGoal(player, JSONreader.getItemTiles(card), points);
            players.get(i).setPersonalGoal(personalGoal);
            i++;
        }
    }
}
