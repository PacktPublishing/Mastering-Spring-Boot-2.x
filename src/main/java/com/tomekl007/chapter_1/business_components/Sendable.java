package com.tomekl007.chapter_1.business_components;

public interface Sendable<T> {
  void send(T object);
}
