package com.tomekl007.salary.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/reactive/**", "/mvc/**", "/salary-add", "/topic", "/application/**", "/hystrix.stream")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/reactive/**", "/mvc/**", "/salary-add", "/topic")
                .permitAll()
                .and()
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/secret")
                .denyAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/templates/**", "/assets/**");
    }
}