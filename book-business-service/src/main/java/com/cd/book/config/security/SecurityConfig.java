package com.cd.book.config.security;
/**
 * @author Mayank
 * 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cd.book.security.CollegeDaysUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CollegeDaysUserDetailsService userDetailService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailService).passwordEncoder(this.passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable();
	/*http.authorizeRequests().antMatchers("/home").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
		.failureUrl("/login?error").permitAll();
	http.logout().logoutSuccessUrl("/");*/
    }
}