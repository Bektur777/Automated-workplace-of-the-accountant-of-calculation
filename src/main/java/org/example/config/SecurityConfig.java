package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("worker").password("{noop}worker").roles("WORKER")
                .and()
                .withUser("hr").password("{noop}hr").roles("HR")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/hr").hasAnyRole("HR")
                .antMatchers("/worker").hasAnyRole("WORKER")
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .and().csrf().disable()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

}