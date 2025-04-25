package network_class.chat_backend.controllers;

import network_class.chat_backend.database.entities.Person;
import network_class.chat_backend.database.entities.PersonRepository;
import network_class.chat_backend.security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) {
        if (personRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        Person newPerson = new Person(
                request.getEmail(),
                request.getUsername(),
                request.getPassword(),
                request.getLastIpKnown()
        );

        personRepository.save(newPerson);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SignupResponse("User created successfully"));
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


    public static class SignupRequest {
        private String email;
        private String username;
        private String password;
        private String lastIpKnown;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getLastIpKnown() { return lastIpKnown; }
        public void setLastIpKnown(String lastIpKnown) { this.lastIpKnown = lastIpKnown; }
    }

    public static class SignupResponse {
        private String message;

        public SignupResponse(String message) {
            this.message = message;
        }

        public String getMessage() { return message; }
    }
}