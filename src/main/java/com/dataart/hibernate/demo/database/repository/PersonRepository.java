package com.dataart.hibernate.demo.database.repository;

import com.dataart.hibernate.demo.database.entities.Person;
import com.dataart.hibernate.demo.database.repository.custom.PersonRepositoryCustom;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PersonRepository extends Repository<Person, Long>, PersonRepositoryCustom {

    @Transactional
    Person save(Person person);

    List<Person> findAll();

}
