package com.example.estudosDro.Services;

import com.example.estudosDro.Entities.PaymentEntity;
import com.example.estudosDro.Entities.UserEntity;
import com.example.estudosDro.Kafka.PaymentProducer;
import com.example.estudosDro.Repositories.PaymentRepository;
import com.example.estudosDro.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentProducer paymentProducer;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public PaymentEntity processPayment(PaymentEntity paymentEntity){
        paymentEntity.setStatus("Pending");
        paymentEntity.setTransactionId("BRT" + System.currentTimeMillis());
        PaymentEntity savedPayment = paymentRepository.save(paymentEntity); // Salva primeiro
        paymentProducer.sendMessage(savedPayment);
        return paymentEntity;
    }

    public void updatePayment(PaymentEntity paymentEntity) {
        paymentEntity.setStatus("Completed");
        paymentRepository.save(paymentEntity);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(paymentEntity.getUser().getId());
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setHasPaid(true);
            userEntity.setAccessExpirationDate(LocalDateTime.now().plusMonths(1));
            userRepository.save(userEntity);
        }
    }

    public Optional<PaymentEntity> getPaymentById(Long id){
        return paymentRepository.findById(id);
    }

}
