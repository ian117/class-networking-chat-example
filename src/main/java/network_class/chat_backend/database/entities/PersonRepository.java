package network_class.chat_backend.database.entities;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);
    Optional<Person> findByUsername(String username);
    boolean existsByEmail(String email);
}
