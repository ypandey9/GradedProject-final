package com.gl.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.springboot.security.service.DomainUserDetailsService;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//used to configure both authentication and authorization
	@Autowired
	private DomainUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	//authentication
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(this.userDetailsService)
			.passwordEncoder(this.passwordEncoder);
		
	}
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
	
	//authorization
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		
		//define the rules which will tell who can access what url's
		httpSecurity
					.authorizeRequests()
					.antMatchers("/login", "/h2-console/**", "/h2-console**", "/actuator/**")
						.permitAll()
					.antMatchers(HttpMethod.GET, "/api/employees/**", "/api/employees**")
						.hasAnyRole("USER", "ADMIN")
					.antMatchers(HttpMethod.POST, "/api/employees**")
						.hasAnyRole("ADMIN")
					.antMatchers(HttpMethod.PUT, "/api/employees**")
						.hasAnyRole("ADMIN")
					.antMatchers(HttpMethod.DELETE, "/api/employees/**")
						.hasAnyRole("ADMIN")
						.antMatchers(HttpMethod.POST, "/api/employees/add-user**")
						.hasAnyRole("ADMIN")
						.antMatchers(HttpMethod.POST, "/api/employees/add-role**")
						.hasAnyRole("ADMIN")
					.anyRequest()
						.authenticated()
					.and()
						.formLogin()
					.and()
						.httpBasic()
					.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
						
					
	}

}
