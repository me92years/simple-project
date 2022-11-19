package com.test.project.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.test.project.model.user.User;
import com.test.project.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	private final UserService userService;
	private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
	private static final String secret = "qwertyuiopasdfghjklzxcvbnmqwerty";
	
	public String returnAccessToken(Authentication authentication) {
		UserDetails uDetails = (UserDetails) authentication.getPrincipal();
		String accessToken = createAccessToken(uDetails.getUsername(), uDetails.getAuthorities(), 10);
		return accessToken;
	}

	private String createAccessToken(String username, Collection<? extends GrantedAuthority> authorities, int minit) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("alg", ALGORITHM);
		headers.put("typ", "token");
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", authorities);
		return Jwts.builder()
				.setHeader(headers)
				.setSubject(username)
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + minit * 60 * 1000))
				.signWith(ALGORITHM, secret)
				.compact();
	}

	public boolean validateJwtToken(HttpServletRequest request, String accessToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(accessToken);
			return true;
		} catch (MalformedJwtException e) {
			request.setAttribute("exception", "MalformedJwtException");
		} catch (ExpiredJwtException e) {
			request.setAttribute("exception", "ExpiredJwtException");
		} catch (UnsupportedJwtException e) {
			request.setAttribute("exception", "UnsupportedJwtException");
		} catch (IllegalArgumentException e) {
			request.setAttribute("exception", "IllegalArgumentException");
		}
		return false;
	}

	public Authentication getAuthentication(String accessToken) {
		String username = this.getUserDetail(accessToken);
		UserDetails userDetails = userService.loadUserByUsername(username);
		User user = (User) userDetails;
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	private String getUserDetail(String accessToken) {
		return (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(accessToken).getBody().getSubject();
	}

}
