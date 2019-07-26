package br.com.iarts.springkafkatest;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
public class SpringKafkaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaTestApplication.class, args);
	}

	@Bean
	public NewTopic topicUsers() {
		return new NewTopic("users", 1, (short) 1);
	}

}
