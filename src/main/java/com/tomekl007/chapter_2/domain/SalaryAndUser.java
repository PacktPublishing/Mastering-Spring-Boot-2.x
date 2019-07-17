package com.tomekl007.chapter_2.domain;

import java.util.List;

public class SalaryAndUser {
  private final User user;
  private final List<Salary> salaries;

  public SalaryAndUser(User user, List<Salary> salaries) {

    this.user = user;
    this.salaries = salaries;
  }

  public User getUser() {
    return user;
  }

  public List<Salary> getSalaries() {
    return salaries;
  }

  @Override
  public String toString() {
    return "SalaryAndUser{" +
        "user=" + user +
        ", salaries=" + salaries +
        '}';
  }
}


