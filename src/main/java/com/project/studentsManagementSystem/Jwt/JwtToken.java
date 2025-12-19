package com.project.studentsManagementSystem.Jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
	private final static String key="jAnR8CtweiHQPQuzVywdc0BzbbNZCofk";
	private final static SecretKey secretkey=Keys.hmacShaKeyFor(key.getBytes());
   public String generateToken(User user) {
	   return Jwts.builder()
	   .setSubject(user.getUsername())
	   .setIssuedAt(new Date())
	   .setExpiration(new Date(System.currentTimeMillis()+1000*3600))
	   .signWith(secretkey)
	   .compact();
   }
   
   public boolean validateToken(UserDetails user,String token) {
	   return extractNetid(token).equals(user.getUsername());
   }
   
   public String extractNetid(String token) {
	   return Jwts.parserBuilder()
	   .setSigningKey(secretkey).build()
	   .parseClaimsJws(token)
	   .getBody()
	   .getSubject();
   }
}
