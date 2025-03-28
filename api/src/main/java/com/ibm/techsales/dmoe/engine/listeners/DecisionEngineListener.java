package com.ibm.techsales.dmoe.engine.listeners;

import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.kie.dmn.api.core.event.AfterEvaluateAllEvent;
import org.kie.dmn.api.core.event.AfterEvaluateContextEntryEvent;
import org.kie.dmn.api.core.event.AfterEvaluateDecisionEvent;
import org.kie.dmn.api.core.event.AfterEvaluateDecisionTableEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateAllEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateContextEntryEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateDecisionEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateDecisionTableEvent;
import org.kie.dmn.api.core.event.DMNRuntimeEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecisionEngineListener implements DMNRuntimeEventListener {

    private static final Logger logger = LoggerFactory.getLogger(DecisionEngineListener.class);

    private final String name;

    public DecisionEngineListener(String name) {
        this.name = name;
    }

    @Override
    public void beforeEvaluateDecision(BeforeEvaluateDecisionEvent event) {
        logger.info("--> Evaluation: '" + event.getDecision().getName() + "'");
    }

    @Override
    public void afterEvaluateDecision(AfterEvaluateDecisionEvent event) {

        DecisionNode decisionNode = event.getDecision();
        String decisionNodeName = decisionNode.getName();
        DMNDecisionResult result = event.getResult().getDecisionResultByName(decisionNodeName);
        log(decisionNode, result);
    }

    @Override
    public void beforeEvaluateContextEntry(BeforeEvaluateContextEntryEvent event) {
        // log("BeforeEvaluateContextEntryEvent");
    }

    @Override
    public void afterEvaluateContextEntry(AfterEvaluateContextEntryEvent event) {
        // log("AfterEvaluateContextEntryEvent");
    }

    @Override
    public void beforeEvaluateDecisionTable(BeforeEvaluateDecisionTableEvent event) {
        // log("BeforeEvaluateDecisionTableEvent");
    }

    @Override
    public void afterEvaluateDecisionTable(AfterEvaluateDecisionTableEvent event) {
        logger.info("--> In the decision table: '" + event.getDecisionTableName()+ "' les lignes suivantes ont été exécutées " + event.getMatches());
    }

    @Override
    public void beforeEvaluateAll(BeforeEvaluateAllEvent event) {
        logger.info("--> New Audit ##");
    }

    @Override
    public void afterEvaluateAll(AfterEvaluateAllEvent event) {
        // log("AfterEvaluateAllEvent");
    }

    private void log(DecisionNode decisionNode, DMNDecisionResult dmnDecisionResult) {

        Object dmnResult = dmnDecisionResult.getResult();
        logger.info("--> Audit result for: '" + decisionNode.getName() + "' est: '" + dmnResult + "'");
    }
}