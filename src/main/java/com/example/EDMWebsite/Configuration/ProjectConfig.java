package com.example.EDMWebsite.Configuration;

import com.example.EDMWebsite.Filters.CSRFTokenLogger;
import com.example.EDMWebsite.Security.CustomAuthenticationProvider;
import com.example.EDMWebsite.Security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig{

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider();
    }

    @Bean//TODO:Change the management of CSRF tokens when scaling(pg 258 Spring Security in Action)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll();
        http.addFilterAfter(new CSRFTokenLogger(), CsrfFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll();
        http.csrf(c-> c.ignoringAntMatchers("/signup"));
//                .mvcMatchers("/blog-post/1").authenticated()
//                .mvcMatchers("/blog-post/2").authenticated()
//                .mvcMatchers("/").permitAll()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout().permitAll();

        return http.build();
    }


}
