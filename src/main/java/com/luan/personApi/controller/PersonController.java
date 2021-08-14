package com.luan.personApi.controller;

import com.luan.personApi.dto.MessageResponseDTO;
import com.luan.personApi.dto.entityDTO.PersonDTO;
import com.luan.personApi.entity.Person;
import com.luan.personApi.error.PersonNotFoundException;
import com.luan.personApi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> allPersonsDTO = this.personService.findAll();

        return ResponseEntity.ok().body(allPersonsDTO);
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return this.personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO save(@RequestBody @Valid PersonDTO personDTO) {

        return this.personService.save(personDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO)
            throws PersonNotFoundException {

        return this.personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        this.personService.deleteById(id);
    }
}
