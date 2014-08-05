package com.rms.pilotapi.dao;

import com.rms.pilotapi.core.Person;

public interface PersonDao {
    Person getPerson(Integer id);

    Person createPerson(Person person);

    Person updatePerson(Integer id, Person person);

    boolean deletePerson(Integer id);
}
