package network_class.chat_backend.database;

import network_class.chat_backend.database.entities.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(PersonRepository personRepository, RoomRepository roomRepository) {

    return args -> {
      personRepository.save(new Person(
       "Hernan",
        "Cortez",
           "reconquista@live.com",
        "sadadadijknbaidoando",
     "a date",
            "husjsosahoas8992"));

      personRepository.save(new Person(
       "Juanc",
        "D Arc",
           "witch@alive.com",
        "sddnidoapado",
     "a date2",
            "asnoasoiao962"));
      
      personRepository.findAll().forEach(person -> log.info("Preloaded " + person));

      roomRepository.save(new Room("Math", "Math problems"));
      roomRepository.save(new Room("Miscelaneus", "Any subject"));

      roomRepository.findAll().forEach(room -> log.info("Preloaded " + room));
    };
  }
}