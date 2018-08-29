package com.neoworks.interviewtests.graph.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVUtils {

    //Saves code duplication and abstracts implementation as a bonus
    public static List<List<String>> loadHeaderlessRecords(final String path) {
        try (Reader in = new FileReader(path)) {
            final Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

            return StreamSupport.stream(records.spliterator(), false)
                .map(rec -> StreamSupport.stream(rec.spliterator(), false).collect(Collectors.toList()))
                .collect(Collectors.toList());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
