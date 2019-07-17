package com.tomekl007.chapter_6;

import com.tomekl007.chapter_2.domain.Salary;
import com.tomekl007.chapter_2.domain.SalaryDto;
import com.tomekl007.eventbus.api.EventBus;
import com.tomekl007.SalaryApplication;
import com.tomekl007.salary.api.MVCController;
import com.tomekl007.chapter_2.persistance.SalaryRepository;
import com.tomekl007.salary.infrastructure.security.SpringSecurityWebAppConfigWithCsrf;
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

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {SalaryApplication.class,
    SpringSecurityWebAppConfigWithCsrf.class})
@WebMvcTest(
    controllers = MVCController.class
)
public class MVCControllerSecurityTest {

  @MockBean
  private SalaryRepository salaryRepository;

  @MockBean
  private EventBus eventBus;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnAllAvailablesalaries() throws Exception {
    //given
    Iterable<Salary> salaries = Arrays.asList(
        new Salary("u1", "134",
            "5125", 23L));
    when(salaryRepository.findAll()).thenReturn(salaries);

    //when, then
    this.mockMvc.perform(get("/mvc/all-salaries")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("u1")))
        .andExpect(content().string(containsString("134")))
        .andExpect(content().string(containsString("5125")))
        .andReturn();
  }

  @Test
  public void shouldReturnFormForCreatingSalaryWithCsrfHiddenField() throws Exception {
    //when, then
    this.mockMvc.perform(get("/mvc/createSalary"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("_csrf"))) //included!
        .andReturn();
  }

  @Test
  public void shouldPostNewSalaryWithCsrf() throws Exception {
    //given
    SalaryDto salaryDto = new SalaryDto();
    salaryDto.setUserId("u_id1");
    salaryDto.setAccountFrom("123");
    salaryDto.setAccountTo("5325");

    mockMvc.perform(
        post("/mvc/salary")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .with(csrf())
            .sessionAttr("salaryDto", salaryDto)
    )
        .andExpect(status().isOk())
        .andExpect(view().name("allsalaries"));
  }

  @Test
  public void shouldReturn403IfPostWithoutCsrf() throws Exception {
    //given
    SalaryDto salaryDto = new SalaryDto();
    salaryDto.setUserId("u_id1");
    salaryDto.setAccountFrom("4214");
    salaryDto.setAccountTo("531251");

    mockMvc.perform(
        post("/mvc/salary")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            //note that req send without
            .sessionAttr("salaryDto", salaryDto)
    )
        .andExpect(status().is(403));
  }
}