package it.polimi.ingsw.View.GUI.SceneController.Utility;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The ConnectionPendingTimer class is used to measure the time taken by the server to respond.
 * If the response time exceeds a specified threshold, it sends an error message.
 */
public class ConnectionPendingTimer {
    /**
     * used to measure the time taken by the server to respond,
     * if it is too much it sends an error message
     */
    private static final Timer connectionPendingTimer = new Timer();
    /**
     * used to keep track if the connectionPendingTimer is counting,
     * when it is, the connectionPending will stop requesting to
     * join a game even if the player clicks "continue".
     */
    private static boolean connectionPending;

    /**
     * Starts the connection pending timer with the specified time threshold.
     * If the timer is already running, it will be canceled and restarted.
     *
     * @param time the time threshold in seconds
     */
    public static void start(int time) {
        if (connectionPending) {
            cancel();
        }

        connectionPending = true;

        TimerTask task = new TimerTask() {
            int secondsPassed = 0;

            public void run() {
                if (connectionPending && secondsPassed > time) {
                    connectionPending = false;
                    cancel();
                }
                secondsPassed++;
            }
        };

        // Schedule the task to run every second
        connectionPendingTimer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * Cancels the connection pending timer and resets the connection pending flag.
     */
    public static void cancel() {
        connectionPendingTimer.cancel();
        connectionPending = false;
    }

    /**
     * Checks if the connection pending timer is currently running.
     *
     * @return true if the connection pending timer is running, false otherwise
     */
    public static boolean isPending() {
        return connectionPending;
    }
}