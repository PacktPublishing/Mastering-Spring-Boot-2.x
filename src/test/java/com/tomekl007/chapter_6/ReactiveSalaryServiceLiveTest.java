package com.tomekl007.chapter_6;

import com.tomekl007.chapter_2.domain.SalaryDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ReactiveSalaryServiceLiveTest {

    @Ignore
    @Test
    public void shouldCreateAndRetrieveSalary() {
        //given
        RestTemplate restTemplate = new RestTemplate();
        SalaryDto salaryDto = new SalaryDto(UUID.randomUUID().toString(),
            "123", "456", 200L);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SalaryDto> entity = new HttpEntity<>(salaryDto, httpHeaders);

        //when
        ResponseEntity<SalaryDto> response = restTemplate
                .postForEntity("http://localhost:8080/salary",
                    entity, SalaryDto.class);

        //then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();

        //when
        List<SalaryDto> getResponse = restTemplate.exchange(
            "http://localhost:8080/salary/" +
                response.getBody().getUserId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SalaryDto>>() {
                }).getBody();

        //then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(getResponse.size()).isGreaterThan(0);

    }

}