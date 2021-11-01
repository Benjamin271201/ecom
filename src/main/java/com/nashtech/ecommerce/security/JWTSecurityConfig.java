//package com.nashtech.ecommerce.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {
//    //TODO: current: 4.4
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authz -> authz
//                        .antMatchers(HttpMethod.GET, "/api/**").hasAuthority("SCOPE_read")
//                        .antMatchers(HttpMethod.POST, "/api").hasAuthority("SCOPE_write")
//                        .anyRequest().authenticated())
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
//    }
//}
