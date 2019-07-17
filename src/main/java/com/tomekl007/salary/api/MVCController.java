package com.tomekl007.salary.api;

import com.tomekl007.chapter_2.domain.Salary;
import com.tomekl007.chapter_2.domain.SalaryDto;
import com.tomekl007.chapter_2.persistance.SalaryRepository;
import com.tomekl007.eventbus.api.EventBus;
import com.tomekl007.eventbus.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class MVCController {

  @Autowired
  private SalaryRepository salaryRepository;

  @PostConstruct
  private void init() {
    salaryRepository.save(new Salary("T1", "dummyFrom", "dummyTo", 100L));
  }

  @Autowired
  private EventBus eventBus;

  @RequestMapping("/mvc/all-salaries")
  public String indexView(Model model) {
    model.addAttribute("list", salaryRepository.findAll());
    return "allsalaries";
  }

  @PostMapping("/mvc/salary")
  public String salarySubmit(@ModelAttribute SalaryDto salaryDto, Model model) {
    salaryRepository.save(new Salary(salaryDto.getUserId(),
        salaryDto.getAccountFrom(), salaryDto.getAccountTo(), salaryDto.getAmount()));
    eventBus.publish(new Event("SAVE", "Save salary" + salaryDto));
    model.addAttribute("list", salaryRepository.findAll());
    return "allsalaries";
  }

  @GetMapping("/mvc/createSalary")
  public String salaryForm(Model model) {
    model.addAttribute("salaryDto", new SalaryDto());
    return "create";
  }
}
