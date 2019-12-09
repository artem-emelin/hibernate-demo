package com.dataart.hibernate.demo.database.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Getter
@RequiredArgsConstructor
public enum PersonGender {

    MALE("M"),
    FEMALE("F");

    private final static Map<String, PersonGender> CONSTANTS = new HashMap<>();
    static {
        for (PersonGender c : values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private final String value;

    public static PersonGender fromValue(String value) {
        PersonGender constant = CONSTANTS.get(value);
        if (nonNull(constant)) {
            return constant;
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
