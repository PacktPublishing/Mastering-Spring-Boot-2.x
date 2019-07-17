package com.tomekl007.salary.api;

import com.tomekl007.chapter_2.domain.SalaryAndUser;
import com.tomekl007.chapter_2.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
  List<User> getAllUsers();
  void insert(User user);
  Optional<SalaryAndUser> getSalaryAndUsersForUserId(String userId);
}
