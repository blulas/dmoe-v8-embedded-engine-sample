package com.ibm.techsales.dmoe.sample.model;

public class Driver {

    private String name;   
    private int age;
    private String city;
    private String state;
    private int points;

    public Driver() {
    }

    public Driver(String name, int age, String state, String city, int points) {

        this.name = name;
        this.age = age;
        this.state = state;
        this.city = city;
        this.points = points;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Driver [name=" + name + ", age=" + age + ", points=" + points + "]";
    }
}
