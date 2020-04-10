package com.mojdan.app.jwt.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.mojdan.app.jwt.security.Constant.SUPER_SECRET_KEY;
import static com.mojdan.app.jwt.security.Constant.EXPIRATION;
import static com.mojdan.app.jwt.security.Constant.HEADER_AUTHORIZACION_KEY;
import static com.mojdan.app.jwt.security.Constant.TOKEN_BEARER_PREFIX;
import static com.mojdan.app.jwt.security.Constant.ISSUER_INFO;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			request.getInputStream();
			
			LoginForm form = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					form.getUsername(), form.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		Map<String, Object> claims = new HashMap<String, Object>();
		
		claims.put("userDetails", (User)auth.getPrincipal());		
		String token = doGenerateToken(claims, ((User)auth.getPrincipal()).getUsername());			
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);				
	}
	
	private static String doGenerateToken(Map<String, Object> claims, String subject) {
		  final Date createdDate = new Date();
		  final Date expirationDate = new Date(createdDate.getTime() + EXPIRATION * 1000);
		  
		  return Jwts.builder()					 
		      .setClaims(claims)
		      .setSubject(subject)
		      .setIssuedAt(createdDate)
		      .setIssuer(ISSUER_INFO)
		      .setExpiration(expirationDate)
		      .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY)
		      .compact();
	}

}