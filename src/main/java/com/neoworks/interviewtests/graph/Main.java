package com.neoworks.interviewtests.graph;

import com.google.common.io.Resources;
import com.neoworks.interviewtests.graph.dao.CSVPersonDAO;
import com.neoworks.interviewtests.graph.dao.CSVPersonRelationshipDAO;

public class Main {

    //could add these to properties, but that won't prove anything
    private static final String PEOPLE_FILE_NAME = "people.csv";
    private static final String RELATIONSHIPS_FILE_NAME = "relationships.csv";

    public static void main(final String[] args) {
        final GraphApplication app = getWiredGraphApplication();
        app.loadData();
    }

    //could also bind these dependencies through Guice
    public static GraphApplication getWiredGraphApplication() {
        return new GraphApplication(
            new CSVPersonDAO(Resources.getResource(PEOPLE_FILE_NAME).getFile()),
            new CSVPersonRelationshipDAO(Resources.getResource(RELATIONSHIPS_FILE_NAME).getFile()));
    }

}
