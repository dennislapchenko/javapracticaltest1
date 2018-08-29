package com.neoworks.interviewtests.graph.dao;

import java.util.List;

import com.neoworks.interviewtests.graph.model.PersonRelationship;

public interface PersonRelationshipDAO {
    List<PersonRelationship> getPersonRelationships();
}
