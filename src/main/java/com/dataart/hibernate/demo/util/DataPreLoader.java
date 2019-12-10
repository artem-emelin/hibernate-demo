package com.dataart.hibernate.demo.util;

import com.dataart.hibernate.demo.database.entities.Account;
import com.dataart.hibernate.demo.database.entities.Orders;
import com.dataart.hibernate.demo.database.entities.Person;
import com.dataart.hibernate.demo.database.entities.PersonGender;
import com.dataart.hibernate.demo.database.repository.OrderRepository;
import com.dataart.hibernate.demo.database.repository.PersonRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static com.dataart.hibernate.demo.database.entities.PersonGender.MALE;

@Component
@RequiredArgsConstructor
public class DataPreLoader implements ApplicationRunner {

    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;

    @Override
    public void run(ApplicationArguments args) {
        Person person = createPerson("Jhonny", "Black", MALE);

        person.addAccount(createAccount("My account 1", new BigDecimal("10.00")));
        person.addAccount(createAccount("My account 2", new BigDecimal("20.00")));
        person.addAccount(createAccount("My account 3", new BigDecimal("30.00")));
        person.addAccount(createAccount("My account 4", new BigDecimal("40.00")));
        person.addAccount(createAccount("My account 5", new BigDecimal("50.00")));
        personRepository.save(person);

        // Data for mass
        for (int i = 0; i < 5; i++) {
            fillFakeData();
        }

        orderRepository.save(createOrder("Order #1"));
        orderRepository.save(createOrder("Order #2"));
        orderRepository.save(createOrder("Order #3"));
        orderRepository.save(createOrder("Order #4"));
        orderRepository.save(createOrder("Order #5"));
    }

    private void fillFakeData() {
        Faker faker = new Faker();
        Person person = createPerson(faker.name().firstName(), faker.name().lastName(), MALE);

        IntStream.range(1, 6)
                .mapToObj(ind -> createAccount(faker.code().asin(), new BigDecimal("77.77")))
                .forEach(person::addAccount);

        personRepository.save(person);
    }

    private Account createAccount(String desc, BigDecimal amount) {
        return Account.builder()
                .description(desc)
                .amount(amount)
                .build();
    }

    private Person createPerson(String firstName, String lastName, PersonGender gender) {
        return Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .build();
    }

    private Orders createOrder(String orderName) {
        return Orders.builder()
                .orderName(orderName)
                .build();
    }
}
