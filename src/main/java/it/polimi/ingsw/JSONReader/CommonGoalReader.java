package it.polimi.ingsw.JSONReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CommonGoalReader implements JSONFileReader {
    protected final JSONParser jsonParser;
    private final String PATH;

    public CommonGoalReader() {
        this.jsonParser = new JSONParser();
        PATH = "src/data/common_cards/";
    }

    /**
     * Reads the src/data/common_cards folder to get the cli representation of any commonGoal card
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


    private JSONObject getCardObject(String cardName) {
        try {
            FileReader reader = new FileReader(PATH + cardName + ".json");
            return (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException ignored) {}
        return null; // should never reach
    }

    public String getDescription(String cardName) {
        return (String) Objects.requireNonNull(getCardObject(cardName)).get("DESCRIPTION");
    }
}
