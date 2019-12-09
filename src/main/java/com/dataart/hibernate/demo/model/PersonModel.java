package com.dataart.hibernate.demo.model;

import com.dataart.hibernate.demo.database.entities.PersonGender;
import com.dataart.hibernate.demo.database.entities.PersonStatus;
import com.dataart.hibernate.demo.util.PersonStatusConverter;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.math.BigDecimal;

@Data
public class PersonModel {

    private long id;
    private String firstName;
    private String lastName;
    private PersonGender gender;
    private PersonStatus status;
}
