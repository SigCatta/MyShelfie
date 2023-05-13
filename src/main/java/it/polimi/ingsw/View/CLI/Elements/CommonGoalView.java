package it.polimi.ingsw.View.CLI.Elements;

import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommonGoalView implements ViewElement {
    private final CommonGoalReader reader = new CommonGoalReader();

    /**
     * Adds common goal drawings on top of the given ArrayList
     *
     * @param output the ArrayList where to add common goal drawings
     * @return the original ArrayList with common goal drawings
     */
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        ArrayList<String> drawing = new ArrayList<>();
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();
        ArrayList<Integer> availablePoints = CommonGoalsRepresentation.getInstance().getAvailablePoints();
        int i = 1;
        for (String cardName : cardNames) {
            drawing.add("       COMMON GOAL #" + i + "       " + (i > 9 ? "" : " "));
            drawing.addAll(reader.getDrawing(cardName));
            drawing.add("     Available points: " + availablePoints.get(i++ - 1) + "     ");
            drawing.add("                             ");
        }
        if (output.size() == 0) return drawing;

        for (int j = 0; j < Math.min(output.size(), drawing.size()); j++) {
            output.set(j, output.get(j).concat(drawing.get(j)));
        }

        return output;
    }

    /**
     * Adds description the common goals drawings
     *
     * @param output the ArrayList where to add the descriptions of the common goals
     * @return an ArrayList containing the common goals next to the respective description
     */
    public ArrayList<String> addDescription(ArrayList<String> output) {
        if (output.size() == 0) return null;
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();

        int margin = 50; // all descriptions lines will be around this length

        int line = 0;
        for (int i = 0; i < cardNames.size(); i++) {
            String description = reader.getDescription(cardNames.get(i));

            ArrayList<String> dLines = applyMargin(margin, description);
            dLines = dLines.stream().map("     "::concat).collect(Collectors.toCollection(ArrayList::new)); // adds padding...

            for (; line < output.size(); line++) {
                if (output.get(line).contains("COMMON GOAL #" + (i + 1))) {
                    line += 6; // offset from the string indicating the common goal number
                    for (String dLine : dLines) {
                        output.set(line, output.get(line++).concat(dLine));
                    }
                    break;
                }
            }
        }
        return output;
    }


    /**
     * Splits a give string in substrings of length margin (not cutting words)
     *
     * @param margin all the lines will be cut at the end of the word that contains the margin'th char
     * @param s      the string to split
     * @return an ArrayList containing the strings in which that starting string has been split
     */
    private ArrayList<String> applyMargin(int margin, String s) {
        ArrayList<String> output = new ArrayList<>();
        for (int j = 0; ; j++) {
            int startIndex = j * margin;
            if (s.charAt(startIndex) != ' ' && j != 0) {
                startIndex = s.indexOf(" ", startIndex);
            }
            if ((j + 1) * margin > s.length()) {
                output.add(s.substring(startIndex).trim());
                break;
            }
            int endOfSubstring = s.indexOf(" ", (j + 1) * margin);

            if (endOfSubstring == -1) {
                output.add(s.substring(startIndex));
                break;
            } else output.add(s.substring(startIndex, endOfSubstring).trim());
        }
        return output;
    }
}
