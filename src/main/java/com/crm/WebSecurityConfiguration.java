package com.crm;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		// @formatter:off
		http.authorizeRequests().antMatchers( "/aa").permitAll().anyRequest().authenticated().and() // http.authorizeRequests().antMatchers( "/", "/ui" ).permitAll().anyRequest().authenticated().and()
				.formLogin()
				.loginPage( "/login" ).permitAll()
				.and()
				.logout()
				.logoutUrl( "/logout" )
				.logoutSuccessUrl( "/login" )
				.and()
				.csrf()
				.disable();
		// @formatter:on
	}

	// @Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		auth.inMemoryAuthentication().withUser( "user" ).password( "password" ).roles( "USER" );
	}
}