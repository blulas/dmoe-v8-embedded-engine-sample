package com.ibm.techsales.dmoe.engine.listeners;

import org.kie.dmn.api.core.event.AfterEvaluateBKMEvent;
import org.kie.dmn.api.core.event.AfterEvaluateContextEntryEvent;
import org.kie.dmn.api.core.event.AfterEvaluateDecisionServiceEvent;
import org.kie.dmn.api.core.event.AfterEvaluateDecisionTableEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateBKMEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateContextEntryEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateDecisionEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateDecisionServiceEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateDecisionTableEvent;
import org.kie.dmn.api.core.event.DMNRuntimeEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecisionEngineListener implements DMNRuntimeEventListener {

    private static final Logger logger = LoggerFactory.getLogger(DecisionEngineListener.class);

    public void beforeEvaluateDecision(BeforeEvaluateDecisionEvent event) {
        logger.info("beforeEvaluateDecision: " + event.toString());
    }

    public void beforeEvaluateBKM(BeforeEvaluateBKMEvent event) {
        logger.info("beforeEvaluateBKM: " + event.toString());
    }
    
    public void afterEvaluateBKM(AfterEvaluateBKMEvent event) {
        logger.info("afterEvaluateBKM: " + event.toString());
    }
    
    public void beforeEvaluateContextEntry(BeforeEvaluateContextEntryEvent event) {
        logger.info("beforeEvaluateContextEntry: " + event.toString());
    }
    
    public void afterEvaluateContextEntry(AfterEvaluateContextEntryEvent event) {
        logger.info("afterEvaluateContextEntry: " + event.toString());
    }
    
    public void beforeEvaluateDecisionTable(BeforeEvaluateDecisionTableEvent event) {
        logger.info("beforeEvaluateDecisionTable: " + event.toString());
    }
    
    public void afterEvaluateDecisionTable(AfterEvaluateDecisionTableEvent event) {
        logger.info("afterEvaluateDecisionTable: " + event.toString());
    }
    
    public void beforeEvaluateDecisionService(BeforeEvaluateDecisionServiceEvent event) {
        logger.info("beforeEvaluateDecisionService: " + event.toString());
    }
    
    public void afterEvaluateDecisionService(AfterEvaluateDecisionServiceEvent event) {
        logger.info("afterEvaluateDecisionService: " + event.toString());
    }
}