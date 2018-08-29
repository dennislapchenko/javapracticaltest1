package com.neoworks.interviewtests.graph.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.neoworks.interviewtests.graph.model.Person;
import com.neoworks.interviewtests.graph.util.CSVUtils;

public class CSVPersonDAO implements PersonDAO {

    private String dataPath;

    public CSVPersonDAO() {};

    public CSVPersonDAO(final String dataPath) {
        this.dataPath = dataPath;
    }

    @Override
    public List<Person> getPeople() {
        final List<List<String>> data = CSVUtils.loadHeaderlessRecords(dataPath);

        return data.stream()
            .flatMap(row -> row.size() > 2 ? Stream.of(new Person(row.get(0), row.get(1), Integer.parseInt(row.get(2)))) //not checking parse exception as per readme
                                           : Stream.empty())
            .collect(Collectors.toList());
    }

    public void setDataPath(final String dataPath) {
        this.dataPath = dataPath;
    }

}
