package org.example.security;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetail userDetail;

    @Autowired
    public SecurityConfig(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetail);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("worker").password("{noop}worker").roles("WORKER")
//                .and()
//                .withUser("hr").password("{noop}hr").roles("HR")
//                .and()
//                .withUser("admin").password("{noop}admin").roles("ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/hr").hasAuthority("HR")
                .antMatchers("/worker").hasAuthority("WORKER")
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