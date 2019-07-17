package com.tomekl007.chapter_1;

import com.tomekl007.chapter_1.business_components.ComplexLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProgrammaticConfigurationOfDI {

  private final ComplexLogicService complexLogicService;

  @Autowired
  public ProgrammaticConfigurationOfDI(
      ComplexLogicService complexLogicService) {
    this.complexLogicService = complexLogicService;
  }

  @PostConstruct
  public void init() {
    complexLogicService.calculateAndSend(100);
  }
}
