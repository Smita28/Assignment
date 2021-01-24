package com.org.recipe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class is having security mechanism to protect this application.
 * @author smita
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     *This method is responsible to secure  request and response.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();


    }
    
     /**
      * This method allows only one role to access the application which is USER.
     * @param auth
     * @throws Exception
     */
    @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) 
                throws Exception 
        {
            auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("USER");
        }

}
