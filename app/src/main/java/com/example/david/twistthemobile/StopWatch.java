package com.example.david.twistthemobile;

/**
 * Created by David on 4/07/2017.
 */

public class StopWatch {
    private long startTime = -1;
    private long endTime = -1;
    private boolean isRunning = false;

    public void startTimer() {
        this.isRunning = true;
        this.startTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        this.isRunning = false;
        this.endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        if (isRunning) {
            return System.currentTimeMillis() - this.startTime;
        }
        return this.endTime - this.startTime;
    }
}
