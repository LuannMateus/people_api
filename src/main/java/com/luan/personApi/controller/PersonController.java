package com.luan.personApi.controller;

import com.luan.personApi.dto.MessageResponseDTO;
import com.luan.personApi.entity.Person;
import com.luan.personApi.service.PersonService;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    private PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        final List<Person> allPersons = this.personService.findAll();

        return ResponseEntity.ok().body(allPersons);
    }

    @PostMapping
    public MessageResponseDTO save(@RequestBody Person person) {
        final Person savedPerson = this.personService.save(person);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
