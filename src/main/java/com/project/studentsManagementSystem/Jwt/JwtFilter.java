package com.project.studentsManagementSystem.Jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.studentsManagementSystem.Repository.StudentRepository;
import com.project.studentsManagementSystem.Service.AdminUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtToken jwt;
    
	@Autowired
	AdminUserDetailsService adminUserDetailsService; 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header=request.getHeader("Authorization");
		if(header==null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
		}
		try {
			String token=header.substring(7);
			String netid=jwt.extractNetid(token);
			if(netid!=null) {
				UserDetails user=adminUserDetailsService.loadUserByUsername(netid);
				if(jwt.validateToken(user, token)) {
					UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
					  auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					  SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
			
		}catch(Exception e) {
			System.err.println(e.getLocalizedMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
			  filterChain.doFilter(request, response);
		}
	}
