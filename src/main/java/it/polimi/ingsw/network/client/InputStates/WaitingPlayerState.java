package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.View.CLI.Printer;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.InputStates.readers.WaitingReader;
import it.polimi.ingsw.network.client.SocketClient;

public class WaitingPlayerState extends InputState {
    WaitingPlayerState(InputStatePlayer player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("You are not the active player...");
        String nickname = SocketClient.getInstance().getNickname();

        WaitingReader reader = new WaitingReader();
        Thread readerThread = new Thread(reader);

        readerThread.start(); //reads input commands and sends messages to the server

        while (!GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) { // waits for the model to change and updates the view
            synchronized (EchosRepresentation.getInstance()) {
                waitForVM(EchosRepresentation.getInstance());
            }

            while (reader.isReading()){ // if the user is using a command the view does not update
                synchronized (reader){
                    try {
                        reader.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            EchoMTC message = EchosRepresentation.getInstance().getMessage();
            if (message.getID() == EchoID.CHANGE) Printer.printHomeScreen();
            else System.out.println(message.getOutput());
        }

        readerThread.interrupt();

        player.setState(new ActivePlayerState(player));
    }
}
