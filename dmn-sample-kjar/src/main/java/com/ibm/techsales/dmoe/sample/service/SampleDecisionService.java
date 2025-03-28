package com.ibm.techsales.dmoe.sample.service;

import com.ibm.techsales.dmoe.engine.api.DecisionEngineAdaptor;

public class SampleDecisionService extends DecisionEngineAdaptor {

    private static final String DMN_NAMESPACE         = "_1C792953-80DB-4B32-99EB-25FBE32BAF9E";
    private static final String DMN_MODEL_NAME        = "_1C792953-80DB-4B32-99EB-25FBE32BAF9E";
    private static final String KJAR_GROUP_ID         = "com.ibm.techsales.dmoe.v8.samples";
    private static final String KJAR_ARTIFACT_ID      = "dmoe-v8-embedded-dmn-sample-kjar";
    private static final String KJAR_VERSION          = "8.0.6";

    public SampleDecisionService() {
        super(DMN_NAMESPACE, DMN_MODEL_NAME, KJAR_GROUP_ID, KJAR_ARTIFACT_ID, KJAR_VERSION);
    }
}
