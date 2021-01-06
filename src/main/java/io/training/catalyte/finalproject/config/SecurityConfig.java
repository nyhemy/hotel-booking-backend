//package io.training.catalyte.finalproject.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//  private static final String[] AUTH_WHITELIST = {
//      // -- swagger ui
//      "/swagger-resources/**",
//      "/swagger-ui.html",
//      "/v2/api-docs",
//      "/webjars/**",
//      "/patients",
//      "/encounters"
//  };
//
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//    http
//        .addFilterBefore(new AuthFilter(), AuthFilter.class)
//        .authorizeRequests()
//        .antMatchers(HttpMethod.GET).permitAll()
//        .antMatchers(HttpMethod.PUT).permitAll()
//        .antMatchers(HttpMethod.POST).permitAll()
//        .antMatchers(HttpMethod.DELETE).permitAll()
//        .antMatchers(AUTH_WHITELIST).permitAll()
//        .and()
//        .sessionManagement().disable()
//        .csrf().disable();
//  }
//}
