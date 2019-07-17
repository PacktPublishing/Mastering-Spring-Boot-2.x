package com.tomekl007.chapter_2.persistance;

import com.tomekl007.chapter_2.domain.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "salary", path = "salary")
public interface SalaryRestRepository extends PagingAndSortingRepository<Salary, Long> {

    @Query("select t from Salary t where t.accountTo =:accountTo")
    List<Salary> findByAccountTo(@Param("accountTo") String accountTo);

}