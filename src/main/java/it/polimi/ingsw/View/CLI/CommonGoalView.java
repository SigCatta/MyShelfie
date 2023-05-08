package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.JSONReader.CommonGoalReader;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommonGoalView implements ViewElement {
    private final CommonGoalReader reader = new CommonGoalReader();

    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        ArrayList<String> drawing = new ArrayList<>();
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();
        ArrayList<Integer> availablePoints = CommonGoalsRepresentation.getInstance().getAvailablePoints();
        int i = 1;
        for (String cardName : cardNames) {
            drawing.add("       COMMON GOAL #" + i);
            drawing.addAll(reader.getDrawing(cardName));
            drawing.add("     Available points: " + availablePoints.get(i++ - 1));
            drawing.add("                             ");
        }
        if (output.size() == 0) return drawing;

        for (int j = 0; j < Math.min(output.size(), drawing.size()); j++) {
            output.set(j, output.get(j).concat(drawing.get(j)));
        }

        return output;
    }

    public ArrayList<String> addDescription(ArrayList<String> output) {
        if (output.size() == 0) return null;
        ArrayList<String> cardNames = CommonGoalsRepresentation.getInstance().getCardNames();

        int referenceLength = 50; // all descriptions lines will be around this length

        for (int i = 0; i < cardNames.size(); i++) {
            String description = reader.getDescription(cardNames.get(i));
            ArrayList<String> dLines = new ArrayList<>();

            for (int j = 0; ; j++) { // splits the description in more strings of similar length, saves the substrings in dLines
                int startIndex = j * referenceLength;
                if (description.charAt(startIndex) != ' ' && j != 0) {
                    startIndex = description.indexOf(" ", startIndex);
                }
                if ((j + 1) * referenceLength > description.length()) {
                    dLines.add(description.substring(startIndex).trim());
                    break;
                }
                int endOfSubstring = description.indexOf(" ", (j + 1) * referenceLength);

                if (endOfSubstring == - 1) {
                    dLines.add(description.substring(startIndex));
                    break;
                }
                else dLines.add(description.substring(startIndex, endOfSubstring).trim());
            }

            dLines = dLines.stream().map("     "::concat).collect(Collectors.toCollection(ArrayList::new)); // adding padding...

            int line = 0;
            for (String s : output) {
                line++;
                if (s.contains("COMMON GOAL #" + (i + 1))) {
                    line += 5;
                    for (String dLine : dLines) {
                        output.set(line, output.get(line++).concat(dLine));
                    }
                }
            }
        }
        return output;
    }
}
