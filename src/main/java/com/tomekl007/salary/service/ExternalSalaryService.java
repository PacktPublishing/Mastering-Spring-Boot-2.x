package com.tomekl007.salary.service;

import com.tomekl007.chapter_2.domain.Salary;
import com.tomekl007.salary.api.SalaryService;
import com.tomekl007.salary.infrastructure.configuration.SalaryServiceSettings;
import com.tomekl007.chapter_2.persistance.SalaryRepository;
import com.tomekl007.eventbus.api.EventBus;
import com.tomekl007.eventbus.domain.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ExternalSalaryService implements SalaryService {
    static Logger log = Logger.getLogger(ExternalSalaryService.class.getName());

    @Autowired
    MeterRegistry meterRegistry;

    private final SalaryServiceSettings salaryServiceSettings;
    private final SalaryRepository salaryRepository;
    private EventBus eventBus;

    @Autowired
    public ExternalSalaryService(SalaryServiceSettings salaryServiceSettings,
                                  SalaryRepository salaryRepository,
                                  EventBus eventBus) {
        this.salaryServiceSettings = salaryServiceSettings;
        this.salaryRepository = salaryRepository;
        this.eventBus = eventBus;
    }

    @Override
    public boolean pay(Salary salary) {
        if (salaryServiceSettings.getSupportedAccounts().contains(salary.getAccountTo())) {
            salaryRepository.save(salary);
            eventBus.publish(new Event("SAVED", "Saved salary " + salary));
            return true;
        }
        eventBus.publish(new Event("REJECTED", "Rejected salary " + salary));
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("in init method");
    }

    @PreDestroy
    public void cleanup() {
        log.info("in cleanup method. Possible to release some resources");
    }
}
