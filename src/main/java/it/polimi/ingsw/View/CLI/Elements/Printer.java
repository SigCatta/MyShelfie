package it.polimi.ingsw.View.CLI.Elements;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.*;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;
import java.util.HashMap;

public class Printer implements VirtualModelObserver {

    static HashMap<Color, String> colorMap;
    private static Printer instance;

    private Printer() {
        BoardRepresentation.getInstance().registerObserver(this);
        ChatRepresentation.getInstance().registerObserver(this);
        CommonGoalsRepresentation.getInstance().registerObserver(this);
        PlayersRepresentation.getInstance().registerObserver(this);
        ShelvesRepresentation.getInstance().registerObserver(this);
    }

    public static Printer getInstance(){
        if (instance == null) instance =  new Printer();
        return instance;
    }

    /**
     * Changes the color map
     *
     * @param isColored a booleas indicating whether the CLI has to be colored or not
     */
    public static void enableCLIColors(boolean isColored) {
        colorMap = new HashMap<>();
        if (isColored) {
            // COLOR = ANSI_BACKGROUND_COLOR_ID + WHITE spaces + ANSI_COLOR_RESET
            colorMap.put(Color.GREEN, "\033[0;102m" + " " + "\u001B[0m");
            colorMap.put(Color.YELLOW, "\033[0;103m" + " " + "\u001B[0m");
            colorMap.put(Color.BLUE, "\033[0;104m" + " " + "\u001B[0m");
            colorMap.put(Color.PINK, "\033[0;105m" + " " + "\u001B[0m");
            colorMap.put(Color.LIGHTBLUE, "\033[0;106m" + " " + "\u001B[0m");
            colorMap.put(Color.WHITE, "\033[0;107m" + " " + "\u001B[0m");
        } else {
            colorMap.put(Color.GREEN, "G");
            colorMap.put(Color.YELLOW, "Y");
            colorMap.put(Color.BLUE, "B");
            colorMap.put(Color.PINK, "P");
            colorMap.put(Color.LIGHTBLUE, "L");
            colorMap.put(Color.WHITE, "W");
        }
        colorMap.put(Color.EMPTY, " ");
    }

    /**
     * @return a HashMap containing the Color enum as a key and the corresponding
     * string as the value (the string may contain ANSI background colors or not
     * depending on whether the user requested a colored CLI or not)
     */
    public static HashMap<Color, String> getColorMap() {
        if (colorMap == null) enableCLIColors(true);
        return colorMap;
    }


    /**
     * Prints the game's home screen, containing:<br>
     * - board<br>
     * - personal shelf<br>
     * - common goals<br>
     * - personal goal<br>
     * - chat (if available)<br>
     * - a list of the available commands<br>
     */
    @Override
    public synchronized void update() {

        ArrayList<String> output = new ArrayList<>();

        output = BoardView.getInstance().getPrint(output);
        output = ShelfView.getInstance().getPrint(output);
        output = CommonGoalView.getInstance().getPrint(output);
        output = PersonalGoalView.getInstance().getPrint(output);
        output = ChatView.getInstance().getPrint(output);
        output = addAvailableCommands(output);

        output.forEach(System.out::println);
    }

    /**
     * Uses ANSI escape code to move the cursore to the top of the screen,
     * makeing it look like the console has been cleared
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Adds a list of the available output to the given ArrayList,
     *
     * @param output the ArrayList where to add the command box
     */
    public static ArrayList<String> addAvailableCommands(ArrayList<String> output) {
        if (output.size() == 0 || !output.get(0).contains("COMMON GOAL")) {
            return getAvailableCommands();
        }

        int i = 0;
        for (; i < output.size(); i++) {
            if (output.get(i).contains("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘")) {
                i += 5;
                break;
            }
        }
        if (i == output.size()) i = 0; // the chat is not being shown yet ~ no messages sent

        ArrayList<String> commands = getAvailableCommands();

        for (String line : commands) {
            output.set(i, output.get(i++).concat(line));
        }

        return output;
    }

    /**
     * @return ArrayList containing all available commands
     */
    private static ArrayList<String> getAvailableCommands() {
        ArrayList<String> commands = new ArrayList<>();

        commands.add("          ┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
        commands.add("          │                                                 COMMANDS                                                 │");
        commands.add("          ├──────────────────────────────────────────────────────────────────────────────────────────────────────────┤");

        if (GameRepresentation.getInstance().getActivePlayerNickname().equals(SocketClient.getInstance().getNickname())) {
            commands.add("          │ - pickup -> to pickup between 1, 2 or 3 tiles from the board                                             │");
            commands.add("          │ - insert -> to insert the tiles you picked up in your personal shelf                                     │");
        }

        commands.add("          │ - chat -> to comunicate with all other players                                                           │");
        commands.add("          │ - pchat -> to comunicate privately with one player                                                       │");
        commands.add("          │ - disconnect -> to leave the game ☹                                                                      │");
        commands.add("          │ - refresh -> to print an updated version of the home screen                                              │");
        commands.add("          │ - colors -> to choose whether to use colors or letters to show item tiles                                │");
        commands.add("          │ - common_goals -> to checkout the description of the common goal cards for this game                     │");
        commands.add("          │ - personal_goal -> to checkout a brief explanation on how personal goal cards work                       │");
        commands.add("          │ - shelves -> to checkout other player's shelves                                                          │");
        commands.add("          └──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

        return commands;
    }
}
