package com.tomekl007.salary.infrastructure.security;

import com.tomekl007.chapter_2.persistance.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @Autowired
    private SalaryRepository salaryRepository;

    @RequestMapping("/secret")
    public Long numberOfSalary() {
        return salaryRepository.count();
    }
}
