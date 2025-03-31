package com.ibm.techsales.dmoe.engine.api;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieRuntimeFactory;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.api.core.DMNMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecisionEngineAdaptor extends EmbeddedEngineAdaptor {
    
    private static final Logger logger = LoggerFactory.getLogger(DecisionEngineAdaptor.class);

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

        this.namespace = namespace;
        this.modelName = modelName;

        setKieServices(KieServices.Factory.get());
        setReleaseId(getKieServices().newReleaseId(groupId, artifactId, version));
        setKieContainer(getKieServices().newKieContainer(getReleaseId()));

        logger.info("Registered decision engine: namespace=" + namespace + ", modelName=" + modelName + ", KJAR=" + getReleaseId().toString() + "...");
    }

    public DecisionResults execute(Map<String, Object> facts) throws Exception {

        // Mark the start time
        LocalDateTime startedOn = LocalDateTime.now();

        // Grab the DMN runtime, model, & context
        DMNRuntime dmnRuntime = KieRuntimeFactory.of(getKieContainer().getKieBase()).get(DMNRuntime.class);
        DMNModel dmnModel = dmnRuntime.getModel(this.namespace, this.modelName);
        DMNContext dmnContext = dmnRuntime.newContext();  
        DecisionResults results = new DecisionResults();

        // Add the facts
        for (Map.Entry<String, Object> entry : facts.entrySet()) {
            dmnContext.set(entry.getKey(), entry.getValue());
        }

        // Execute the model
        DMNResult dmnResults = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        for (DMNDecisionResult dmnResult : dmnResults.getDecisionResults()) {

            DecisionResult result = new DecisionResult();
            result.setDecisionId(dmnResult.getDecisionId());
            result.setDecisionName(dmnResult.getDecisionName());
            result.setStatus(dmnResult.getEvaluationStatus().toString());
            result.getResults().add(dmnResult.getResult());

            //  Translate all the messages
            for (DMNMessage msg : dmnResult.getMessages()) {
                result.getMessages().add(msg.toString());
            }

            results.getDecisions().add(result);
        }

        // Mark completion time        
        LocalDateTime completedOn = LocalDateTime.now();

        // Report
        ExecutionDuration duration = calculateExecutionDuration(startedOn, completedOn);
        
        // Execution results
        results.setStartedOn(formatLocalDateTime(startedOn));
        results.setCompletedOn(formatLocalDateTime(completedOn));
        results.setExecutionDuration(duration);

        return results;
    }
}