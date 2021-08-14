package com.luan.personApi.controller;

import com.luan.personApi.dto.MessageResponseDTO;
import com.luan.personApi.dto.entityDTO.PersonDTO;
import com.luan.personApi.entity.Person;
import com.luan.personApi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    private PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> allPersonsDTO = this.personService.findAll();

        return ResponseEntity.ok().body(allPersonsDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO save(@RequestBody @Valid PersonDTO personDTO) {

        return this.personService.save(personDTO);
    }
}
