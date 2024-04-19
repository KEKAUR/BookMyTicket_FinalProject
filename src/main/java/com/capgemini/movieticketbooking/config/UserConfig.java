package com.capgemini.movieticketbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class UserConfig {
	
	@Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Bean
		public static BCryptPasswordEncoder passwordEncoder() {
			log.info("inside password encoder");
		
			return new BCryptPasswordEncoder(); 
		}	
	
	@SuppressWarnings("removal")
	@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		log.info("inside security filter");
		
		httpSecurity.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(HttpMethod.GET, "/api/addresses/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/addresses").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/movies/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/movies/").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/tickets/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/tickets/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/users/**").hasAuthority("ADMIN")
				.anyRequest()
				.authenticated())
        .httpBasic(Customizer.withDefaults())
        .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

				return httpSecurity.build();
		}
	
	
	@Bean
		public UserDetailsService userDetailsService() {
			return new CustomUserDetailsService();
		}
	
	
	@Bean
		public AuthenticationManager authenticationMangManager(AuthenticationConfiguration configuration) throws Exception {
			log.info("inside authentication manager");
		
			return configuration.getAuthenticationManager();
		}
	
	
	@Bean
		public DaoAuthenticationProvider daoAuthenticationProvider() {
			log.info("inside dao authentication provider");
		
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService());
			authProvider.setPasswordEncoder(passwordEncoder());
			
			return authProvider;
		}
	
}
