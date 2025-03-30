package com.ibm.techsales.dmoe.sample.model;

public class Applicant {

    private String id;   
    private int age;

    public Applicant() {
    }

    public Applicant(String id, int age) {

        this.id = id;
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Applicant [id=" + id + ", age=" + age + "]";
    }
}
