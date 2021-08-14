package com.luan.personApi.service;

import com.luan.personApi.dto.MessageResponseDTO;
import com.luan.personApi.dto.entityDTO.PersonDTO;
import com.luan.personApi.entity.Person;
import com.luan.personApi.error.PersonNotFoundException;
import com.luan.personApi.mapper.PersonMapper;
import com.luan.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll() {
        List<Person> allPersons = this.personRepository.findAll();

        return allPersons.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(person);
    }

    public MessageResponseDTO save(PersonDTO personDTO) {
        Person personEntity = personMapper.toModel(personDTO);

        Person savedPerson = this.personRepository.save(personEntity);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }


}
