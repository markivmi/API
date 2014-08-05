package com.rms.pilotapi.dao;

import com.google.common.base.Optional;
import com.rms.pilotapi.core.Person;

public interface PersonDao {

    Person getPerson(Optional<String> name);

    Person createPerson(Person person);

    Person updatePerson(Person person);

    boolean deletePerson(Person person);
}
