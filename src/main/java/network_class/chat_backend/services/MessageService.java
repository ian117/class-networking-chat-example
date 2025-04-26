package network_class.chat_backend.services;

import network_class.chat_backend.database.entities.Message;
import network_class.chat_backend.database.entities.Person;
import network_class.chat_backend.database.entities.MessageRepository;
import network_class.chat_backend.database.entities.PersonRepository;
import network_class.chat_backend.dtos.MessageRequest;
import network_class.chat_backend.dtos.MessageResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;

    public MessageService(MessageRepository messageRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
    }

    public MessageResponse sendMessage(MessageRequest request) {
        // Obtener email desde el token
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Person sender = personRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sender not found with email: " + email
                ));

        Person receiver = personRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Receiver not found with ID: " + request.getReceiverId()
                ));

        Message message = new Message(sender, receiver, request.getMessage());
        Message saved = messageRepository.save(message);

        return new MessageResponse(
                saved.getSender().getId(),
                saved.getReceiver().getId(),
                saved.getMessage(),
                saved.getCreatedAt()
        );
    }
}