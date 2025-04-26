package network_class.chat_backend.controllers;

import network_class.chat_backend.database.entities.Person;
import network_class.chat_backend.database.entities.PersonRepository;
import network_class.chat_backend.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        Optional<Person> personOptional = personRepository.findByEmail(request.getEmail());
    
        if (personOptional.isPresent() && personOptional.get().getPassword().equals(request.getPassword())) {
            Person person = personOptional.get();
    
            // 🔥 Obtener la IP real del cliente
            String ipAddress = httpRequest.getHeader("X-Forwarded-For");
            if (ipAddress == null || ipAddress.isEmpty()) {
                ipAddress = httpRequest.getRemoteAddr();
            }
            System.out.println("IP del cliente al hacer login: " + ipAddress);
    
            // 🔥 Solo actualizamos si la IP cambió
            if (ipAddress != null && !ipAddress.isEmpty() && !ipAddress.equals(person.getLastIpKnown())) {
                person.setLastIpKnown(ipAddress);
                personRepository.save(person);
            }
    
            String token = jwtUtil.generateToken(person.getEmail());
            return new LoginResponse(token);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
    }

    @PostMapping("/update-ip")
    public ResponseEntity<Void> updateLastIp(@RequestBody Map<String, String> body) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String lastIpKnown = body.get("lastIpKnown");

        if (lastIpKnown != null && !lastIpKnown.isEmpty()) {
            personRepository.findByEmail(email).ifPresent(person -> {
                person.setLastIpKnown(lastIpKnown);
                personRepository.save(person);
            });
        }

        return ResponseEntity.ok().build();
    }


    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request, HttpServletRequest httpRequest) {

        if (personRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        String ipAddress = httpRequest.getRemoteAddr();
        System.out.println("IP del cliente: " + ipAddress);        

        Person newPerson = new Person(
                request.getEmail(),
                request.getUsername(),
                request.getPassword(),
                ipAddress
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