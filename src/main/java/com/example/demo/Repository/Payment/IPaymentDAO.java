package com.example.demo.Repository.Payment;

import com.example.demo.Entity.Payment;

public interface IPaymentDAO {

    Payment saveNewPayment(Payment payment);
}
