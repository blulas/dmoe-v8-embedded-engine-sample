package com.ibm.techsales.dmoe.engine.api;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.builder.ReleaseId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedEngineAdaptor {
    
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedEngineAdaptor.class);
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private KieServices kieServices;
    private KieContainer kieContainer;
    private ReleaseId releaseId;

    public EmbeddedEngineAdaptor() {
    }

    public KieServices getKieServices() {
        return this.kieServices;
    }

    public void setKieServices(KieServices kieServices) {
        this.kieServices = kieServices;
    }

    public KieContainer getKieContainer() {
        return this.kieContainer;
    }

    public void setKieContainer(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public ReleaseId getReleaseId() {
        return this.releaseId;
    }

    public void setReleaseId(ReleaseId releaseId) {
        this.releaseId = releaseId;
    }

    public void dispose() throws Exception {
    }

    protected ExecutionDuration calculateExecutionDuration(LocalDateTime begin, LocalDateTime end) {

        ExecutionDuration ed = new ExecutionDuration();
        ed.setDays(ChronoUnit.DAYS.between(begin, end));
        ed.setHours(ChronoUnit.HOURS.between(begin, end));
        ed.setMinutes(ChronoUnit.MINUTES.between(begin, end));
        ed.setSeconds(ChronoUnit.SECONDS.between(begin, end));
        ed.setMilliseconds(ChronoUnit.MILLIS.between(begin, end));
        return ed;
   }

   protected String formatLocalDateTime(LocalDateTime ldt) {

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
       return ldt.format(formatter);
   }
}