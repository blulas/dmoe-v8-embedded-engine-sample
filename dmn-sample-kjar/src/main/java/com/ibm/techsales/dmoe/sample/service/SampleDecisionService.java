package com.ibm.techsales.dmoe.sample.service;

import com.ibm.techsales.dmoe.engine.api.DecisionEngineAdaptor;

public class SampleDecisionService extends DecisionEngineAdaptor {

    private static final String DMN_NAMESPACE         = "NAMESPACE";
    private static final String DMN_MODEL_NAME        = "MODEL_NAME";
    private static final String KJAR_GROUP_ID         = "com.ibm.techsales.dmoe.samples";
    private static final String KJAR_ARTIFACT_ID      = "dmoe-v8-embedded-dmn-sample-kjar";
    private static final String KJAR_VERSION          = "1.0.0-SNAPSHOT";

    public SampleDecisionService() {
        super(DMN_NAMESPACE, DMN_MODEL_NAME, KJAR_GROUP_ID, KJAR_ARTIFACT_ID, KJAR_VERSION);
    }
}
