package com.tomekl007.chapter_1.business_components;

public class Mail {
  private final String to;
  private final String from;
  private final String text;

  public Mail(String to, String from, String text) {
    this.to = to;
    this.from = from;
    this.text = text;
  }

  @Override
  public String toString() {
    return "Mail{" +
        "to='" + to + '\'' +
        ", from='" + from + '\'' +
        ", text='" + text + '\'' +
        '}';
  }
}
