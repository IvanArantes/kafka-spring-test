package br.com.iarts.springkafkatest;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    private final Producer producer;
    private final KafkaAdmin kafkaAdmin;

    @Autowired
    public KafkaController(Producer producer, KafkaAdmin kafkaAdmin) {
        this.producer = producer;
        this.kafkaAdmin = kafkaAdmin;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);

    }

    @PostMapping(value = "")
    public void createTopic(@RequestParam("nome") String nome){
        logger.info("Criando novo tópico: " + nome);
        AdminClient admin = AdminClient.create(this.kafkaAdmin.getConfig());
        ListTopicsResult listTopicsResult = admin.listTopics();

        admin.createTopics(Arrays.asList(new NewTopic(nome, 10, (short) 1)));
    }
}
