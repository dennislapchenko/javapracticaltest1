package com.neoworks.interviewtests.graph.model;

public class Person {

    private String name;
    private String email;
    private int age;

    /**
     * no builder since all fields are mandatory
     * immutable
     */
    public Person(final String name, final String email, final int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

}
