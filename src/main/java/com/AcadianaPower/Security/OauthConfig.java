package com.AcadianaPower.Security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OauthConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .antMatcher("/").authorizeRequests() //change
                .antMatchers("/**").permitAll() //change
                .anyRequest().authenticated()
                .and().oauth2Login();
    }
}
