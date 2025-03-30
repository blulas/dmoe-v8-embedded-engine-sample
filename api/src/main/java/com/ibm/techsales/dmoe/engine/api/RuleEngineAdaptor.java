package com.ibm.techsales.dmoe.engine.api;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieContainerSessionsPool;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.builder.ReleaseId;
import org.kie.api.command.Command;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.internal.command.CommandFactory;

import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.InsertElementsCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleEngineAdaptor {

    private static final Logger logger = LoggerFactory.getLogger(RuleEngineAdaptor.class);
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private KieServices kieServices;
    private KieContainer kieContainer;
    private KieSession kieSession;
    private String kieSessionName;
    private KieContainerSessionsPool kieSessionsPool;
    private ReleaseId releaseId;
   
    public RuleEngineAdaptor() {
    }

    public RuleEngineAdaptor(String kieSessionName, String groupId, String artifactId, String version) {

        try {
            register(kieSessionName, 0, groupId, artifactId, version);
        } catch (Exception e) {
            logger.error("Unable to register KIE Session: reason=" + e.getMessage());
        }
    }

    public RuleEngineAdaptor(String kieSessionName, int kieSessionPoolSize, String groupId, String artifactId, String version) {

        try {
            register(kieSessionName, kieSessionPoolSize, groupId, artifactId, version);
        } catch (Exception e) {
            logger.error("Unable to register KIE Session: reason=" + e.getMessage());
        }
    }

    public void register(String kieSessionName, String groupId, String artifactId, String version)  throws Exception {
        register(kieSessionName, 0, groupId, artifactId, version);
    }

    public void register(String kieSessionName, int kieSessionPoolSize, String groupId, String artifactId, String version) throws Exception {

        this.kieSessionName = kieSessionName;
        this.kieServices = KieServices.Factory.get();
        this.releaseId = kieServices.newReleaseId(groupId, artifactId, version);
        this.kieContainer = kieServices.newKieContainer(this.releaseId);
        this.kieSessionsPool = this.kieContainer.newKieSessionsPool(kieSessionPoolSize);
        logger.info("Registered rule engine: kieSessionName=" + kieSessionName + ", kieSessionPoolSize=" + kieSessionPoolSize + ", KJAR=" + releaseId.toString() + "...");
    }

    public ExecutionInfo execute(Map<String, Object> facts) throws Exception {

        List<Object> variables = new ArrayList<Object>();

        for (Map.Entry<String, Object> entry : facts.entrySet()) {
            variables.add(entry.getValue());
        }

        return execute(variables);
    }

    public ExecutionInfo execute(List<Object> facts) throws Exception {

        // Mark the start time
        LocalDateTime startedOn = LocalDateTime.now();

        // Get a kieSession from the pool
        this.kieSession = this.kieSessionsPool.newKieSession(this.kieSessionName);

        // Insert all facts into a command object
        Command insertCommand = CommandFactory.newInsertElements(facts);

        // Determine how we will fire rules
        FireAllRulesCommand fireCommand = new FireAllRulesCommand();
        fireCommand.setOutIdentifier("firedActivations");

        // Prepare the batch of commands
        BatchExecutionCommand batchCommand = new BatchExecutionCommandImpl(Arrays.asList(insertCommand, fireCommand), this.kieSessionName);

        // Execute all commands
        ExecutionResults results = (ExecutionResults) this.kieSession.execute(batchCommand);

        // Cleanup
        this.kieSession.dispose();

        // Mark completion time        
        LocalDateTime completedOn = LocalDateTime.now();

        // Report
        ExecutionDuration duration = calculateExecutionDuration(startedOn, completedOn);
        
        // Prepare the execution results
        ExecutionInfo executionInfo = new ExecutionInfo();
        executionInfo.setStartedOn(formatLocalDateTime(startedOn));
        executionInfo.setCompletedOn(formatLocalDateTime(completedOn));
        executionInfo.setExecutionDuration(duration);
        executionInfo.setFiredRuleCount((int) results.getValue("firedActivations"));
        executionInfo.setFacts(facts);

        return executionInfo;
    }
    
    public void dispose() throws Exception {

        logger.info("Cleaning up rule session pool...");
        this.kieSessionsPool.shutdown();
    }

    private ExecutionDuration calculateExecutionDuration(LocalDateTime begin, LocalDateTime end) {

        ExecutionDuration ed = new ExecutionDuration();
        ed.setDays(ChronoUnit.DAYS.between(begin, end));
        ed.setHours(ChronoUnit.HOURS.between(begin, end));
        ed.setMinutes(ChronoUnit.MINUTES.between(begin, end));
        ed.setSeconds(ChronoUnit.SECONDS.between(begin, end));
        ed.setMilliseconds(ChronoUnit.MILLIS.between(begin, end));
        return ed;
   }

   private String formatLocalDateTime(LocalDateTime ldt) {

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
       return ldt.format(formatter);
   }
}
