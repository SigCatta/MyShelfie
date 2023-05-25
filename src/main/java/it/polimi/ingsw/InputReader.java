package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @SuppressWarnings("BusyWait")
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