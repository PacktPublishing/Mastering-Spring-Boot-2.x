package com.tomekl007.beans;

import org.springframework.stereotype.Component;

@Component
public class ParentBean {
  public void action(){
    System.out.println("Action from parent");
  }

}
