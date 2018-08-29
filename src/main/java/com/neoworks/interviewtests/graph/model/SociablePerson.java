package com.neoworks.interviewtests.graph.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Not inhereting from Person since this class is 'business class', separate from data reading pojo
 * immutable
 */
public class SociablePerson {

    private String name;
    private String email;
    private int age;
    private Multimap<RelationshipType, String> relationships;

    //for those who aren't really sociable :(
    public SociablePerson(final String name, final String email, final int age) {
        this(name, email, age, ArrayListMultimap.create());
    }

    public SociablePerson(final String name, final String email, final int age, final Multimap<RelationshipType, String> relationships) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.relationships = relationships;
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

    public Multimap<RelationshipType, String> getRelationships() {
        return relationships;
    }
}
