package com.example.estudosDro.Repositories;

import com.example.estudosDro.Entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
