package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.Controller.Client.CanIPlayMTS;
import it.polimi.ingsw.Controller.Client.NewGameMTS;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.VirtualView.Messages.GameMTC;

public class StartOrJoinState extends InputState implements VirtualModelObserver {

    private boolean triedToCreateAGame;
    private boolean hasJoined;

    /**
     * Asks the player to either join a game or create a new one,
     * after the player decides and inputs the correct information the state changes to WaitingForPlayers
     */
    @Override
    public void play() {
        hasJoined = false;

        System.out.println("Type 'join' if you want to join a game, 'new' if you want to create a new one: ");
        while (input == null) {
            input = Reader.getInput();
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
    }

    /**
     * Asks the player for a gameID to join a game, if the gameID is wrong, asks again
     */
    private void joinGame() {
        while (true) {
            System.out.println("Insert gameID: ");
            input = Reader.getInput();
            if (input.equals(".")) return;

            GameRepresentation.getInstance().registerObserver(this);
            EchosRepresentation.getInstance().registerObserver(this);
            triedToCreateAGame = false;
            try {
                socketClient.sendCommand(new CanIPlayMTS(Integer.parseInt(input)));
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: gameID must be a number!");
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
            input = Reader.getInput();
            if (input.equals(".")) return;
            try {
                numOfPlayers = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: number of players must be a number!\n Insert number of players: ");
                continue;
            }
            if (numOfPlayers >= 5 || numOfPlayers <= 1) System.out.println("ERROR: the number of players must be between 2 and 4!\nInsert number of players: ");
        } while (numOfPlayers >= 5 || numOfPlayers <= 1);

        GameRepresentation.getInstance().registerObserver(this);
        triedToCreateAGame = true;
        socketClient.sendCommand(new NewGameMTS(numOfPlayers));
    }

    @Override
    public void update() {
        GameMTC gameMessage = GameRepresentation.getInstance().getGameMessage();
        if (hasJoined) return;
        if (triedToCreateAGame) {
            System.out.println("The game was successfully create! The gameID is: " + gameMessage.getGameID());
            GameRepresentation.getInstance().removeObserver(this);
        } else {
            if (EchosRepresentation.getInstance().peekMessage() != null) {
                EchoMTC message = EchosRepresentation.getInstance().popMessage();
                if (message.isError()) {
                    System.out.println(message.getOutput());
                    return;
                } else if (GameRepresentation.getInstance().getGameMessage() != null) {
                    GameRepresentation.getInstance().removeObserver(this);
                    EchosRepresentation.getInstance().removeObserver(this);
                }
            }
        }
        hasJoined = true;
        new WaitingForPlayersState().play();
    }
}
