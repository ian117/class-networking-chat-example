package network_class.chat_backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ChatController {

    @GetMapping("/hello")
    public String salute() {
        return "HELLO MUDAFAKAS";
    }
}