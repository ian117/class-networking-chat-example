package network_class.chat_backend.controllers;

import network_class.chat_backend.database.entities.Person;
import network_class.chat_backend.database.entities.PersonRepository;
import network_class.chat_backend.security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonRepository personRepository;
    private final JwtUtil jwtUtil;

    public AuthController(PersonRepository personRepository, JwtUtil jwtUtil) {
        this.personRepository = personRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Optional<Person> person = personRepository.findByEmail(request.getEmail());

        if (person.isPresent() && person.get().getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(person.get().getUsername());
            return new LoginResponse(token);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    // Clases internas DTO
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}