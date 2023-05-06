package it.polimi.ingsw.Controller.server.Messages;

import it.polimi.ingsw.VirtualView.Messages.GameMTC;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameMessageTestToClient {
    @Test
    public void dimension() throws IOException {

        Game game = new Game(3);
        game.addPlayer(new Player("Gian"));
        game.addPlayer(new Player("Turing"));
        game.setGameID(29);
        game.start();


        GameMTC obj = new GameMTC(game); // create an instance of your object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized game size: " + size + " bytes");
    }
}
