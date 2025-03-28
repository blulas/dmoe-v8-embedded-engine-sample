package com.ibm.techsales.dmoe.engine.api;

import java.util.List;

public class ExecutionInfo {

    private String startedOn;
    private String completedOn;
    private int firedRuleCount;
    private ExecutionDuration executionDuration;
    private List<Object> facts;
    private List<Fact> variables;

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

    public int getFiredRuleCount() {
        return this.firedRuleCount;
    }

    public void setFiredRuleCount(int firedRuleCount) {
        this.firedRuleCount = firedRuleCount;
    }

    public ExecutionDuration getExecutionDuration() {
        return this.executionDuration;
    }

    public void setExecutionDuration(ExecutionDuration executionDuration) {
        this.executionDuration = executionDuration;
    }

    public List<Object> getFacts() {
        return this.facts;
    }

    public void setFacts(List<Object> facts) {
        this.facts = facts;
    }

    public List<Fact> getVariables() {
        return this.variables;
    }

    public void setVariables(List<Fact> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "startedOn=" + startedOn + ", completedOn=" + completedOn + ", firedRuleCount=" + firedRuleCount + ", executionDuration=" + executionDuration;
    }
}