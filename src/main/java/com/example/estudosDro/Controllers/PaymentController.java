package com.example.estudosDro.Controllers;


import com.example.estudosDro.Entities.PaymentEntity;
import com.example.estudosDro.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentEntity> processPayment(@RequestBody PaymentEntity paymentEntity){
        PaymentEntity processedPayment = paymentService.processPayment(paymentEntity);
        return ResponseEntity.ok(processedPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentEntity> getPayment(@PathVariable Long id) {
        return paymentService.getPaymentById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
