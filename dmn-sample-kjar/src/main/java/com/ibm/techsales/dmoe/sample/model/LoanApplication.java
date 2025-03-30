package com.ibm.techsales.dmoe.sample.model;

import java.time.LocalDate;

public class LoanApplication {

    private String applicantId;
    private String explanation;
    private boolean approved;
    
    public LoanApplication() {
    }

    public LoanApplication(String applicantId, String explanation, boolean approved) {

        this.applicantId = applicantId;
        this.explanation = explanation;
        this.approved = approved;
    }

    public String getApplicantId() {
        return this.applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isApproved() {
        return this.approved;
    }

    public boolean getApproved() {
        return this.approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
    @Override
    public String toString() {
        return "LoanApplication [applicantId=" + applicantId + ", explanation=" + explanation + ", tapprovedpe=" + approved + "]";
    }
}
