package com.happy.java.happy.services;

import com.happy.java.happy.models.dtos.PersonDto;
import com.happy.java.happy.models.entities.Person;
import com.happy.java.happy.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final List<PersonDto> people = new ArrayList<>();

    private long counter = 0;

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
        people.add(new PersonDto(counter++, "Adna"));
        people.add(new PersonDto(counter++, "Nedzad"));
        people.add(new PersonDto(counter++, "Dino"));
        people.add(new PersonDto(counter++, "Zeljo"));
        people.add(new PersonDto(counter++, "Pavel*Pavel"));
    }

    public PersonDto getHappyPerson(Integer id) {
        return people.stream()
                .filter(p -> id != null && id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

    public PersonDto getHappyPerson(String name) {
        if (name == null) {
            return null;
        }
        return people.stream()
                .filter(p -> p.getName() != null
                        && p.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElse(null);

    }

    public PersonDto savePerson(PersonDto personToUse) {
        Person person = new Person();
        person.setFirstName(personToUse.getName());

        person = personRepository.save(person);

        return new PersonDto(person.getId(), personToUse.getName());
    }

    public PersonDto updatePerson(Long id, PersonDto personToUse) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName(personToUse.getName());

        person = personRepository.save(person);

        return new PersonDto(person.getId(), personToUse.getName());
    }

    public List<PersonDto> getAllPeople() {
        List<Person> all = personRepository.findAll();
        List<PersonDto> result = new ArrayList<>();
        for (Person person : all) {
            PersonDto dto = new PersonDto(person.getId(), person.getFirstName());
            result.add(dto);
        }
        return result;
    }

}






