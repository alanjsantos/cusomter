package com.api.customer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EnderecoCliente {


    @KafkaListener(topics = "${topic.endereco-client}", groupId = "${spring.kafka.consumer.group-id}")
    public void obterEnderecoCliente(String enderecoCliente) throws JsonProcessingException {
        log.info("Mensagem Enedereco {}", enderecoCliente);
    }
}
