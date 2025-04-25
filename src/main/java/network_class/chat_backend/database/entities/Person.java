package network_class.chat_backend.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String password;
    private String lastIpKnown;

    // Constructor vacío para JPA
    protected Person() {}

    // Constructor completo
    public Person(String email, String username, String password, String lastIpKnown) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.lastIpKnown = lastIpKnown;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastIpKnown() {
        return lastIpKnown;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastIpKnown(String lastIpKnown) {
        this.lastIpKnown = lastIpKnown;
    }

    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id) &&
               Objects.equals(email, person.email) &&
               Objects.equals(username, person.username) &&
               Objects.equals(password, person.password) &&
               Objects.equals(lastIpKnown, person.lastIpKnown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, lastIpKnown);
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", email='" + email + '\'' +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", lastIpKnown='" + lastIpKnown + '\'' +
               '}';
    }
}