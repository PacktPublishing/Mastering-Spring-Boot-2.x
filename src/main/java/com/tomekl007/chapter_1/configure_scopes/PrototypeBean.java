package com.tomekl007.chapter_1.configure_scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {

  public PrototypeBean() {
    System.out.println("constructor of PrototypeBean");
  }
}
