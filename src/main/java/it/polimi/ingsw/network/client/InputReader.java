package it.polimi.ingsw.network.client;

import it.polimi.ingsw.Controller.Client.Messages.CanIPlayMessage;
import it.polimi.ingsw.Controller.Client.Messages.HandshakeMessage;
import it.polimi.ingsw.Controller.Client.Messages.NewGameMessage;
import it.polimi.ingsw.Controller.Client.VirtualModel.ErrorsRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.GameRepresentation;
import it.polimi.ingsw.Enum.ErrorCode;

import java.util.concurrent.ExecutionException;

import static it.polimi.ingsw.InputReader.readLine;

public class InputReader implements Runnable {
    private String input;
    private SocketClient socketClient;

    InputReader(String input) {
        this.input = input;
        socketClient = (SocketClient) SocketClient.getInstance();
    }

    @Override
    public void run() {
        System.out.println("Insert nickname: ");
        sendNickname();
        System.out.println("Type 'join' if you want to join a game, 'new' if you want to create a new one: ");
        while (input == null) {
            getInput();
            startOrJoin();
        }
        while (true) {
        }
    }

    private void startOrJoin() {
        if (input.equals("join")) joinGame();
        else if (input.equals("new")) createNewGame();
        else {
            System.out.println("ERROR: Invalid command!\n Type 'join' if you want to join a game, 'new' if you want to create a new one: ");
            input = null;
        }
    }

    private void createNewGame() {
        System.out.println("Insert players number (between 2 and 4): ");
        int numOfPlayers;
        do {
            getInput();
            numOfPlayers = Integer.parseInt(input);
            if (numOfPlayers >= 5 || numOfPlayers <= 1) System.out.println("ERROR: the number of players must be between 2 and 4!\n Insert players number:");
        } while (numOfPlayers >= 5 || numOfPlayers <= 1);
        socketClient.sendCommand(new NewGameMessage(numOfPlayers));
    }

    @SuppressWarnings("BusyWait")
    private void joinGame() {
        while (true){
            System.out.println("Insert gameID: ");
            getInput();
            socketClient.sendCommand(new CanIPlayMessage(Integer.parseInt(input)));
            while (true) {
                try {
                    System.out.println("Wait for a server to respond...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (ErrorsRepresentation.getInstance().getErrorCodes().contains(ErrorCode.NOID)) {
                    System.out.println("This gameID is not associated to a game!"); break;
                }
                if (GameRepresentation.getInstance().getGameMessage() != null) return;
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

    private void sendNickname() {
        getInput();
        socketClient.sendCommand(new HandshakeMessage(input));
        input = null;
    }
}
