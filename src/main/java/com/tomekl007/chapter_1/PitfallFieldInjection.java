package com.tomekl007.chapter_1;


import com.tomekl007.payment.api.PaymentService;
import com.tomekl007.chapter_3.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class PitfallFieldInjection {

  @Autowired
  private PaymentService paymentService;

  PitfallFieldInjection(){
    paymentService.pay(new Payment());//problem!!
  }

}
