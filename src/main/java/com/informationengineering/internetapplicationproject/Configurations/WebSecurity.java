package com.informationengineering.internetapplicationproject.Configurations;

import com.informationengineering.internetapplicationproject.Filters.TokenFilter;
import com.informationengineering.internetapplicationproject.Repositories.EstateRepository;
import com.informationengineering.internetapplicationproject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    TokenFilter tokenFilter;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/login", "/authenticate", "/index", "/").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().
                authenticationEntryPoint(
                        (httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED));
//        http.csrf().disable().authorizeRequests()
//                .antMatchers( "/**").permitAll()
//                .anyRequest().permitAll();
    }
}
