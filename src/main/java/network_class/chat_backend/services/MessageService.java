package network_class.chat_backend.services;

import network_class.chat_backend.database.entities.Message;
import network_class.chat_backend.database.entities.Person;
import network_class.chat_backend.database.entities.MessageRepository;
import network_class.chat_backend.database.entities.PersonRepository;
import network_class.chat_backend.dtos.MessageRequest;
import network_class.chat_backend.dtos.MessageResponse;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        // validación: no enviarse mensajes a sí mismo
        if (sender.getId().equals(receiver.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot send a message to yourself");
        }

        Message message = new Message(sender, receiver, request.getMessage());
        Message saved = messageRepository.save(message);

        return new MessageResponse(
                saved.getSender().getId(),
                saved.getReceiver().getId(),
                saved.getMessage(),
                saved.getCreatedAt()
        );
    }


    public List<MessageResponse> getConversation(Long otherUserId, Integer limit) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Person me = personRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Pageable pageable = limit != null ? PageRequest.of(0, limit) : Pageable.unpaged();

        List<Message> messages = messageRepository.findConversation(me.getId(), otherUserId, pageable);

        return messages.stream()
                .map(m -> new MessageResponse(
                        m.getSender().getId(),
                        m.getReceiver().getId(),
                        m.getMessage(),
                        m.getCreatedAt()))
                .toList();
    }

    public long countConversation(Long otherUserId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Person me = personRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return messageRepository.countConversation(me.getId(), otherUserId);
    }

}