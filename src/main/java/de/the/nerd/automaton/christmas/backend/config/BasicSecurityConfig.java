package de.the.nerd.automaton.christmas.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * to enable activiti, we also need spring security.
     * This provides a very insecure authorization and authentication implementation with basic auth,
     * granting all rights to any authenticated user and disabling csrf.
     *
     * A real application should put more thought into the implementation depending on the requirements.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated() // any request allowed if user is authenticated
                .and()
                .httpBasic() // use basic auth instead of any token based authentication methods
                .and()
                .antMatcher("/api/*")
                .csrf()
                .disable(); // disable csrf to enable simple api calls

    }
}


