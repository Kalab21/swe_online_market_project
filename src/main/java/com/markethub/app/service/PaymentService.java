package com.markethub.app.service;

import com.markethub.app.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();

    Payment addNewPayment(Payment payment);

    Payment findPaymentById(long id);

    Payment updateById(long id, Payment payment);

    void deletePaymentById(long id);

}
