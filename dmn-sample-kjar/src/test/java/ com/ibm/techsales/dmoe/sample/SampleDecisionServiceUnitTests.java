package com.ibm.techsales.dmoe.sample;

import org.junit.jupiter.api.Test;

import com.ibm.techsales.dmoe.sample.model.Driver;
import com.ibm.techsales.dmoe.sample.model.Violation;
import com.ibm.techsales.dmoe.sample.model.Fine;

import com.ibm.techsales.dmoe.engine.api.DecisionEngineAdaptor;
import com.ibm.techsales.dmoe.engine.api.DecisionResults;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleDecisionServiceUnitTests {

    private static final Logger logger = LoggerFactory.getLogger(SampleDecisionServiceUnitTests.class);

    private static final String PROPERTY_DMN_NAMESPACE    = "dmn.namespace";
    private static final String PROPERTY_DMN_MODEL_NAME   = "dmn.model";
    private static final String PROPERTY_KJAR_GROUP_ID    = "kjar.groupId";
    private static final String PROPERTY_KJAR_ARTIFACT_ID = "kjar.artifactId";
    private static final String PROPERTY_KJAR_VERSION     = "kjar.version";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Test
    void processDecisions() {

        try {

            // Load the application properties
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/application.properties");
            properties.load(input);

            // Create and register an adaptor
            DecisionEngineAdaptor decisionEngineAdaptor = new DecisionEngineAdaptor();
            decisionEngineAdaptor.register(properties.getProperty(PROPERTY_DMN_NAMESPACE), properties.getProperty(PROPERTY_DMN_MODEL_NAME), properties.getProperty(PROPERTY_KJAR_GROUP_ID), properties.getProperty(PROPERTY_KJAR_ARTIFACT_ID), properties.getProperty(PROPERTY_KJAR_VERSION));

            // Facts
            Map<String, Object> facts = new HashMap<String, Object>();
            facts.put("Driver",    new Driver("Les", 33, "FL", "Wesley Chapel", 20));
            facts.put("Violation", new Violation("ABC123", "2024-10-16", "speed", 100, 120));

            // Test the service
            DecisionResults results = decisionEngineAdaptor.execute(facts);
            logger.info("Decision execution duration: " + results);
            logger.info("Decision results: " + results.getDecisions());

             // Be sure to call dispose, otherwise the engine pooll will not be released and you will get memory leaks    
            decisionEngineAdaptor.dispose();
        } catch (Exception e) {
            logger.error("Error executing ruleset: reason=" + e.getMessage());
            e.printStackTrace();
        }
    }
}

