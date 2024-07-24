package com.example.estudosDro.Entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String status;
    private String paymentMethod;
    private String transactionId;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
