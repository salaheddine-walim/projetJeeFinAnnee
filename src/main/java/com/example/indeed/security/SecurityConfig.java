package com.example.indeed.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Pour la gestion des utilisateurs et leurs roles

        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password , active  from user where email=?")
                .authoritiesByUsernameQuery("select email as principal, role from user where email=?")
        ;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Pour la gestion des URLs
        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();

        //http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
        //http.authorizeRequests().antMatchers("/index/*").hasRole("USER");
        //http.authorizeRequests().antMatchers("/admin/*").hasAnyRole("USER","ADMIN");

        //http.authorizeRequests().antMatchers("/index/*").denyAll();

        http.authorizeRequests().anyRequest().authenticated();


    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
