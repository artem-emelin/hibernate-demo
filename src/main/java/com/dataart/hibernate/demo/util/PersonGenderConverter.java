package com.dataart.hibernate.demo.util;

import com.dataart.hibernate.demo.database.entities.PersonGender;

import javax.persistence.AttributeConverter;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

public class PersonGenderConverter implements AttributeConverter<PersonGender, String> {

    @Override
    public String convertToDatabaseColumn(PersonGender attribute) {
        if (isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public PersonGender convertToEntityAttribute(String dbData) {
        if (!hasText(dbData)) {
            return null;
        }
        return PersonGender.fromValue(dbData);
    }
}
