package com.ibm.techsales.dmoe.sample;

import org.junit.jupiter.api.Test;

import com.ibm.techsales.dmoe.sample.model.Applicant;
import com.ibm.techsales.dmoe.sample.model.LoanApplication;
import com.ibm.techsales.dmoe.sample.service.SampleRuleService;
import com.ibm.techsales.dmoe.engine.api.ExecutionInfo;
import com.ibm.techsales.dmoe.engine.api.Fact;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleDecisionServiceUnitTests {

    private static final Logger logger = LoggerFactory.getLogger(SampleDecisionServiceUnitTests.class);

    @Test
    void processRules() {

        try {

            logger.info("Testing sample decision service...");
            SampleDecisionService decisionService = new SampleDecisionService();

            // Facts
            List<Fact> variables = new ArrayList<Fact>();
            variables.add("Les",   new LoanApplication("ABC10001", 2000,  100, 5000, new Applicant("Les",   45)));
            variables.add("Larry", new LoanApplication("ABC10002", 5000,  100, 5000, new Applicant("Larry", 25)));
            variables.add("Tim",   new LoanApplication("ABC10015", 1000,  100, 5000, new Applicant("Tim",   12)));

            // Test the pool
            for (int i=0; i<10; i++) {

                ExecutionInfo executionInfo = decisionService.execute(facts);
                logger.info("Decision execution duration: (" + i + ") " + executionInfo);
                logger.info("Decision execution results:  (" + i + ") " + executionInfo.getVariables());
            }

            // Be sure to call dispose, otherwise the engine pooll will not be released and you will get memory leaks    
            decisionService.dispose();
        } catch (Exception e) {
            logger.error("Error executing ruleset: reason=" + e.getMessage());
            e.printStackTrace();
        }
    }
}

