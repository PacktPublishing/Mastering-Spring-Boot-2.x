package com.tomekl007.chapter_1.business_components;

public class SendableOfMail implements Sendable<Mail> {
  @Override
  public void send(Mail object) {
    System.out.println("sending mail:" + object);
  }
}
