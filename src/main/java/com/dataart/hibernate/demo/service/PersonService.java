package com.dataart.hibernate.demo.service;

import com.dataart.hibernate.demo.database.entities.Account;
import com.dataart.hibernate.demo.database.entities.Orders;
import com.dataart.hibernate.demo.database.entities.Person;
import com.dataart.hibernate.demo.database.repository.OrderRepository;
import com.dataart.hibernate.demo.database.repository.PersonRepository;
import com.dataart.hibernate.demo.model.PersonModel;
import com.dataart.hibernate.demo.util.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    //@Transactional -- remove before DEMO !!!!
    public PersonModel getPerson(long personId) {
        LOG.info(">>>>>>>>>> Loading Person by Id {}", personId);
        Person person = personRepository.findById(personId);
        LOG.info(">>>>>>>>>> Iterating by Person accounts");
        person.getAccounts().forEach(System.out::println);
        LOG.info(">>>>>>>>>> Iteration complete");
        return personMapper.toPersonModel(person);
    }

    @Transactional
    public void addRandomAccount(long personId) {
        Person person = personRepository.findById(personId);
        person.addAccount(createAccount("Random Account", BigDecimal.TEN));
    }

    @Transactional
    public void nOne() {
        LOG.info(">>>>>>>>>> Loading All Persons");
        List<Person> allPersons = personRepository.findAll();
        LOG.info(">>>>>>>>>> Iterating Persons");
        for (Person person : allPersons) {
            person.getAccounts().size();
        }
    }

    @Transactional
    public PersonModel updatePerson(long personId, PersonModel personModel) {
        Person repositoryPerson = personRepository.findById(personId);
        personMapper.update(repositoryPerson, personModel);
        repositoryPerson.setCreated(LocalDateTime.now());

        // Example for Dirty Checking
        Orders order = orderRepository.findById(1);
        //Orders order = orderRepository.findByIdReadOnly(1);

        return personMapper.toPersonModel(repositoryPerson);
    }

    private Account createAccount(String desc, BigDecimal amount) {
        return Account.builder()
                .description(desc)
                .amount(amount)
                .build();
    }
}
