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
  CommandLineRunner initDatabase(
    PersonRepository personRepository, 
    MessageRepository messageRepository
  ) {

    return args -> {
      Person person1 = new Person(
        "reconquista@live.com",
         "Heran Cortez",
         "#12345",
             "127.0.0.2");
            
      Person person2 = new Person(
        "witch@alive.com",
         "Joan D Arc",
         "#12345",
             "127.0.0.3");

      personRepository.save(person1);

      personRepository.save(person2);
      
      personRepository.findAll().forEach(person -> log.info("Preloaded " + person));

      messageRepository.save(new Message(person1, person2, "Soy numero 1, como estas"));

      messageRepository.save(new Message(person2, person1, "Hola, espero estes bien, soy numero 2"));

      messageRepository.findAll().forEach(message -> log.info("Preloaded " + message));

      // roomRepository.findAll().forEach(room -> log.info("Preloaded " + room));
    };
  }
}