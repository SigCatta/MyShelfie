package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.SocketClient;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonalGoalView implements ViewElement {
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        String nickname = SocketClient.getInstance().getNickname();
        HashMap<Color, Point> personalGoal = PlayersRepresentation.getInstance().getPlayerByNickname(nickname).getPersonalGoal();
        Color[][] goal = new Color[6][5];
        Printer.enableCLIColors(true); //TODO delete after testing
        HashMap<Color, String> colorMap = Printer.getColorMap();

        for (Color color : Color.values()) {
            if (color == Color.EMPTY) continue;
            Point p = personalGoal.get(color);
            goal[p.y - 1][p.x - 1] = color;
        }

        if (output.size() == 0) {
            output = new ArrayList<>();
            //TODO need a way to use output.add() if a new ArrayList is declared, output.set() otherwise
            output.add(" PERSONAL GOAL");
            output.add("┌──┬──┬──┬──┬──┐");
            for (Color[] row : goal) {
                StringBuilder string = new StringBuilder("│");
                for (Color color : row) {
                    string
                            .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
                            .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
                            .append("│");
                }
                output.add(string.toString());
                output.add("├──┼──┼──┼──┼──┤");
            }
            output.set(output.size() - 1, output.get(output.size() - 1).replace("├──┼──┼──┼──┼──┤", "└──┴──┴──┴──┴──┘"));
        } else if (output.get(0).contains("COMMON GOAL #")) {
            int i = 36; // starts printing after common goals
            //TODO need a way to use output.add() if a new ArrayList is declared, output.set() otherwise
            output.set(i, output.get(i++).concat(" PERSONAL GOAL"));
            output.set(i, output.get(i++).concat("┌──┬──┬──┬──┬──┐"));
            for (Color[] row : goal) {
                StringBuilder string = new StringBuilder("│");
                for (Color color : row) {
                    string
                            .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
                            .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
                            .append("│");
                }
                output.set(i, output.get(i++).concat(string.toString()));
                output.set(i, output.get(i++).concat("├──┼──┼──┼──┼──┤"));
            }
            i--;
            output.set(i, output.get(i).replace("├──┼──┼──┼──┼──┤", "└──┴──┴──┴──┴──┘"));
        }
        return output;
    }
}
