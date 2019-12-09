package com.dataart.hibernate.demo.util;

import com.dataart.hibernate.demo.database.entities.PersonStatus;

import javax.persistence.AttributeConverter;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

public class PersonStatusConverter implements AttributeConverter<PersonStatus, String> {

    @Override
    public String convertToDatabaseColumn(PersonStatus attribute) {
        if (isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public PersonStatus convertToEntityAttribute(String dbData) {
        if (!hasText(dbData)) {
            return null;
        }
        return PersonStatus.fromValue(dbData);
    }
}
