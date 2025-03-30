package com.ibm.techsales.dmoe.sample.model;

import java.time.LocalDate;

public class Fine {

    private int amount;
    private int points;

    public Fine() {
    }

    public Fine(int amount, int points) {

        this.amount = amount;
        this.points = points;        
    }    

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Fine [amount=" + amount + ", points=" + points + "]";
    }
}
