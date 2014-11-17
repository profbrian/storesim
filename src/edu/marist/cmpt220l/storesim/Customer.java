package edu.marist.cmpt220l.storesim;

/**
 * Created by brian on 11/16/14.
 */
public class Customer {
    private int waitTime;

    /**
     * Update this customer's processing time
     */
    public void update() {
        waitTime++;
    }

    /**
     * Retrieve this customer's wait time
     *
     * @return this customer's wait time
     */
    public int getWaitTime() {
        return waitTime;
    }
}
