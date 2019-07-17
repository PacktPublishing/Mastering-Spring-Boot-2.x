package com.tomekl007.chapter_6;

import com.tomekl007.chapter_2.domain.SalaryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public class ReactiveSalaryServiceIntegrationTest {

  // Inject which port we was assigned
  @Value("${local.server.port}")
  private int port;


  @Test
  public void shouldCreateAndRetrieveSalary() {
    System.out.println("Started at port: " + port);
    //given
    RestTemplate restTemplate = new RestTemplate();
    SalaryDto salaryDto = new SalaryDto(UUID.randomUUID().toString(),
        "123", "456", 200L);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<SalaryDto> entity = new HttpEntity<>(salaryDto, httpHeaders);

    //when
    ResponseEntity<SalaryDto> response = restTemplate
        .postForEntity(createTestUrl("/reactive/salary"),
            entity, SalaryDto.class);

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(response.getBody()).isNotNull();

    //when
    List<SalaryDto> getResponse = restTemplate.exchange(
        createTestUrl("/reactive/salary/" +
            response.getBody().getUserId()),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<SalaryDto>>() {
        }).getBody();

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(getResponse.size()).isGreaterThan(0);

  }

  private String createTestUrl(String suffix) {
    return "http://localhost:" + port + suffix;
  }

}