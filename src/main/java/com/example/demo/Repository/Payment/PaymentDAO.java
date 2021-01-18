package com.example.demo.Repository.Payment;

import com.example.demo.Entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDAO implements IPaymentDAO {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment saveNewPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
