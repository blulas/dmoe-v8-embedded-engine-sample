package com.ibm.techsales.dmoe.sample.model;

public class Violation {

    private String code;
    private String date;
    private String type;
    private int speedLimit;
    private int actualSpeed;

    public Violation() {

    }

    public Violation(String code, String date, String type, int speedLimit, int actualSpeed) {

        this.code = code;
        this.date = date;
        this.type = type;
        this.speedLimit = speedLimit;
        this.actualSpeed = actualSpeed;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeedLimit() {
        return this.speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getActualSpeed() {
        return this.actualSpeed;
    }

    public void setActualSpeed(int actualSpeed) {
        this.actualSpeed = actualSpeed;
    }

    @Override
    public String toString() {
        return "Violation [code=" + code + ", date=" + date + ", type=" + type + ", speedLimit=" + speedLimit + ", actualSpeed=" + actualSpeed + "]";
    }
}
