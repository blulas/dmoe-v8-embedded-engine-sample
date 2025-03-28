package com.ibm.techsales.dmoe.sample.service;

import com.ibm.techsales.dmoe.engine.api.RuleEngineAdaptor;

public class SampleRuleService extends RuleEngineAdaptor {

    private static final String KIE_SESSION_NAME      = "LoanApplication";
    private static final int    KIE_SESSION_POOL_SIZE = 100;
    private static final String KJAR_GROUP_ID         = "com.ibm.techsales.dmoe.v8.samples";
    private static final String KJAR_ARTIFACT_ID      = "dmoe-v8-embedded-drl-sample-kjar";
    private static final String KJAR_VERSION          = "8.0.6";

    public SampleRuleService() {
        super(KIE_SESSION_NAME, KIE_SESSION_POOL_SIZE, KJAR_GROUP_ID, KJAR_ARTIFACT_ID, KJAR_VERSION);
    }
}
