package com.tomekl007.chapter_1.configure_scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonBean {
  public SingletonBean() {
    System.out.println("constructor of SingletonBean");
  }
}
