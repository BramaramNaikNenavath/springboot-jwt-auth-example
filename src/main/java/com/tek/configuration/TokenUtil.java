package com.tek.configuration;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil implements Serializable {

	private static final long serialVersionUID = 5963526648460670478L;
	public static final long JWT_TOKEN_VALIDITY = 0 * 1 * 60;

	@Value("${jwt.key}")
	private String secret;

	public String generateToken(UserDetails userDetails) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - generateToken() ");
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - doGenerateToken() ");
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// retrieve username from jwt token
	public String getUserNameFromToken(String token) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - getUserNameFromToken() ");
		return getClaimFromToken(token, Claims::getSubject);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - getClaimFromToken() ");
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - getAllClaimsFromToken() ");
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - validateToken() ");
		System.out.println(token);
		final String username = getUserNameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - isTokenExpired() ");
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		System.out.println(">>>>>>>>>> method -- TokenUtil - getExpirationDateFromToken() ");
		return getClaimFromToken(token, Claims::getExpiration);
	}

}
