package edu.marist.cmpt220l.storesim;

/**
 * Created by brian on 11/16/14.
 */
public class Register {
    private Customer customer;
    private int timeToService;

    /**
     * Retrieve the current customer at this register
     * @return the current customer at this register
     */
    public Customer getCustomer() {
        return customer;
    }

    public int getTimeRemaining() {
        return timeToService;
    }

    public boolean hasCustomer() {
        return customer != null;
    }

    public void removeCustomer() {
        customer = null;
    }

    /**
     * Set the customer for this register as well as how long it will take to service them.
     *
     * @param customer the customer that is at this register
     * @param timeToService the time it will take to service this customer
     */
    public void setCustomer(Customer customer, int timeToService) {
        this.customer = customer;
        this.timeToService = timeToService;
    }

    /**
     * Update this register processing time.
     *
     * @return true if the current customer is finished, false otherwise
     */
    public boolean update() {
        if(!hasCustomer())
            return false;
        return --timeToService <= 0;
    }
}
