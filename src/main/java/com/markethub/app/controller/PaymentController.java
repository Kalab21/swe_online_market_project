package com.markethub.app.controller;

import com.markethub.app.model.Payment;
import com.markethub.app.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.findAllPayments();
    }

    @PostMapping("/save-payment")
    public Payment addNewPayment(@RequestBody Payment payment){
        return paymentService.addNewPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable("id") long id){
        return paymentService.findPaymentById(id);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable("id") long id, @RequestBody Payment payment){
        return paymentService.updateById(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        paymentService.deletePaymentById(id);
    }

}
