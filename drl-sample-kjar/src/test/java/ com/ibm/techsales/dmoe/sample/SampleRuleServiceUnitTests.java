package com.ibm.techsales.dmoe.sample;

import org.junit.jupiter.api.Test;

import com.ibm.techsales.dmoe.sample.model.Applicant;
import com.ibm.techsales.dmoe.sample.model.LoanApplication;
import com.ibm.techsales.dmoe.sample.service.SampleRuleService;
import com.ibm.techsales.dmoe.engine.api.ExecutionInfo;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleRuleServiceUnitTests {

    private static final Logger logger = LoggerFactory.getLogger(SampleRuleServiceUnitTests.class);

    @Test
    void processRules() {

        try {

            logger.info("Testing sample rule service...");
            SampleRuleService ruleService = new SampleRuleService();

            // Facts
            List<Object> facts = new ArrayList<Object>();
            facts.add(new LoanApplication("ABC10001", 2000,  100, 5000, new Applicant("Les",   45)));
            facts.add(new LoanApplication("ABC10002", 5000,  100, 5000, new Applicant("Larry", 25)));
            facts.add(new LoanApplication("ABC10015", 1000,  100, 5000, new Applicant("Tim",   12)));

            // Test the pool
            for (int i=0; i<10; i++) {

                ExecutionInfo executionInfo = ruleService.execute(facts);
                logger.info("Rule execution duration: (" + i + ") " + executionInfo);
                logger.info("Rule execution results:  (" + i + ") " + executionInfo.getFacts());
            }

            // Be sure to call dispose, otherwise the engine pooll will not be released and you will get memory leaks    
            ruleService.dispose();
        } catch (Exception e) {
            logger.error("Error executing ruleset: reason=" + e.getMessage());
            e.printStackTrace();
        }
    }
}

