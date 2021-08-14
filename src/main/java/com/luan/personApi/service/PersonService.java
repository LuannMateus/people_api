package com.luan.personApi.service;

import com.luan.personApi.dto.MessageResponseDTO;
import com.luan.personApi.dto.entityDTO.PersonDTO;
import com.luan.personApi.entity.Person;
import com.luan.personApi.error.PersonNotFoundException;
import com.luan.personApi.mapper.PersonMapper;
import com.luan.personApi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

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

        return this.createMessageResponse(savedPerson.getId(), "Save person with ID ");
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        this.verifyIfExists(id);

        Person personEntity = personMapper.toModel(personDTO);

        Person updatedPerson = this.personRepository.save(personEntity);

        return this.createMessageResponse(id, "Updated person with ID ");
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        this.verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private void verifyIfExists(Long id) throws PersonNotFoundException {
        this.personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
