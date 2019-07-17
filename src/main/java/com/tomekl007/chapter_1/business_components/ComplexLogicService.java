package com.tomekl007.chapter_1.business_components;

public class ComplexLogicService {

  private Sendable<Mail> sendable;

  public ComplexLogicService(Sendable<Mail> sendable) {

    this.sendable = sendable;
  }

  public void calculateAndSend(int a) {
    sendable.send(
        new Mail("f1", "t1", String.format("%d", a * 10))
    );
  }
}
