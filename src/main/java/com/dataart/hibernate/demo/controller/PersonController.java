package com.dataart.hibernate.demo.controller;

import com.dataart.hibernate.demo.model.PersonModel;
import com.dataart.hibernate.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{personId}")
    public PersonModel getPerson(@PathVariable("personId") long personId) {
        return personService.getPerson(personId);
    }

    @PutMapping("/{personId}")
    public PersonModel updatePerson(@PathVariable("personId") long personId, @RequestBody PersonModel personModel) {
        return personService.updatePerson(personId, personModel);
    }

    @PutMapping("/{personId}/random-account")
    public void addRandomAccount(@PathVariable("personId") long personId) {
        personService.addRandomAccount(personId);
    }

    @GetMapping("/nOne")
    public void nOne() {
        personService.nOne();
    }
}
