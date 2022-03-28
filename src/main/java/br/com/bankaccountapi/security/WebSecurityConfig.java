package br.com.bankaccountapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;



	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()
			throws Exception {
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(
			AuthenticationManagerBuilder authenticationManagerBuilder)
					throws Exception {

		authenticationManagerBuilder
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and()
			.csrf().disable()

			.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/api/v1/auth/**",
										"/v2/api-docs",
										"/swagger-resources/**",
										"/swagger-ui/**",
										"/h2-console/**",
						"/Projectbankaccount-env.eba-fqpipk8g.us-east-1.elasticbeanstalk.com/**")
					.permitAll()
				.antMatchers("/api/v1/cards/**", "/api/v1/accounts/**")
					//.hasAnyRole("USER", "ADMIN")
					.authenticated();

		http.headers().frameOptions().disable();

		http.addFilterBefore(authenticationJwtTokenFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						//.allowedOrigins("http://localhost:4200")
						.allowedOrigins("*")
						//.allowedMethods("PUT", "DELETE", "GET", "POST");
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}
	
	
	
}
