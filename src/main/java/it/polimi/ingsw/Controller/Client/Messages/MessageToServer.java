package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.server.SocketClientHandler;

public abstract class MessageToServer {
    /**
     * this information are necessary for the server to select the
     * game and player correctly, it is not necessary for the client
     * to send it every time.
     */
    private transient int gameId;
    private transient String nickname;
    private transient SocketClientHandler socketClientHandler;
    public abstract void update(Game game);

    public int getGameID() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public SocketClientHandler getSocketClientHandler() {
        return socketClientHandler;
    }

    public void setSocketClientHandler(SocketClientHandler socketClientHandler) {
        this.socketClientHandler = socketClientHandler;
    }
}
