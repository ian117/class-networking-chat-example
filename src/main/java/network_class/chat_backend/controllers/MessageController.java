package network_class.chat_backend.controllers;

import network_class.chat_backend.dtos.MessageRequest;
import network_class.chat_backend.dtos.MessageResponse;
import network_class.chat_backend.services.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
        MessageResponse response = messageService.sendMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/conversation/{otherUserId}")
    public ResponseEntity<List<MessageResponse>> getConversation(
        @PathVariable Long otherUserId,
        @RequestParam(required = false) String limit) {

            List<MessageResponse> conversation = messageService.getConversation(otherUserId, limit);
            return ResponseEntity.ok(conversation);
    }

    @GetMapping("/conversation/{otherUserId}/count")
    public ResponseEntity<Map<String, Long>> countConversation(@PathVariable Long otherUserId) {
        long total = messageService.countConversation(otherUserId);
        Map<String, Long> response = new HashMap<>();
        response.put("totalMessages", total);
        return ResponseEntity.ok(response);
    }

}