package it.polimi.ingsw.Controller.server.Messages;

import it.polimi.ingsw.View.VirtualView.Messages.ShelfMessageToClient;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ShelfMessageTestToClient {
    @Test
    public void dimension() throws IOException {

        Player newPlayer = new Player("Diego");
        newPlayer.getShelf().insertTile(new ItemTile(Color.WHITE), 2);

        ShelfMessageToClient obj = new ShelfMessageToClient(newPlayer); // create an instance of your object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized shelf size: " + size + " bytes");
    }
}
