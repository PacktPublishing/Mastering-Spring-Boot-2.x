package com.tomekl007.salary.service;

import com.tomekl007.salary.api.UserService;
import com.tomekl007.chapter_2.domain.Salary;
import com.tomekl007.chapter_2.domain.SalaryAndUser;
import com.tomekl007.chapter_2.domain.User;
import com.tomekl007.chapter_2.persistance.SalaryRepository;
import com.tomekl007.chapter_2.persistance.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private SalaryRepository salaryRepository;

  @Override
  public List<User> getAllUsers() {
    return StreamSupport.stream(usersRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public void insert(User user) {
    usersRepository.save(user);
  }

  @Override
  public Optional<SalaryAndUser> getSalaryAndUsersForUserId(String userId) {
    List<User> users = usersRepository.findByName(userId);
    List<Salary> salaries = salaryRepository.findByUserId(userId);
    if(users.isEmpty()){
      return Optional.empty();
    }
    return Optional.of(new SalaryAndUser(users.get(0), salaries));

  }
}
