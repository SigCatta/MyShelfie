package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Client.VirtualModel.ErrorsRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.GameRepresentation;
import it.polimi.ingsw.Enum.ErrorCode;
import it.polimi.ingsw.View.VirtualView.Messages.GameMessageToClient;
import it.polimi.ingsw.network.client.InputReader;

public class StartOrJoinState extends InputState {
    public StartOrJoinState(InputReader reader) {
        super(reader);
    }

    @Override
    public void play() {
        System.out.println("Type 'join' if you want to join a game, 'new' if you want to create a new one: ");
        while (input == null) {
            getInput();
            if (input.equals("join")) joinGame();
            else if (input.equals("new")) createNewGame();
            else {
                System.out.println("ERROR: Invalid command!\nType 'join' if you want to join a game, 'new' if you want to create a new one: ");
                input = null;
            }
        }
        reader.setState(new WaitingForPlayersState(reader));
    }

    private void joinGame() {
        while (true) {
            while (true) {
                System.out.println("Insert gameID: ");
                getInput();
                try {
                    socketClient.sendCommand(new CanIPlayMessage(Integer.parseInt(input)));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: gameID must be a number!");
                }
            }
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (ErrorsRepresentation.getInstance().getErrorCodes().contains(ErrorCode.NOID)) {
                    System.out.println("ERROR: This gameID is not associated to a game!");
                    break;
                }
                if (GameRepresentation.getInstance().getGameMessage() != null) {
                    System.out.println("You joined game " + GameRepresentation.getInstance().getGameMessage().getGameID());
                    return;
                }
            }
        }
    }

    private void createNewGame() {
        System.out.println("Insert players number (between 2 and 4): ");
        int numOfPlayers;
        do {
            getInput();
            numOfPlayers = Integer.parseInt(input);
            if (numOfPlayers >= 5 || numOfPlayers <= 1) System.out.println("ERROR: the number of players must be between 2 and 4!\nInsert players number: ");
        } while (numOfPlayers >= 5 || numOfPlayers <= 1);
        socketClient.sendCommand(new NewGameMessage(numOfPlayers));

        GameMessageToClient gameMessage = null;
        while (gameMessage == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            gameMessage = GameRepresentation.getInstance().getGameMessage();
        }
        System.out.println("The game was successfully create! The gameID is: " + gameMessage.getGameID());
    }

}
