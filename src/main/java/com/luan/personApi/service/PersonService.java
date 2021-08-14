package com.luan.personApi.service;

import com.luan.personApi.entity.Person;
import com.luan.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }


}
