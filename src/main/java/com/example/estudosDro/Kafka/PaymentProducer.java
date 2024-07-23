package com.example.estudosDro.Kafka;


import com.example.estudosDro.Entities.PaymentEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    private static final String TOPIC = "payments";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(PaymentEntity payment) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String paymentJson = objectMapper.writeValueAsString(payment);
            kafkaTemplate.send(TOPIC, paymentJson);
        } catch (JsonProcessingException e) {
            // Handle exception
        }
    }


}
