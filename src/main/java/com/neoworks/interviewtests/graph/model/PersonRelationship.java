package com.neoworks.interviewtests.graph.model;

public class PersonRelationship {

    private String personA;
    private String personB;
    private RelationshipType type;

    /**
     * no builder since all fields are mandatory
     * immutable
     */
    public PersonRelationship(final String personA, final RelationshipType type, final String personB) {
        this.personA = personA;
        this.personB = personB;
        this.type = type;
    }

    public String getPersonA() {
        return personA;
    }

    public String getPersonB() {
        return personB;
    }

    public RelationshipType getType() {
        return type;
    }

}
