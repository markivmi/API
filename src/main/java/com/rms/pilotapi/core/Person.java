package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.mongojack.Id;

public class Person {
    @Id
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private int age;

    private DateTime birthDateTime;

    private Address address;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Id
    @JsonProperty
    public long getId() {
        return this.id;
    }

    @Id
    @JsonProperty
    public void setId(long id) {
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
    @JsonSerialize(using = DateTimeSerializer.class)
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
