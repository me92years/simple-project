package com.test.project.provider;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtTokenProvider {

  private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

  public static String returnAccessToken(Authentication authentication) {
    UserDetails uDetails = (UserDetails) authentication.getPrincipal();
    String accessToken = createAccessToken(uDetails.getUsername(), uDetails.getAuthorities(), 10);
    return accessToken;
  }

  private static String createAccessToken(String username, Collection<? extends GrantedAuthority> authorities, int minit) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("alg", ALGORITHM);
    headers.put("typ", "token");
    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", authorities);
    return Jwts.builder()
    .setHeader(headers)
    .setSubject(username)
    .setClaims(claims)
    .setExpiration(new Date(System.currentTimeMillis()+minit*60*1000))
    .compact();
  }
  
}
