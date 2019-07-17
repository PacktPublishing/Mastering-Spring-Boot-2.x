package com.tomekl007.chapter_7;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tomekl007.salary.details.api.SalaryDetails;
import com.tomekl007.eventbus.api.EventBus;
import com.tomekl007.eventbus.domain.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.metrics.instrument.Timer;
import org.springframework.metrics.instrument.stats.quantile.GKQuantiles;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Component
@Profile("!integration")
public class SalaryDetailsWithFallback implements SalaryDetails {
  static Logger log = Logger.getLogger(SalaryDetailsWithFallback.class.getName());

  private RestTemplate rest;
  private EventBus eventBus;

  @Autowired
  private MeterRegistry meterRegistry;

  @Autowired
  public SalaryDetailsWithFallback(
      @Qualifier("salaries-client") RestTemplate rest,
      EventBus eventBus) {
    this.rest = rest;
    this.eventBus = eventBus;
  }

  @HystrixCommand(fallbackMethod = "reliable")
  @Override
  public String getInfoAboutSalary(String account) {
    log.warn("some request for account " + account);
    return request(account);
  }

  public String request(String account) {
    Timer timer = meterRegistry
        .timerBuilder("salary_detail_request")
        .quantiles(GKQuantiles.quantiles(0.99, 0.90, 0.50).create()).create();
    return timer.record(() -> requestForSalaryDetails(account));
  }

  private String requestForSalaryDetails(String account) {
//    rest.getForEntity("http://ms-account/account/" +account);
    return "{\"name\" : " + account + ", \"time\": " + Instant.now() + "}";
  }

  public String reliable(String account) {
    meterRegistry.counter("hystrix_fallback").increment();
    return "Info about salary: " + account + " is not currently not available";
  }

  @Scheduled(fixedRate = 1000)
  public void processEvents() {
    Event event = eventBus.receive();
    if (event != null) {
      log.info("received event to process :" + event);
    }

  }
}
