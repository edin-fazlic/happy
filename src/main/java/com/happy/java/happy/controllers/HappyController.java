package com.happy.java.happy.controllers;

import com.happy.java.happy.models.dtos.PersonDto;
import com.happy.java.happy.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/happy-people")
@RestController
public class HappyController {

    private final List<PersonDto> people = new ArrayList<>();
    private int counter = 0;

    private PersonService personService;

    public HappyController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/single/{id}")
    public PersonDto getHappyPeople(@PathVariable Integer id) {
        return personService.getHappyPerson(id);
    }

    @GetMapping("/search")
    public PersonDto getHappyPeople(@RequestParam String name) {
        return personService.getHappyPerson(name);
    }

    @GetMapping("/list")
    public List<PersonDto> getDoubleHappyPeople() {
        return personService.getAllPeople();
    }

    @PostMapping
    public PersonDto savePerson(@RequestBody PersonDto person) {
        return personService.savePerson(person);
    }

    @DeleteMapping("/single/{id}")
    public void deletePerson(@PathVariable Integer id) {
        people.removeIf(p -> id != null && id.equals(p.getId()));
    }

    @PutMapping("/single/{id}")
    public PersonDto updatePerson(@PathVariable Integer id,
                                  @RequestBody PersonDto person) {
        PersonDto toUpdate = people.stream()
                .filter(p -> id != null && id.equals(p.getId()))
                .findFirst()
                .orElse(null);
        toUpdate.setName(person.getName());

        return toUpdate;
    }


}
