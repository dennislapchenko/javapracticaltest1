package com.neoworks.interviewtests.graph;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import com.neoworks.interviewtests.graph.model.SociablePerson;

public class GraphApplicationTest {

    private static GraphApplication app;
    private static Collection<SociablePerson> people;

    @BeforeClass
    public static void beforeClass() {
        app = Main.getWiredGraphApplication();
        app.loadData();

        people = app.getSociablePeople().values();
    }

    /* Exercise 2 */
    @Test
    public void testLoadedPeopleCount() {
        assertEquals(12, people.size());
    }

    /* Exercise 3 */
    @Test
    public void testParsedRelationships() {
        assertEquals(4, getRelationshipsSize("Bob"));
        assertEquals(3, getRelationshipsSize("Jenny"));
        assertEquals(2, getRelationshipsSize("Nigel"));
        assertEquals(0, getRelationshipsSize("Alan"));
    }

    /* Exercise 4 */
    @Test
    public void testExtendedFamilySizeCalculation() {
        assertEquals(4, app.calculateFamilySize(getPersonByName("Jenny")));
        assertEquals(4, app.calculateFamilySize(getPersonByName("Bob")));
    }

    private int getRelationshipsSize(final String name) {
        return getPersonByName(name).getRelationships().size();
    }

    //kept key of app.sociablePeople as email to be more realistic. getting objects by name since readme has names
    private SociablePerson getPersonByName(final String name) {
        return people.stream()
            .filter(p -> p.getName().equalsIgnoreCase(name))
            .findFirst().orElse(null);
    }

}
