package com.ibm.techsales.dmoe.sample.model;

public class Applicant {

    private String name;   
    private Integer age;

    public Applicant() {
    }

    public Applicant(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Applicant [name=" + name + ", age=" + age + "]";
    }
}
