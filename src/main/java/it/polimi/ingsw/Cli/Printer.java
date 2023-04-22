package it.polimi.ingsw.Cli;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

public class Printer {
    public static final String ANSI_BLACK_BACKGROUND = "\033[0;100m";
    public static final String ANSI_GREEN_BACKGROUND = "\033[0;102m";
    public static final String ANSI_YELLOW_BACKGROUND = "\033[0;103m";
    public static final String ANSI_BLUE_BACKGROUND = "\033[0;104m";
    public static final String ANSI_PINK_BACKGROUND = "\033[0;105m";
    public static final String ANSI_CYAN_BACKGROUND = "\033[0;106m";
    public static final String ANSI_WHITE_BACKGROUND = "\033[0;107m";
    public static final String ANSI_RED_BACKGROUND = "\033[0;101m";

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void printMyShelfie() {
        System.out.println( "M   M    Y     Y      SSSSS    H   H    EEEE    L         FFFFFF   III  EEEE\n" +
                            "MM MM     Y   Y      S         H   H    E       L         F         I   E   \n" +
                            "M M M      Y Y        SSSSS    HHHHH    EEEE    L         FFFFF     I   EEEE\n" +
                            "M   M       Y              S   H   H    E       L         F         I   E   \n" +
                            "M   M       Y         SSSSS    H   H    EEEE    LLLLLL    F        III  EEEE\n");
    }

    public static void printColorMatrix(ItemTile[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(ANSI_RED + "|" + ANSI_RESET);
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == null) {
                    System.out.print(ANSI_BLACK_BACKGROUND + "    " + ANSI_RESET +
                            ANSI_RED + "|" + ANSI_RESET );
                } else {
                    switch (matrix[i][j].getColor()) {
                        case GREEN:
                            System.out.print(ANSI_GREEN_BACKGROUND + "    " + ANSI_RESET +
                                    ANSI_RED + "|"  + ANSI_RESET );
                            break;
                        case YELLOW:
                            System.out.print(ANSI_YELLOW_BACKGROUND + "    " + ANSI_RESET +
                                    ANSI_RED + "|"  + ANSI_RESET );
                            break;
                        case BLUE:
                            System.out.print(ANSI_BLUE_BACKGROUND + "    " + ANSI_RESET +
                                    ANSI_RED + "|"  + ANSI_RESET );
                            break;
                        case PINK:
                            System.out.print(ANSI_PINK_BACKGROUND + "    " + ANSI_RESET +
                                    ANSI_RED + "|"  + ANSI_RESET );
                            break;
                        case LIGHTBLUE:
                            System.out.print(ANSI_CYAN_BACKGROUND + "    " + ANSI_RESET +
                                    ANSI_RED + "|"  + ANSI_RESET );
                            break;
                        case WHITE:
                            System.out.print(ANSI_WHITE_BACKGROUND + "    " + ANSI_RESET +
                                    ANSI_RED + "|"  + ANSI_RESET );
                            break;
                        default:
                            System.out.print(ANSI_BLACK_BACKGROUND + "    " + ANSI_RESET +
                                    ANSI_RED + "|"  + ANSI_RESET );
                            break;
                    }
                }
            }
            System.out.println();
            System.out.println();
        }
    }
}
