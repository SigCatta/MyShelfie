package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.SocketClient;

public class WaitingPlayerState extends InputState {
    WaitingPlayerState(InputStatePlayer player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("You are not the active player!");
        String nickname = SocketClient.getInstance().getNickname();



        while (!GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)){
            synchronized (GameRepresentation.getInstance()){
                waitForVM(GameRepresentation.getInstance());
            }
        }

        player.setState(new ActivePlayerState(player));
    }
}
