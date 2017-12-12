package io.dagistan.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// configure authentication as normal (means not from a datasource like database or redis etc.)
		auth.inMemoryAuthentication().
			withUser("dagistan").password("dagistan").roles("ADMIN").
			and().
			withUser("test").password("test").roles("USER");
	}
	
	// just remove block comments from one of (1) or (2) ...  below to check the results 
	// from your rest client calling http://localhost:8080/api/hello
	// test with user and password above and with different variations
	
	/*
	// (1) authenticate every request
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().anyRequest().
		fullyAuthenticated().	// (1)
		and().httpBasic();
		httpSecurity.csrf().disable();
	}
	*/
	
	/*
	// (2) just authenticate the resource specified, others are allowed
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().
		antMatchers("/api/**").
		permitAll().
		and().httpBasic();
		httpSecurity.csrf().disable();
	}
	*/
	
	/*
	// (3) just allow ADMIN role users
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().
		antMatchers("/api/**").hasRole("ADMIN").
		anyRequest().fullyAuthenticated().
		and().httpBasic();
		httpSecurity.csrf().disable();
	}
	*/
	
	// (3) just allow ADMIN role users for "each resource's" hello endpoint
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().
		antMatchers("/**/hello").hasRole("ADMIN").
		anyRequest().fullyAuthenticated().
		and().httpBasic();
		httpSecurity.csrf().disable();
	}

}
