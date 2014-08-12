package com.rms.pilotapi.dao;

import com.rms.pilotapi.core.Person;

public interface PersonDao {
    Person getPerson(long id);

    Person createPerson(Person person);

    Person updatePerson(long id, Person person);

    boolean deletePerson(long id);
}
