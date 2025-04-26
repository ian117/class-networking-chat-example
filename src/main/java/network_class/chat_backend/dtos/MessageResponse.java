package network_class.chat_backend.dtos;

import java.time.LocalDateTime;

public class MessageResponse {
    private Long senderId;
    private Long receiverId;
    private String message;
    private LocalDateTime createdAt;

    public MessageResponse(Long senderId, Long receiverId, String message, LocalDateTime createdAt) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}