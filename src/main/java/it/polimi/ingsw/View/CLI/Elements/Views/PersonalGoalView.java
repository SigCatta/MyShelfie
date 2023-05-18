package it.polimi.ingsw.View.CLI.Elements.Views;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonalGoalView extends ViewElement {

    private static PersonalGoalView instance;

    private PersonalGoalView() {
    }

    public static PersonalGoalView getInstance() {
        if (instance == null) instance = new PersonalGoalView();
        return instance;
    }

    /**
     * prints the player's personal goal card
     *
     * @param output an ArrayList where to add the drawing
     * @return the give ArrayList with the player's personal goal card drawing
     */
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        String nickname = SocketClient.getInstance().getNickname();
        HashMap<Color, Point> personalGoal = PlayersRepresentation.getInstance().getPlayerInfoByNickname(nickname).getPersonalGoal();
        Color[][] goal = new Color[6][5];
        HashMap<Color, String> colorMap = Printer.getColorMap();

        for (Color color : Color.values()) {
            Point p = personalGoal.get(color);
            goal[p.y - 1][p.x - 1] = color;
        }

        if (output.size() == 0) {
            output = new ArrayList<>();
            output.add("       PERSONAL GOAL");
            output.add("      ┌──┬──┬──┬──┬──┐");
            for (Color[] row : goal) {
                StringBuilder string = new StringBuilder("      │");
                for (Color color : row) {
                    string
                            .append(colorMap.getOrDefault(color, " ").repeat(2))
                            .append("│");
                }
                output.add(string.toString());
                output.add("      ├──┼──┼──┼──┼──┤");
            }
            output.set(output.size() - 1, output.get(output.size() - 1).replace("├──┼──┼──┼──┼──┤", "└──┴──┴──┴──┴──┘"));
        } else if (output.get(0).contains("COMMON GOAL #")) {
            int i = 36; // starts printing after common goals
            output.set(i, output.get(i++).concat("       PERSONAL GOAL"));
            output.set(i, output.get(i++).concat("      ┌──┬──┬──┬──┬──┐"));
            for (Color[] row : goal) {
                StringBuilder string = new StringBuilder("      │");
                for (Color color : row) {
                    string
                            .append(colorMap.getOrDefault(color, " ").repeat(2))
                            .append("│");
                }
                output.set(i, output.get(i++).concat(string.toString()));
                output.set(i, output.get(i++).concat("      ├──┼──┼──┼──┼──┤"));
            }
            i--;
            output.set(i, output.get(i).replace("├──┼──┼──┼──┼──┤", "└──┴──┴──┴──┴──┘"));
        }
        return output;
    }


    /**
     * Adds a brief description about personal goal cards next to the drawing of the cards
     *
     * @param output a drwoing of a personal card
     * @return a drowing of the personal card (argument) next to a brief description
     */
    public ArrayList<String> addDescription(ArrayList<String> output) {
        if (output.size() == 0) return null;
        output.set(6, output.get(6).concat("     The personal goal card grants points if you match the"));
        output.set(7, output.get(7).concat("     highlighted spaces with the corresponding item tiles."));
        output.set(8, output.get(8).concat("     Points are awarded in the following order: 1, 2, 4, 6, 9 ,12"));
        return output;
    }
}
