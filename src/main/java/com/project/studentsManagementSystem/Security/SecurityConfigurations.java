package com.project.studentsManagementSystem.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.studentsManagementSystem.Jwt.JwtFilter;
import com.project.studentsManagementSystem.Service.AdminUserDetailsService;
import com.project.studentsManagementSystem.Service.StudentDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	JwtFilter jwtfilter;
	
	     @Bean
         public DefaultSecurityFilterChain securityfilterchain(HttpSecurity http) {
        	 http.authorizeHttpRequests(
        			 (auth)->auth.requestMatchers("/auth/**").permitAll()
        			 .requestMatchers("/student/**").authenticated()
        			 .anyRequest().permitAll())
        	 .csrf(csrf->csrf.disable())
        	 .cors(cors->cors.configurationSource(corss()))
        	 .sessionManagement(sess->sess.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
        	 .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
        	 return http.build();
         }
	     
	     @Value("${frontend.url}")
	     private String frontendUrl;
	     @Bean
	 	public CorsConfigurationSource corss() {
	 		CorsConfiguration cors=new CorsConfiguration();
	 		cors.setAllowedOrigins(List.of("http://localhost:5500"));
	 		cors.setAllowedMethods(List.of("GET","POST","PATCH","DELETE"));
	 		cors.setAllowedHeaders(List.of("*"));
	 	   
	 		UrlBasedCorsConfigurationSource url= new UrlBasedCorsConfigurationSource();
	 		url.registerCorsConfiguration("/**", cors);
	 		return url;
	 	}
         
         @Autowired
         AdminUserDetailsService adminuserdetailsservice;
         
         @Autowired
         StudentDetailsService studentdetailsservice;
         
         @Bean
         PasswordEncoder passwordencoder() {
        	 return new BCryptPasswordEncoder();
         }
         
         DaoAuthenticationProvider daoforadmin() {
        	 DaoAuthenticationProvider dao=new DaoAuthenticationProvider(adminuserdetailsservice);
        	 dao.setPasswordEncoder(passwordencoder());
        	 return dao;
         }
         
         DaoAuthenticationProvider daoforstudent() {
        	 DaoAuthenticationProvider dao=new DaoAuthenticationProvider(studentdetailsservice);
        	 dao.setPasswordEncoder(passwordencoder());
        	 return dao;
         }
         
         @Bean
         AuthenticationManager authmanager() {
        	 return new ProviderManager(List.of(daoforadmin(),daoforstudent()));
         }
}


