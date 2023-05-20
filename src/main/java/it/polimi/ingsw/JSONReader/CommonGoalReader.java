package it.polimi.ingsw.JSONReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class CommonGoalReader implements JSONFileReader {
    protected final JSONParser jsonParser;

    public CommonGoalReader() {
        this.jsonParser = new JSONParser();
    }

    /**
     * Reads the resources/data/common_cards folder to get the cli representation of any commonGoal card
     *
     * @param cardName the name of the card to be read
     * @return a String arrayList containing the drawing
     */
    public ArrayList<String> getDrawing(String cardName) {
        ArrayList<String> drawing = new ArrayList<>();

        JSONArray rows = (JSONArray) Objects.requireNonNull(getCardObject(cardName)).get("DRAWING");

        for (Object line : rows) {
            drawing.add((String) line);
        }

        return drawing;
    }

    /**
     * Finds a common_goals's json file given its name
     *
     * @param cardName the common goal card's name
     * @return the common goal's JSONOBJECT
     */
    private JSONObject getCardObject(String cardName) {
        try {
            InputStream inputSream = this.getClass().getClassLoader().getResourceAsStream("data/common_cards/" + cardName + ".json");
            assert inputSream != null;
            InputStreamReader reader = new InputStreamReader(inputSream);
            return (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds a card's description given its name
     *
     * @param cardName the common goal card's name
     * @return a String containing the card's description
     */
    public String getDescription(String cardName) {
        return (String) Objects.requireNonNull(getCardObject(cardName)).get("DESCRIPTION");
    }
}
