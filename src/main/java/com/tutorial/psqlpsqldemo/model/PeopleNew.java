package com.tutorial.psqlpsqldemo.model;

import java.sql.Timestamp;

public class PeopleNew {

    private String name;
    private String gender;
    private int age;
    private Timestamp query_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getQuery_time() {
        return query_time;
    }

    public void setQuery_time(Timestamp query_time) {
        this.query_time = query_time;
    }
}
