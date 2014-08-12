package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

public class Person {
    private int id;
    @NotEmpty
    private String name;
    @NotNull
    private int age;
    private DateTime birthDateTime;
    private Address address;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (id != person.id) return false;
        if (address != null ? !address.equals(person.address) : person.address != null) return false;
        if (birthDateTime != null ? !birthDateTime.equals(person.birthDateTime) : person.birthDateTime != null)
            return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (birthDateTime != null ? birthDateTime.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @JsonProperty

    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public int getAge() {
        return age;
    }

    @JsonProperty
    public void setAge(int age) {
        this.age = age;
    }

    @JsonProperty
    public DateTime getBirthDateTime() {
        return birthDateTime;
    }

    @JsonProperty
    public void setBirthDateTime(DateTime birthDateTime) {
        this.birthDateTime = birthDateTime;
    }

    @JsonProperty
    public Address getAddress() {
        return address;
    }

    @JsonProperty
    public void setAddress(Address address) {
        this.address = address;
    }
}
