package com.tomekl007.chapter_1;

import com.tomekl007.chapter_1.profilesconfig.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NewComponent {
  @Autowired
  DataSourceConfig dataSourceConfig;

  @PostConstruct
  public void init(){
    dataSourceConfig.setup();
  }
}
