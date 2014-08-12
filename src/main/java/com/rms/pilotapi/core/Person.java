package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.mongojack.Id;

import javax.validation.constraints.NotNull;

public class Person {
    @Id
    private long id;

    @NotEmpty
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (id != person.id) return false;
        if (!address.equals(person.address)) return false;
        if (!birthDateTime.equals(person.birthDateTime)) return false;
        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + birthDateTime.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    @NotNull
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
