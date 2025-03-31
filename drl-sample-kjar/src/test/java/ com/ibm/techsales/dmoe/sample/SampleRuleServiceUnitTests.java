package com.ibm.techsales.dmoe.sample;

import org.junit.jupiter.api.Test;

import com.ibm.techsales.dmoe.sample.model.Applicant;
import com.ibm.techsales.dmoe.sample.model.LoanApplication;

import com.ibm.techsales.dmoe.engine.api.RuleEngineAdaptor;
import com.ibm.techsales.dmoe.engine.api.RuleResults;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleRuleServiceUnitTests {

    private static final Logger logger = LoggerFactory.getLogger(SampleRuleServiceUnitTests.class);

    private static final String PROPERTY_KIE_SESSION_NAME       = "kie.session.name";
    private static final String PROPERTY_KIE_SESSION_POOL_SIZE  = "kie.session.poolsize";
    private static final String PROPERTY_KJAR_GROUP_ID          = "kjar.groupId";
    private static final String PROPERTY_KJAR_ARTIFACT_ID       = "kjar.artifactId";
    private static final String PROPERTY_KJAR_VERSION           = "kjar.version";

    @Test
    void processRules() {

        try {

            // Load the application properties
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/application.properties");
            properties.load(input);

            // Create and register an adaptor
            RuleEngineAdaptor ruleEngineAdaptor = new RuleEngineAdaptor();
            ruleEngineAdaptor.register(properties.getProperty(PROPERTY_KIE_SESSION_NAME), Integer.parseInt(properties.getProperty(PROPERTY_KIE_SESSION_POOL_SIZE)), properties.getProperty(PROPERTY_KJAR_GROUP_ID), properties.getProperty(PROPERTY_KJAR_ARTIFACT_ID), properties.getProperty(PROPERTY_KJAR_VERSION));

            // Facts
            List<Object> facts = new ArrayList<Object>();
            facts.add(new LoanApplication("ABC10001", 1200,  400, 5000, new Applicant("Les", 25)));
            facts.add(new LoanApplication("ABC10002", 5000,  100, 5000, new Applicant("Larry", 25)));
            facts.add(new LoanApplication("ABC10015", 1000,  100, 5000, new Applicant("Tim",   12)));

            // Test the pool
            for (int i=0; i<10; i++) {

                RuleResults results = ruleEngineAdaptor.execute(facts);
                logger.info("Rule execution duration: (" + i + ") " + results);
                logger.info("Rule execution results:  (" + i + ") " + results.getFacts());
            }

            // Be sure to call dispose, otherwise the engine pooll will not be released and you will get memory leaks    
            ruleEngineAdaptor.dispose();
        } catch (Exception e) {

            logger.error("Error executing ruleset: reason=" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void processRulesViaNamedFacts() {

        try {

            // Load the application properties
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/application.properties");
            properties.load(input);

            // Create and register an adaptor
            RuleEngineAdaptor ruleEngineAdaptor = new RuleEngineAdaptor();
            ruleEngineAdaptor.register(properties.getProperty(PROPERTY_KIE_SESSION_NAME), Integer.parseInt(properties.getProperty(PROPERTY_KIE_SESSION_POOL_SIZE)), properties.getProperty(PROPERTY_KJAR_GROUP_ID), properties.getProperty(PROPERTY_KJAR_ARTIFACT_ID), properties.getProperty(PROPERTY_KJAR_VERSION));

            // Facts
            Map<String, Object> facts = new HashMap<String, Object>();
            facts.put("app1", new LoanApplication("ABC10001", 1200,  400, 5000, new Applicant("Les", 25)));
            facts.put("app2", new LoanApplication("ABC10002", 5000,  100, 5000, new Applicant("Larry", 25)));
            facts.put("app3", new LoanApplication("ABC10015", 1000,  100, 5000, new Applicant("Tim",   12)));

            // Test the pool
            for (int i=0; i<10; i++) {

                RuleResults results = ruleEngineAdaptor.execute(facts);
                logger.info("Rule execution duration: (" + i + ") " + results);
                logger.info("Rule execution results:  (" + i + ") " + results.getFacts());
            }

            // Be sure to call dispose, otherwise the engine pooll will not be released and you will get memory leaks    
            ruleEngineAdaptor.dispose();
        } catch (Exception e) {

            logger.error("Error executing ruleset: reason=" + e.getMessage());
            e.printStackTrace();
        }
    }
}

