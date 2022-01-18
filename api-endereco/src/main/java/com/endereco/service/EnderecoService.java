package com.endereco.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EnderecoService {

    @Value("${topic.endereco-cliente}")
    private String topicEnderecoCliente;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageKafka(String message) {
        this.kafkaTemplate.send(topicEnderecoCliente, message);
        log.info("Mensagem enviada para o topic_endereco-cliente: {}", message);
    }
}
