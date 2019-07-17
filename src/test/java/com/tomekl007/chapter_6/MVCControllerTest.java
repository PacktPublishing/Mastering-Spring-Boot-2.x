package com.tomekl007.chapter_6;

import com.tomekl007.chapter_2.domain.Salary;
import com.tomekl007.eventbus.api.EventBus;
import com.tomekl007.SalaryApplication;
import com.tomekl007.chapter_2.domain.SalaryDto;
import com.tomekl007.chapter_2.persistance.SalaryRepository;
import com.tomekl007.salary.api.MVCController;
import com.tomekl007.salary.infrastructure.security.SpringSecurityWebAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(
    classes = {SalaryApplication.class, SpringSecurityWebAppConfig.class})
@WebMvcTest(
    controllers = MVCController.class
)
public class MVCControllerTest {

  @MockBean
  private SalaryRepository salaryRepository;

  @MockBean
  private EventBus eventBus;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnAllsalaries() throws Exception {
    //given
    Iterable<Salary> salaries = Arrays.asList(
        new Salary("u1", "from1",
            "to1", 23L));
    when(salaryRepository.findAll()).thenReturn(salaries);

    //when, then
    this.mockMvc.perform(get("/mvc/all-salaries")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("u1")))
        .andExpect(content().string(containsString("from1")))
        .andExpect(content().string(containsString("to1")))
        .andReturn();
  }

  @Test
  public void shouldReturnFormForCreatingSalary() throws Exception {
    //when, then
    this.mockMvc.perform(get("/mvc/createSalary")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "<form action=\"/mvc/salary\" method=\"post\">\n" +
                "    <p>userId: <input type=\"text\" id=\"userId\" name=\"userId\" value=\"\"/></p>\n" +
                "    <p>accountFrom: <input type=\"text\" id=\"accountFrom\" name=\"accountFrom\" value=\"\"/></p>\n" +
                "    <p>accountTo: <input type=\"text\" id=\"accountTo\" name=\"accountTo\" value=\"\"/></p>\n" +
                "    <p>amount: <input type=\"number\" id=\"amount\" name=\"amount\" value=\"\"/></p>\n" +
                "    <p><input type=\"submit\" value=\"Submit\"/> <input type=\"reset\" value=\"Reset\"/></p>\n" +
                "</form>")))

        .andReturn();
  }

  @Test
  public void shouldPostNewSalary() throws Exception {
    //given
    SalaryDto salaryDto = new SalaryDto();
    salaryDto.setUserId("u_id1");
    salaryDto.setAccountFrom("51351");
    salaryDto.setAccountTo("123");

    mockMvc.perform(
        post("/mvc/salary")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("salaryDto", salaryDto)
    )
        .andExpect(status().isOk())
        .andExpect(view().name("allsalaries"));
  }
}