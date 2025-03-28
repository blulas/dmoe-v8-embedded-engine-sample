package com.ibm.techsales.dmoe.engine.api;

import java.util.List;

public class Fact {

    private String name;
    private Object value;
    private FactType type = FactType.INOUT;

    public Fact(String name, Object value, FactType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public FactType getType() {
        return this.type;
    }

    public void setType(FactType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "name=" + name + ", value=" + value + ", type=" + type;
    }
}