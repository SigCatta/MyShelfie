package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.Controller.Client.NewGameMTS;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.VirtualView.Messages.GameMTC;
import it.polimi.ingsw.View.CLI.InputStatePlayer;

public class StartOrJoinState extends InputState {
    public StartOrJoinState(InputStatePlayer player) {
        super(player);
    }

    /**
     * Asks the player to either join a game or create a new one,
     * after the player decides and inputs the correct information the state changes to WaitingForPlayers
     */
    @Override
    public void play() {
        System.out.println("Type 'join' if you want to join a game, 'new' if you want to create a new one: ");
        while (input == null) {
            getInput();
            if (input.equals("join")) {
                joinGame();
                if (input.equals(".")) {
                    input = null;
                    return;
                }
            } else if (input.equals("new")) {
                createNewGame();
                if (input.equals(".")) {
                    input = null;
                    return;
                }
            } else {
                System.out.println("ERROR: Invalid command!\nType 'join' if you want to join a game, 'new' if you want to create a new one: ");
                input = null;
            }
        }
        player.setState(new WaitingForPlayersState(player));
    }

    /**
     * Asks the player for a gameID to join a game, if the gameID is wrong, asks again
     */
    private void joinGame() {
        while (true) {
            while (true) {
                System.out.println("Insert gameID: ");
                getInput();
                if (input.equals(".")) return;
                try {
                    socketClient.sendCommand(new CanIPlayMTS(Integer.parseInt(input)));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: gameID must be a number!");
                }
            }
            while (true) {
                synchronized (EchosRepresentation.getInstance()) {
                    waitForVM(EchosRepresentation.getInstance());
                }
                EchoMTC message = EchosRepresentation.getInstance().getMessage();
                if (message.isError()) {
                    System.out.println(message.getOutput());
                    break;
                }
                if (GameRepresentation.getInstance().getGameMessage() != null) {
                    System.out.println(message.getOutput() + GameRepresentation.getInstance().getGameMessage().getGameID());
                    return;
                }
            }
        }
    }

    /**
     * ASks for the game's number of players and starts the game if the number is valid
     */
    private void createNewGame() {
        System.out.println("Insert number of players (between 2 and 4): ");
        int numOfPlayers = 0;
        do {
            getInput();
            if (input.equals(".")) return;
            try {
                numOfPlayers = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: number of players must be a number!\n Insert number of players: ");
                continue;
            }
            if (numOfPlayers >= 5 || numOfPlayers <= 1) System.out.println("ERROR: the number of players must be between 2 and 4!\nInsert number of players: ");
        } while (numOfPlayers >= 5 || numOfPlayers <= 1);
        socketClient.sendCommand(new NewGameMTS(numOfPlayers));

        GameMTC gameMessage = null;
        while (gameMessage == null) {
            synchronized (GameRepresentation.getInstance()) {
                waitForVM(GameRepresentation.getInstance());
            }
            gameMessage = GameRepresentation.getInstance().getGameMessage();
        }
        System.out.println("The game was successfully create! The gameID is: " + gameMessage.getGameID());
    }

}
