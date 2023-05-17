package it.polimi.ingsw.View.CLI.Elements;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;


/**
 * A class that prints the handles the endgame procedure,
 * telling the players if they won or not and closing the game
 */
public class GameStopper implements VirtualModelObserver {
    private static GameStopper instance;

    private GameStopper() {
        GameRepresentation.getInstance().registerObserver(this);
    }

    public static GameStopper getIntance() {
        if (instance == null) instance = new GameStopper();
        return instance;
    }

    /**
     * If the game goes into the end state, prints an ascii art indicating whether
     * the player lost or not and prints the leaderboard
     */
    @Override
    public void update() {
        if (GameRepresentation.getInstance().getGameState() != GameState.END) return;

        Printer.clearConsole();
        ArrayList<PlayerMTC> players = PlayersRepresentation.getInstance().getAllPlayerMTC();

        players.sort((a, b) -> b.getScore() - a.getScore());

        if (players.get(0).getNickname().equals(SocketClient.getInstance().getNickname())) printWinnerScreen();
        else printLoserScreen();

        printLeaderBoard(players);

        System.exit(0);
    }

    /**
     * prints an ascii art indicating that the player won
     */

    private void printWinnerScreen() {

        System.out.println("                                                                                                                                                       \n" +
                "YYYYYYY       YYYYYYY     OOOOOOOOO     UUUUUUUU     UUUUUUUU     WWWWWWWW                           WWWWWWWW     OOOOOOOOO     NNNNNNNN        NNNNNNNN      !!! \n" +
                "Y:::::Y       Y:::::Y   OO:::::::::OO   U::::::U     U::::::U     W::::::W                           W::::::W   OO:::::::::OO   N:::::::N       N::::::N     !!:!!\n" +
                "Y:::::Y       Y:::::Y OO:::::::::::::OO U::::::U     U::::::U     W::::::W                           W::::::W OO:::::::::::::OO N::::::::N      N::::::N     !:::!\n" +
                "Y::::::Y     Y::::::YO:::::::OOO:::::::OUU:::::U     U:::::UU     W::::::W                           W::::::WO:::::::OOO:::::::ON:::::::::N     N::::::N     !:::!\n" +
                "YYY:::::Y   Y:::::YYYO::::::O   O::::::O U:::::U     U:::::U       W:::::W           WWWWW           W:::::W O::::::O   O::::::ON::::::::::N    N::::::N     !:::!\n" +
                "   Y:::::Y Y:::::Y   O:::::O     O:::::O U:::::D     D:::::U        W:::::W         W:::::W         W:::::W  O:::::O     O:::::ON:::::::::::N   N::::::N     !:::!\n" +
                "    Y:::::Y:::::Y    O:::::O     O:::::O U:::::D     D:::::U         W:::::W       W:::::::W       W:::::W   O:::::O     O:::::ON:::::::N::::N  N::::::N     !:::!\n" +
                "     Y:::::::::Y     O:::::O     O:::::O U:::::D     D:::::U          W:::::W     W:::::::::W     W:::::W    O:::::O     O:::::ON::::::N N::::N N::::::N     !:::!\n" +
                "      Y:::::::Y      O:::::O     O:::::O U:::::D     D:::::U           W:::::W   W:::::W:::::W   W:::::W     O:::::O     O:::::ON::::::N  N::::N:::::::N     !:::!\n" +
                "       Y:::::Y       O:::::O     O:::::O U:::::D     D:::::U            W:::::W W:::::W W:::::W W:::::W      O:::::O     O:::::ON::::::N   N:::::::::::N     !:::!\n" +
                "       Y:::::Y       O:::::O     O:::::O U:::::D     D:::::U             W:::::W:::::W   W:::::W:::::W       O:::::O     O:::::ON::::::N    N::::::::::N     !!:!!\n" +
                "       Y:::::Y       O::::::O   O::::::O U::::::U   U::::::U              W:::::::::W     W:::::::::W        O::::::O   O::::::ON::::::N     N:::::::::N      !!! \n" +
                "       Y:::::Y       O:::::::OOO:::::::O U:::::::UUU:::::::U               W:::::::W       W:::::::W         O:::::::OOO:::::::ON::::::N      N::::::::N          \n" +
                "    YYYY:::::YYYY     OO:::::::::::::OO   UU:::::::::::::UU                 W:::::W         W:::::W           OO:::::::::::::OO N::::::N       N:::::::N      !!! \n" +
                "    Y:::::::::::Y       OO:::::::::OO       UU:::::::::UU                    W:::W           W:::W              OO:::::::::OO   N::::::N        N::::::N     !!:!!\n" +
                "    YYYYYYYYYYYYY         OOOOOOOOO           UUUUUUUUU                       WWW             WWW                 OOOOOOOOO     NNNNNNNN         NNNNNNN      !!! \n");
    }

    /**
     * prints an ascii art indicating that the player lost
     */
    private void printLoserScreen() {

        System.out.println("                                                                                                                                                                    \n" +
                "YYYYYYY       YYYYYYY     OOOOOOOOO     UUUUUUUU     UUUUUUUU     LLLLLLLLLLL                  OOOOOOOOO        SSSSSSSSSSSSSSS TTTTTTTTTTTTTTTTTTTTTTT                        \n" +
                "Y:::::Y       Y:::::Y   OO:::::::::OO   U::::::U     U::::::U     L:::::::::L                OO:::::::::OO    SS:::::::::::::::ST:::::::::::::::::::::T                        \n" +
                "Y:::::Y       Y:::::Y OO:::::::::::::OO U::::::U     U::::::U     L:::::::::L              OO:::::::::::::OO S:::::SSSSSS::::::ST:::::::::::::::::::::T                        \n" +
                "Y::::::Y     Y::::::YO:::::::OOO:::::::OUU:::::U     U:::::UU     LL:::::::LL             O:::::::OOO:::::::OS:::::S     SSSSSSST:::::TT:::::::TT:::::T                        \n" +
                "YYY:::::Y   Y:::::YYYO::::::O   O::::::O U:::::U     U:::::U        L:::::L               O::::::O   O::::::OS:::::S            TTTTTT  T:::::T  TTTTTT                        \n" +
                "   Y:::::Y Y:::::Y   O:::::O     O:::::O U:::::D     D:::::U        L:::::L               O:::::O     O:::::OS:::::S                    T:::::T                                \n" +
                "    Y:::::Y:::::Y    O:::::O     O:::::O U:::::D     D:::::U        L:::::L               O:::::O     O:::::O S::::SSSS                 T:::::T                                \n" +
                "     Y:::::::::Y     O:::::O     O:::::O U:::::D     D:::::U        L:::::L               O:::::O     O:::::O  SS::::::SSSSS            T:::::T                                \n" +
                "      Y:::::::Y      O:::::O     O:::::O U:::::D     D:::::U        L:::::L               O:::::O     O:::::O    SSS::::::::SS          T:::::T                                \n" +
                "       Y:::::Y       O:::::O     O:::::O U:::::D     D:::::U        L:::::L               O:::::O     O:::::O       SSSSSS::::S         T:::::T                                \n" +
                "       Y:::::Y       O:::::O     O:::::O U:::::D     D:::::U        L:::::L               O:::::O     O:::::O            S:::::S        T:::::T                                \n" +
                "       Y:::::Y       O::::::O   O::::::O U::::::U   U::::::U        L:::::L         LLLLLLO::::::O   O::::::O            S:::::S        T:::::T                                \n" +
                "       Y:::::Y       O:::::::OOO:::::::O U:::::::UUU:::::::U      LL:::::::LLLLLLLLL:::::LO:::::::OOO:::::::OSSSSSSS     S:::::S      TT:::::::TT                              \n" +
                "    YYYY:::::YYYY     OO:::::::::::::OO   UU:::::::::::::UU       L::::::::::::::::::::::L OO:::::::::::::OO S::::::SSSSSS:::::S      T:::::::::T       ......  ......  ...... \n" +
                "    Y:::::::::::Y       OO:::::::::OO       UU:::::::::UU         L::::::::::::::::::::::L   OO:::::::::OO   S:::::::::::::::SS       T:::::::::T       .::::.  .::::.  .::::. \n" +
                "    YYYYYYYYYYYYY         OOOOOOOOO           UUUUUUUUU           LLLLLLLLLLLLLLLLLLLLLLLL     OOOOOOOOO      SSSSSSSSSSSSSSS         TTTTTTTTTTT       ......  ......  ...... ");
    }

    /**
     * Prints the leaderboard
     *
     * @param players ArraList of {@link PlayerMTC} to print in the leaderboard
     */
    private void printLeaderBoard(ArrayList<PlayerMTC> players) {
        for (int i = 0; i < players.size(); i++) {
            PlayerMTC player = players.get(i);
            if (player.getNickname().equals(SocketClient.getInstance().getNickname())) {
                System.out.println("\u001B[1m " + (i + 1) + ". " + player.getNickname() + "     " + player.getScore() + "\033[0;1m");
            }
            System.out.println((i + 1) + ". " + player.getNickname() + "     " + player.getScore());
        }
    }
}
