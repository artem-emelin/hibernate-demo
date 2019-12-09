package com.dataart.hibernate.demo.database.repository.custom;

import com.dataart.hibernate.demo.database.entities.Person;

import java.util.List;

public interface PersonRepositoryCustom {

    void saveCustom(Person person);

    Person findById(Long id);

    Person findByName(String firstName);

    List<Person> findAllWithGraph();
}
