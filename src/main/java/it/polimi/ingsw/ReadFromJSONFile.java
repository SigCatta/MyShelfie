package it.polimi.ingsw;

import it.polimi.ingsw.model.tiles.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Used for anything reguarding reading data from a json file
 */

public class ReadFromJSONFile {
    private final JSONParser jsonParser;
    private final String PATH = "src/data/";

    /**
     * Contructs a new JSON file reader
     */
    public ReadFromJSONFile() {
        this.jsonParser = new JSONParser();
    }

    /**
     * Reads a JSON file from the src/data/personal_cards/ folder given its name,
     * if the file exists in the directory the mothod returns a hashmap containing
     * the color-coordinates pair for each objective
     *
     * @param fileName the name of the JSON file that need to be read
     * @return a map containing the coordinates of each objective, by colour
     * @throws IOException    if the file does not exist or, for any other reason cannot be opened for reading
     * @throws ParseException if an incorrect JSON is being parsed
     */
    public HashMap<Color, Point> getPersonalGoalsData(String fileName) throws IOException, ParseException {
        HashMap<Color, Point> map = new HashMap<>();
        FileReader fileReader = new FileReader(PATH + "personal_cards/" + fileName);
        JSONObject JSONobj = (JSONObject) jsonParser.parse(fileReader);

        for (Color color : Color.values()) {
            JSONArray arr = (JSONArray) JSONobj.get(color.name());
            Object x = arr.get(0);
            Object y = arr.get(1);
            Point point = new Point(Integer.parseInt(x.toString()), Integer.parseInt(y.toString()));
            map.put(color, point);
        }

        return map;
    }

    /**
     * Reads the src/data/personal_cards/points.json file to get how many points the player gets for completing
     * each objective. The ammount of points received is based on the number of objectives already completed
     * (this data is set while writing the points.json file)
     *
     * @return a stack containing the points that is player is going to get for completing each objective
     * @throws IOException    if the file does not exist or, for any other reason cannot be opened for reading
     * @throws ParseException if an incorrect JSON is being parsed
     */
    public Stack<Integer> getPointStack() throws IOException, ParseException {
        Stack<Integer> pointStack = new Stack<>();
        FileReader fileReader = new FileReader(PATH + "personal_cards/points.json");
        JSONObject JSONobj = (JSONObject) jsonParser.parse(fileReader);

        JSONArray points = (JSONArray) JSONobj.get("POINTS");
        for (int i = points.size() - 1; i >= 0; i--) {
            pointStack.push(Integer.valueOf(points.get(i).toString()));
        }

        return pointStack;
    }

    /**
     * Reads the src/data/boards folder to get which spots on the board a playable, reads the files based on
     * how many players are playing, allowing more tile for bigger games
     *
     * @param numOfPlayers indicats how many players will be playing
     * @param boardSize    indicats how big the board is going to be
     * @return a boolean matrix containg true if the square is playable
     * @throws IOException    if the file does not exist or, for any other reason cannot be opened for reading
     * @throws ParseException if an incorrect JSON is being parsed
     */
    public boolean[][] getPlayableSquares(int numOfPlayers, int boardSize) throws IOException, ParseException {
        boolean[][] board = new boolean[boardSize][boardSize];

        for (int i = 2; i <= numOfPlayers; i++) {
            String filename = i + ".json";
            FileReader fileReader = new FileReader(PATH + "boards/" + filename);
            JSONObject JSONobj = (JSONObject) jsonParser.parse(fileReader);
            int numOfPoints = (int) JSONobj.get("numOfPoints");
            for (int j = 0; j < numOfPoints; j++) {

                JSONArray arr = (JSONArray) JSONobj.get("Point" + j);
                Object x = arr.get(0);
                Object y = arr.get(1);
                board[Integer.parseInt(x.toString())][Integer.parseInt(y.toString())] = true;
            }
        }
        return board;
    }

}
