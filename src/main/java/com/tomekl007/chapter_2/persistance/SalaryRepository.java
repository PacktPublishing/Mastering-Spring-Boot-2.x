package com.tomekl007.chapter_2.persistance;

import com.tomekl007.chapter_2.domain.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryRepository extends CrudRepository<Salary, Long> {

    @Query("select t from Salary t where t.userId =:userId")
    List<Salary> findByUserId(@Param("userId") String userId);

    @Query("select t from Salary t where t.accountFrom =:accountFrom")
    List<Salary> findByFromAccount(
        @Param("accountFrom") String accountFrom);
}
