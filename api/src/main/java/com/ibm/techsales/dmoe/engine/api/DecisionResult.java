package com.ibm.techsales.dmoe.engine.api;

import java.util.List;
import java.util.ArrayList;

public class DecisionResult {

    private String decisionId;
    private String decisionName;
    private String status;
    private boolean hasErrors;
    private List<String> messages = new ArrayList<String>();
    private List<Object> results = new ArrayList<Object>();

    public String getDecisionId() {
        return this.decisionId;
    }

    public void setDecisionId(String decisionId) {
        this.decisionId = decisionId;
    }

    public String getDecisionName() {
        return this.decisionName;
    }

    public void setDecisionName(String decisionName) {
        this.decisionName = decisionName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<Object> getResults() {
        return this.results;
    }

    public void setFacts(List<Object> results) {
        this.results = results;
    }

    public boolean hasErrors() {
        return this.hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }
    @Override
    public String toString() {
        return "name=" + decisionName + ", results" + results + ", hasErrors=" + hasErrors + ", status=" + status + ", messages=" + messages;
    }
}
