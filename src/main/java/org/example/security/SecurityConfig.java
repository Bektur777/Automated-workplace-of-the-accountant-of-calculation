package org.example.security;

import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetail userDetail;

    @Autowired
    public SecurityConfig(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetail).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/hr/**").hasAuthority("HR")
                .antMatchers("/worker/**").hasAuthority("WORKER")
                .antMatchers("/accountant/**").hasAuthority("ACCOUNTANT")
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/css/**","/js/**","/scss/**", "/fonts/**", "/pages/**", "/images/**"
                        ).permitAll()
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