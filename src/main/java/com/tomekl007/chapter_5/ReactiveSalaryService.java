package com.tomekl007.chapter_5;


import com.tomekl007.chapter_2.domain.Salary;
import com.tomekl007.chapter_2.domain.SalaryDto;
import com.tomekl007.chapter_2.persistance.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReactiveSalaryService {

    //note composition
    private final SalaryRepository salaryRepository;

    @Autowired
    public ReactiveSalaryService(SalaryRepository salaryRepository) {

        this.salaryRepository = salaryRepository;
    }

    public Mono<List<SalaryDto>> getSalary(String userId) {
        return Mono.defer(() -> Mono.just(salaryRepository.findByUserId(userId)))
                .subscribeOn(Schedulers.elastic())
                .map(p ->
                        p.stream().map(p1 -> new SalaryDto(p1.getUserId(),
                            p1.getAccountFrom(), p1.getAccountTo(), p1.getAmount()))
                                .collect(Collectors.toList()));
    }

    public Mono<SalaryDto> addSalary(final SalaryDto salary) {
        return Mono.just(salary)
                .map(t -> new Salary(t.getUserId(), t.getAccountFrom(), t.getAccountTo(), t.getAmount()))
                .publishOn(Schedulers.parallel())
                .doOnNext(salaryRepository::save)
                .map(t -> new SalaryDto(t.getUserId(), t.getAccountFrom(), t.getAccountTo(), t.getAmount()));
    }
}
