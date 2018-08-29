package com.neoworks.interviewtests.graph.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.neoworks.interviewtests.graph.model.PersonRelationship;
import com.neoworks.interviewtests.graph.model.RelationshipType;
import com.neoworks.interviewtests.graph.util.CSVUtils;

public class CSVPersonRelationshipDAO implements PersonRelationshipDAO {

    private String dataPath;

    public CSVPersonRelationshipDAO() {};

    public CSVPersonRelationshipDAO(final String dataPath) {
        this.dataPath = dataPath;
    }

    @Override
    public List<PersonRelationship> getPersonRelationships() {
        final List<List<String>> data = CSVUtils.loadHeaderlessRecords(dataPath);

        return data.stream()
            .flatMap(row -> row.size() > 2 ? Stream.of(new PersonRelationship(row.get(0), RelationshipType.valueOf(row.get(1)), row.get(2))) //not checking enum exception as per readme
                                           : Stream.empty())
            .collect(Collectors.toList());
    }

    public void setDataPath(final String dataPath) {
        this.dataPath = dataPath;
    }

}
