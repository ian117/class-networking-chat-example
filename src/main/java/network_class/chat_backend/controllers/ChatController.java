package network_class.chat_backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
public class ChatController {

    @GetMapping("/hello")
    public String salute() {
        return "HELLO MUDAFAKAS";
    }
}