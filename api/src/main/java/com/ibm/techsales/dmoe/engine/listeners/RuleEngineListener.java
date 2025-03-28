package com.ibm.techsales.dmoe.engine.listeners;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleEngineListener implements AgendaEventListener {

    private static final Logger logger = LoggerFactory.getLogger(RuleEngineListener.class);

    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        logger.info("--> Firing Rule: rule=" + event.getMatch().getRule().getName());
	}

    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        logger.info("--> Agenda Group popped from agenda: agendaGroup=" + event.getAgendaGroup().getName());
	}

    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
        logger.info("--> Agenda Group pushed to agenda: agendaGroup=" + event.getAgendaGroup().getName());
	}

    public void matchCreated(MatchCreatedEvent event) {
        logger.info("--> Rule Added to Agenda: rule=" + event.getMatch().getRule().getName());
	}

    public void matchCancelled(MatchCancelledEvent event) {
        logger.info("--> Rule Removed from Agenda: rule=" + event.getMatch().getRule().getName());
	}

    public void afterMatchFired(AfterMatchFiredEvent event) {
    }

    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
    }

    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	}

    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	}

    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	}
}