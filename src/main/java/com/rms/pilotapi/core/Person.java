package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

public class Person {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private int age;
    private DateTime birthDateTime;
    private Address address;

    public Person() {}

    public Person(String name) {
        this.name = name;
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
