package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputReaderTest {

    /*
    @Test
    void askServerInfo() {
        String input = "localhost\n12345\n";
        InputStream originalIn = System.in;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Map<String, String> serverInfo = InputReader.askServerInfo();

        assertEquals("localhost", serverInfo.get("address"));
        assertEquals("12345", serverInfo.get("port"));

        System.setIn(originalIn);
    }

     */

    @Test
    void askNickname() {
        String input = "player1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String nickname = InputReader.askNickname();

        assertEquals("player1", nickname);
    }

    @Test
    void readLine() {
        String input = "test\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String line = null;
        try {
            line = InputReader.readLine();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertEquals("test", line);
    }
}