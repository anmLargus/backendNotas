package com.app.backendNotas.security.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {
	
	// LLAVE_MUY_SECRETA => [Base64] => TExBVkVfTVVZX1NFQ1JFVEE=
		private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEE=";

		public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas

		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
		}

		public Date extractExpiration(String token) {
			return extractClaim(token, Claims::getExpiration);
		}

		public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
			return claimsResolver.apply(extractAllClaims(token));
		}

		private Claims extractAllClaims(String token) {
			return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
		}

		private Boolean isTokenExpired(String token) {
			return extractExpiration(token).before(new Date());
		}
		
		// Recibe la info del usuario y crea el token
		public String generateToken(UserDetails userDetails) {
			Map<String, Object> claims = new HashMap<>();
			// Agregando informacion adicional como "claim"
			var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
			claims.put("rol", rol);
			return createToken(claims, userDetails.getUsername());
		}

		private String createToken(Map<String, Object> claims, String subject) {

			return Jwts.builder()
					.setClaims(claims)
					.setSubject(subject) //setea quien es el usuario
					.setIssuedAt(new Date(System.currentTimeMillis())) // setea cuando se genero ese token
					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) //Cuando expira
					.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).compact(); //Con que llave se firma
		}

		public boolean validateToken(String token, UserDetails userDetails) {
			final String username = extractUsername(token);
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		}

}