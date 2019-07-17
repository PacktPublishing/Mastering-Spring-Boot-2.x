package com.tomekl007;


import com.tomekl007.salary.details.api.SalaryDetails;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("integration")
public class SalaryDetailsMock implements SalaryDetails {
  private final Map<String, String> info = new LinkedHashMap<>();

  public void addInfo(String key, String value){
    info.put(key, value);
  }

  @Override
  public String getInfoAboutSalary(String cityName) {
    return info.get(cityName);
  }
}
