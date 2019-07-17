package com.tomekl007.chapter_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SalariesService {

  private RestTemplate restTemplate;

  @Autowired
  public SalariesService(@Qualifier("salaries-client") RestTemplate restTemplate) {

    this.restTemplate = restTemplate;
  }

  public void simpleGet() {
    restTemplate.getForEntity("http://salaries.com", String.class);
  }
}
