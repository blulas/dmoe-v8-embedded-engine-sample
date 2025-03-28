package com.ibm.techsales.dmoe.engine.api;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.api.builder.ReleaseId;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecisionEngineAdaptor {
    
    private static final Logger logger = LoggerFactory.getLogger(DecisionEngineAdaptor.class);
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private KieServices kieServices;
    private KieContainer kieContainer;
    private ReleaseId releaseId;
    private String namespace;
    private String modelName;

    public DecisionEngineAdaptor() {
    }

    public DecisionEngineAdaptor(String namespace, String modelName, String groupId, String artifactId, String version) {

        try {
            register(namespace, modelName, groupId, artifactId, version);
        } catch (Exception e) {
            logger.error("Unable to register KIE Session: reason=" + e.getMessage());
        }
    }

    public void register(String namespace, String modelName, String groupId, String artifactId, String version)  throws Exception {

        this.kieServices = KieServices.Factory.get();
        this.releaseId = kieServices.newReleaseId(groupId, artifactId, version);
        this.kieContainer = kieServices.newKieContainer(this.releaseId);
        this.namespace = namespace;
        this.modelName = modelName;
        logger.info("Registered decision engine: namespace=" + namespace + ", modelName=" + modelName + ", KJAR=" + releaseId.toString() + "...");
    }

    public void execute(List<Fact> variables) throws Exception {

        // Mark the start time
        LocalDateTime startedOn = LocalDateTime.now();

        // Grab the DMN runtime, model, & context
        DMNRuntime dmnRuntime = KieRuntimeFactory.of(kieContainer.getKieBase()).get(DMNRuntime.class);
        DMNModel dmnModel = dmnRuntime.getModel(this.namespace, this.modelName);
        DMNContext dmnContext = dmnRuntime.newContext();  

        // Add the facts
        Iterator<Fact> iVariables = variables.iterator();

        while(iVariables.hasNext()) {

            Fact variable = (Fact) iVariables.next();
            logger.info("Variable: " + variable);
        }

        // Mark completion time        
        LocalDateTime completedOn = LocalDateTime.now();

        // Report
        ExecutionDuration duration = calculateExecutionDuration(startedOn, completedOn);
        
        // Prepare the execution results
        ExecutionInfo executionInfo = new ExecutionInfo();
        executionInfo.setStartedOn(formatLocalDateTime(startedOn));
        executionInfo.setCompletedOn(formatLocalDateTime(completedOn));
        executionInfo.setExecutionDuration(duration);
        //executionInfo.setFacts(facts);
    }

    public void dispose() throws Exception {
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