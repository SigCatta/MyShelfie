package it.polimi.ingsw.network.client.InputStates.readers;

public class WaitingReader extends Reader implements Runnable {
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true){
            System.out.println("You can use the following commands:\n - chat\n - disconnect\n - common_goals\n - personal_goal\n - shelvs\n"); //TODO other commands?
            getInput();
            isReading = true;

            input = input.toLowerCase();
            executeCommand();

            isReading = false;
            notifyAll();
        }
    }

    @Override
    public void executeCommand() {
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isReading(){
        return isReading;
    }
}
