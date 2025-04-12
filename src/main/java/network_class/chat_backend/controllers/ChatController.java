package network_class.chat_backend.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ChatController {
    
    @MessageMapping("/room1")
    @SendTo("/room1/public")
    public String greeting(String message) throws Exception {
      Thread.sleep(1000); // simulated delay
      return "Hello, " + HtmlUtils.htmlEscape("MY broda") + "!";
    }
  
    @GetMapping("/hello")
    public String salute() {
        return "HELLO MUDAFAKAS";
    }
}