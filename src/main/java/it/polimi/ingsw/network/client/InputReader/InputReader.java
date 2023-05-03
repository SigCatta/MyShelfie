package it.polimi.ingsw.network.client.InputReader;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.HandshakeMessage;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Client.VirtualModel.ErrorsRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.GameRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.GeneralMessagesRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.Enum.ErrorCode;
import it.polimi.ingsw.View.VirtualView.Messages.GameMessageToClient;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

@SuppressWarnings("BusyWait")
public class InputReader implements Runnable {
    private String input;
    private final SocketClient socketClient;

    public InputReader(String input) {
        this.input = input;
        socketClient = (SocketClient) SocketClient.getInstance();
    }

    @Override
    public void run() {
        insertNickname();
        System.out.println("Type 'join' if you want to join a game, 'new' if you want to create a new one: ");
        startOrJoin();
        while (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
            System.out.println(PlayersRepresentation.getInstance().getPlayersList());
            System.out.println("Waiting for players...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("The game has started!");
    }


    private void startOrJoin() {
        while (input == null) {
            getInput();
            if (input.equals("join")) joinGame();
            else if (input.equals("new")) createNewGame();
            else {
                System.out.println("ERROR: Invalid command!\nType 'join' if you want to join a game, 'new' if you want to create a new one: ");
                input = null;
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
            System.out.println("Waiting for server to respond...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            gameMessage = GameRepresentation.getInstance().getGameMessage();
        }
        System.out.println("The game was successfully create! The gameID is: " + gameMessage.getGameID());
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
                    System.out.println("Waiting for server to respond...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (ErrorsRepresentation.getInstance().getErrorCodes().contains(ErrorCode.NOID)) {
                    System.out.println("ERROR: This gameID is not associated to a game!");
                    break;
                }
                if (GameRepresentation.getInstance().getGameMessage() != null) {
                    System.out.println("You joined game " + GameRepresentation.getInstance().getGameMessage().getGameID());
                }
            }
        }
    }

    private void getInput() {
        try {
            input = readLine();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertNickname() {
        while (true){
            System.out.println("Insert nickname:");
            getInput();
            socketClient.sendCommand(new HandshakeMessage(input));
            while (true){
                System.out.println("Waiting for server to respond...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (ErrorsRepresentation.getInstance().getErrorCodes().contains(ErrorCode.BADNICK)) {
                    System.out.println("This nickname is already taken");
                    break;
                }
                if (GeneralMessagesRepresentation.getInstance().getCodes().contains("NICKOK")){
                    System.out.println("Nickname accepted!");
                    input = null;
                    return;
                }
            }
        }
    }
}
