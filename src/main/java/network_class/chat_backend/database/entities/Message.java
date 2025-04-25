package network_class.chat_backend.database.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Person sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Person receiver;

    @Lob
    private String message;

    private LocalDateTime createdAt;

    public Message() {}

    public Message(Person sender, Person receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Person getSender() {
        return sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
               "id=" + id +
               ", sender=" + sender.getId() +
               ", receiver=" + receiver.getId() +
               ", message='" + message + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}