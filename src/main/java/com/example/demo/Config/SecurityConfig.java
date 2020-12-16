package com.example.demo.Config;

import com.example.demo.Service.MyUserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsServicesImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Configuration
    @Order(1)
    public class WebSecurityConfigCustomer extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
           /** http.
                    authorizeRequests()
                    .antMatchers("/store", "/store/**", "/connexion").permitAll()
                    .antMatchers("/sell/**").hasAuthority("SUPPLIER")
                    .and()
                    .formLogin()
                    .loginPage("/login/customer/")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();*/

           //@formatter:off
          /* http.antMatcher("/customer/**")
                   .authorizeRequests()
                   .anyRequest()
                   .hasRole("CUSTOMER")

                   .and()
                   .formLogin()
                   .loginPage("/loginPage/customer/")
                   .loginProcessingUrl("/loginPage/customer/")
                   .usernameParameter("username")
                   .passwordParameter("password")
                   .defaultSuccessUrl("/shop/")

                   .and()
                   .logout()
                   .deleteCookies("JSESSIONID");*/
            //@formatter:on
            //throw new AuthenticationSuccessEvent(http);


        }
/*
        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(authenticationProvider());
        }*/


    }


    @Configuration
    @Order(2)
    public static class WebSecurityConfigSupplier extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            /*http.
                    authorizeRequests()
                    .antMatchers("/store", "/store/**", "/connexion").permitAll()
                    .antMatchers("/sell/**").hasAuthority("SUPPLIER")
                    .and()
                    .formLogin()
                    .loginPage("/login/customer/")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();*/
            //@formatter:off
           /*http.antMatcher("/sell/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("SUPPLIER")

                    .and()
                    .formLogin()
                    .loginPage("/loginPage/supplier/")
                    .permitAll()
                    .defaultSuccessUrl("/sell/")

                    .and()
                    .logout()
                    .deleteCookies("JSESSIONID");*/
            //@formatter:on
            //throw new AuthenticationSuccessEvent(http);


        }

       /* @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(authenticationProvider());
        }*/


    }
}
