package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * This class is used to read the input stream and making the input kind of interruptible.
 */
public class InputReader implements Callable<String> {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final BufferedReader br;

    public InputReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String call() throws IOException, InterruptedException {
        String input;
        // mark the current position in the stream
        br.mark(1);
        // wait until there is data to complete a readLine()
        while (!br.ready()) {
            Thread.sleep(200);
        }
        input = br.readLine();
        // reset the stream to the marked position
        br.reset();
        return input;
    }

    /**
     * @return a map containing the server address and port that the user will be connected to
     */
    public static Map<String, String> askServerInfo()  {
        Map<String, String> serverInfo = new HashMap<>();
        String defaultAddress = "localhost";
        String defaultPort = "28888";
        boolean validInput;

        System.out.println("Please specify the following settings. The default value is shown between brackets.");

        do {
            System.out.print("Enter the server address [" + defaultAddress + "]: ");

            String address;
            try {
                address = readLine();
            } catch (ExecutionException e) {
                e.printStackTrace();
                address = "";
            }

            if (address.equals("")) {
                serverInfo.put("address", defaultAddress);
                validInput = true;
            } else if (InputValidator.isValidIpAddress(address)) {
                serverInfo.put("address", address);
                validInput = true;
            } else {
                System.out.println("Invalid address!");
                validInput = false;
            }
        } while (!validInput);

        do {
            System.out.print("Enter the server port [" + defaultPort + "]: ");
            String port;
            try {
                port = readLine();
            } catch (ExecutionException e) {
                e.printStackTrace();
                port = "";
            }

            if (port.equals("")) {
                serverInfo.put("port", defaultPort);
                validInput = true;
            } else {
                if (InputValidator.isValidPort(port)) {
                    serverInfo.put("port", port);
                    validInput = true;
                } else {
                    System.out.println("Invalid port!");
                    validInput = false;
                }
            }
        } while (!validInput);

        return serverInfo;
    }

    public static String askNickname() {
        String nickname = null;
        do {
            System.out.print("Enter your nickname: ");


            try {
                nickname = readLine();
            } catch (ExecutionException e) {
                System.out.println("User input canceled.");
            }
        } while (nickname == null || nickname.equals(""));

        return nickname;
    }

    /**
     * @return the string read from the input.
     * @throws ExecutionException if the input stream thread is interrupted.
     */
    public static String readLine() throws ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new InputReader());
            executorService.submit(futureTask);

        String input = null;

        try {
            input = futureTask.get();
        } catch (InterruptedException e) {
            futureTask.cancel(true);
            executorService.shutdownNow();
        }
        return input;
    }
}