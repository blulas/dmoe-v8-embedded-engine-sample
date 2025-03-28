package com.ibm.techsales.dmoe.sample.model;

public class LoanApplication {

    private String id;
    private Applicant applicant;
    private int amount;
    private int deposit;
    private boolean approved = false;
    private int maxAmount;

    public LoanApplication() {
    }

    public LoanApplication(String id, int amount, int deposit, int maxAmount, Applicant applicant) {
        
        this.id = id;
        this.amount = amount;
        this.deposit = deposit;
        this.maxAmount = maxAmount;
        this.applicant = applicant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getMaxAmount() {
        return this.maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public String toString() {
        return "LoanApplication [id=" + id + ", amount=" + amount + ", deposit=" + deposit + ", approved=" + approved + ", maxAmount=" + maxAmount + ", applicant=" + applicant + "]";
    }
}

