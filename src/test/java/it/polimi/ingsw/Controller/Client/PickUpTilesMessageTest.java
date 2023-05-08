package it.polimi.ingsw.Controller.Client;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class PickUpTilesMessageTest {
    @Test
    public void dimension() throws IOException {

        ArrayList<Point> tiles = new ArrayList<>(Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(2, 3)));

        MessageToServer obj = new PickUpTilesMTS(tiles);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized pickup message size: " + size + " bytes");
    }
}
