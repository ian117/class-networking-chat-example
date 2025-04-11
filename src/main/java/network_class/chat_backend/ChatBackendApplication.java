package network_class.chat_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ChatBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBackendApplication.class, args);
	}

}
