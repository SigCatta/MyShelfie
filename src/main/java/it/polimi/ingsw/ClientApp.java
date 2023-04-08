package it.polimi.ingsw;


/**
 * Main of the client app.
 */
public class ClientApp {

    public static void main(String[] args) {

        boolean cliParam = false; // default value

        for (String arg : args) {
            if (arg.equals("--cli") || arg.equals("-c")) {
                cliParam = true;
                break;
            }
        }

        //TODO: implement when Cli and Gui will be done
        if (cliParam) {
            //launch cli app
        } else {
            //launch gui app
        }
    }
}