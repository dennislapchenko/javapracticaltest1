package com.neoworks.interviewtests.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.neoworks.interviewtests.graph.dao.PersonDAO;
import com.neoworks.interviewtests.graph.dao.PersonRelationshipDAO;
import com.neoworks.interviewtests.graph.model.Person;
import com.neoworks.interviewtests.graph.model.PersonRelationship;
import com.neoworks.interviewtests.graph.model.RelationshipType;
import com.neoworks.interviewtests.graph.model.SociablePerson;

public class GraphApplication {

    private PersonDAO personDAO;
    private PersonRelationshipDAO personRelationshipDAO;

    private Map<String, SociablePerson> sociablePeople;

    public GraphApplication(final PersonDAO personDAO, final PersonRelationshipDAO personRelationshipDAO) {
        this.personDAO = personDAO;
        this.personRelationshipDAO = personRelationshipDAO;
        sociablePeople = Maps.newTreeMap();
    }

    /* Exercise 1 */
    public void loadData() {
        final List<Person> people = personDAO.getPeople();
        final List<PersonRelationship> peopleRelationships = personRelationshipDAO.getPersonRelationships();

        people.forEach(person -> {
            final Set<PersonRelationship> associatedRelationships = peopleRelationships.stream()
                .filter(rel -> rel.getPersonA().equals(person.getEmail()) || rel.getPersonB().equals(person.getEmail()))
                .collect(Collectors.toSet()); //ideally would write a MultimapCollector to avoid explicitly recollecting below

            final Multimap<RelationshipType, String> relationships = ArrayListMultimap.create();
            associatedRelationships.forEach(rel -> relationships.put(
                rel.getType(), rel.getPersonA().equals(person.getEmail()) ? rel.getPersonB() : rel.getPersonA()));

            sociablePeople.put(person.getEmail(), new SociablePerson(person.getName(), person.getEmail(), person.getAge(), relationships));
        });
    }

    /* Exercise 4 */
    public int calculateFamilySize(final SociablePerson person) {
        final Set<String> familyLinks = findAllRelationshipLinks(person, RelationshipType.FAMILY);
        return familyLinks.size();
    }

    //Java version of having a default method argument (other than varargs)
    public Set<String> findAllRelationshipLinks(final SociablePerson person, final RelationshipType type) {
        return findAllRelationshipLinks(person, type, new HashSet<>());
    }

    /**
     * Goes through all relationships of a passed person, adds them to links
     * and any relationship member is not already added recursively checks whether there are any new members
     */
    private Set<String> findAllRelationshipLinks(final SociablePerson person, final RelationshipType type, final Set<String> links) {
        links.add(person.getEmail());

        person.getRelationships().get(type).forEach(member -> {
            links.add(member);
            sociablePeople.get(member).getRelationships().get(type).forEach(submember -> {
                if (!links.contains(submember)) {
                    findAllRelationshipLinks(sociablePeople.get(submember), type, links);
                }
            });
        });

        return links;
    }

    public Map<String, SociablePerson> getSociablePeople() {
        return sociablePeople;
    }

}
