package it.polimi.ingsw.JSONReader;

import it.polimi.ingsw.Enum.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * Reads personal goal cards data from json files
 */
public class PersonalGoalReader implements JSONFileReader {
    private final JSONParser jsonParser;

    /**
     * Contracts a new JSON file reader
     */
    public PersonalGoalReader() {
        this.jsonParser = new JSONParser();
    }

    /**
     * Reads a JSON file from the resources/data/personal_cards/ folder given its name,
     * if the file exists in the directory the method returns a hashmap containing
     * the color-coordinates pair for each objective
     *
     * @param fileName the name of the JSON file that need to be read
     * @return a map containing the coordinates of each objective, by colour
     * @throws IOException    if the file does not exist or, for any other reason cannot be opened for reading
     * @throws ParseException if an incorrect JSON is being parsed
     */
    public HashMap<Color, Point> getPersonalGoalsData(String fileName) throws IOException, ParseException {
        HashMap<Color, Point> map = new HashMap<>();

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/personal_cards/" + fileName);
        assert inputStream != null;
        InputStreamReader reader = new InputStreamReader(inputStream);
        JSONObject JSONObj = (JSONObject) jsonParser.parse(reader);

        for (Color color : Color.values()) {
            JSONArray arr = (JSONArray) JSONObj.get(color.name());
            Object x = arr.get(0);
            Object y = arr.get(1);
            Point point = new Point(Integer.parseInt(x.toString()), Integer.parseInt(y.toString()));
            map.put(color, point);
        }

        return map;
    }

    /**
     * Reads the resources/data/personal_cards/points.json file to get how many points the player gets for completing
     * each objective. The amount of points received is based on the number of objectives already completed
     * (this data is set while writing the points.json file)
     *
     * @return a stack containing the points that is player is going to get for completing each objective
     * @throws IOException    if the file does not exist or, for any other reason cannot be opened for reading
     * @throws ParseException if an incorrect JSON is being parsed
     */
    public Stack<Integer> getPointStack() throws IOException, ParseException {
        Stack<Integer> pointStack = new Stack<>();

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/personal_cards/points.json");
        assert inputStream != null;
        InputStreamReader reader = new InputStreamReader(inputStream);
        JSONObject JSONObj = (JSONObject) jsonParser.parse(reader);

        JSONArray points = (JSONArray) JSONObj.get("POINTS");
        for (int i = points.size() - 1; i >= 0; i--) {
            pointStack.push(Integer.valueOf(points.get(i).toString()));
        }

        return pointStack;
    }

}
