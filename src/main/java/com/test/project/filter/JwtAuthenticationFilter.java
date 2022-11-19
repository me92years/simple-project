package com.test.project.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.project.provider.JwtTokenProvider;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider provider;
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenprovider) {
		this.provider = jwtTokenprovider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String accessToken = request.getHeader("Authorization");
		System.out.println(accessToken);
		if (accessToken != null && provider.validateJwtToken(request, accessToken)) {
			Authentication authentication = provider.getAuthentication(accessToken);
			
		}
		
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/api/user/auth");
	}

}
