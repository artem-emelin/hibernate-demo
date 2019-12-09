package com.dataart.hibernate.demo.database.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Getter
@RequiredArgsConstructor
public enum PersonStatus {

    ACTIVE("A"),
    INACTIVE("I");

    private final static Map<String, PersonStatus> CONSTANTS = new HashMap<>();
    static {
        for (PersonStatus c : values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private final String value;

    public static PersonStatus fromValue(String value) {
        PersonStatus constant = CONSTANTS.get(value);
        if (nonNull(constant)) {
            return constant;
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
