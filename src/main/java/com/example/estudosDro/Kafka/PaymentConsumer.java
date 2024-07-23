package com.example.estudosDro.Kafka;

import com.example.estudosDro.Entities.PaymentEntity;
import com.example.estudosDro.Services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
    private static final Logger logger = LoggerFactory.getLogger(PaymentConsumer.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper; // Certifique-se de que o ObjectMapper está injetado

    @KafkaListener(topics = "payments", groupId = "group_id")
    public void consume(String message) { // Remove o parâmetro Acknowledgment
        logger.info("Mensagem recebida do Kafka: {}", message);

        try {
            PaymentEntity paymentEntity = objectMapper.readValue(message, PaymentEntity.class);
            logger.info("Objeto PaymentEntity desserializado: {}", paymentEntity);
            paymentService.processPayment(paymentEntity);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao desserializar a mensagem: {}", message, e);
            // Lide com o erro de desserialização aqui (ex: reprocessar, descartar, etc.)
        }
    }
}
