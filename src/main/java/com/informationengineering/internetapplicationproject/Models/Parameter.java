package com.informationengineering.internetapplicationproject.Models;

import javax.persistence.*;

@Entity
@Table(name = "PARAMETERS")
public class Parameter {

    @Id
    @Column(name = "key_name")
    String key;

    @Column(name = "value")
    String value;

    public Parameter() {
    }

    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
