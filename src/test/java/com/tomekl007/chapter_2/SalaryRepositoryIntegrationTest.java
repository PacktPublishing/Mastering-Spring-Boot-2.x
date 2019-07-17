package com.tomekl007.chapter_2;

import com.tomekl007.chapter_2.domain.Salary;
import com.tomekl007.chapter_2.persistance.SalaryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class SalaryRepositoryIntegrationTest {

    @Autowired
    private SalaryRepository salaryRepository;

    @Test
    public void shouldSaveSalaryAndRetrieveItByUserId() {
        //given
        String userId = "joseph";
        Salary salary =
            new Salary(userId, "124215", "t5356315", 23L);

        //when
        salaryRepository.save(salary);
        List<Salary> salaries = salaryRepository.findByUserId(userId);

        //then
        assertThat(salaries.get(0).getAccountFrom()).isEqualTo("124215");
        assertThat(salaries.get(0).getAccountTo()).isEqualTo("t5356315");
    }

    @Test
    public void shouldRetrieveAllSalariesThatHave123AsAccountFrom() {
        //given
        List<Salary> salaries = Arrays.asList(
                new Salary(UUID.randomUUID().toString(),
                    "123", "55555", 23L),
                new Salary(UUID.randomUUID().toString(),
                    "123", "77777", 23L),
                new Salary(UUID.randomUUID().toString(),
                    "77777", "2145", 23L)
        );

        //when
        salaryRepository.saveAll(salaries);
        List<Salary> foundSalaries = salaryRepository
            .findByFromAccount("123");

        //then
        assertThat(foundSalaries.size()).isEqualTo(2);
    }

}