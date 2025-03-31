package com.ibm.techsales.dmoe.engine.api;

import java.util.List;
import java.util.ArrayList;

public class DecisionResults {

    private String startedOn;
    private String completedOn;
    private ExecutionDuration executionDuration;
    private List<DecisionResult> decisions = new ArrayList<DecisionResult>();

    public String getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getCompletedOn() {
        return this.completedOn;
    }

    public void setCompletedOn(String completedOn) {
        this.completedOn = completedOn;
    }

    public ExecutionDuration getExecutionDuration() {
        return this.executionDuration;
    }

    public void setExecutionDuration(ExecutionDuration executionDuration) {
        this.executionDuration = executionDuration;
    }

    public List<DecisionResult> getDecisions() {
        return this.decisions;
    }

    public void setDecisions(List<DecisionResult> decisions) {
        this.decisions = decisions;
    }

    @Override
    public String toString() {
        return "startedOn=" + startedOn + ", completedOn=" + completedOn + ", executionDuration=" + executionDuration;
    }
}
