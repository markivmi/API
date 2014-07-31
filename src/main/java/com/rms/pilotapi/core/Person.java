package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

public class Person {
    @NotEmpty
    private String name;

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    private int age;

    @JsonProperty
    public int getAge() {
        return age;
    }

    @JsonProperty
    public void setAge(int age) {
        this.age = age;
    }

    private DateTime birthDateTime;

    @JsonProperty
    public DateTime getBirthDateTime() {
        return birthDateTime;
    }

    @JsonProperty
    public void setBirthDateTime(DateTime birthDateTime) {
        this.birthDateTime = birthDateTime;
    }

    private Address address;

    @JsonProperty
    public Address getAddress() {
        return address;
    }

    @JsonProperty
    public void setAddress(Address address) {
        this.address = address;
    }
}
