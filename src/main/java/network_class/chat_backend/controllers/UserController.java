package network_class.chat_backend.controllers;

import network_class.chat_backend.database.entities.Person;
import network_class.chat_backend.database.entities.PersonRepository;
import network_class.chat_backend.dtos.UserSummaryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final PersonRepository personRepository;

    public UserController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<UserSummaryDTO> getAllUsers() {
        List<Person> persons = personRepository.findAll();

        return persons.stream()
                .map(person -> new UserSummaryDTO(
                        person.getId(),
                        person.getUsername(),
                        person.getLastIpKnown()
                ))
                .collect(Collectors.toList());
    }
}