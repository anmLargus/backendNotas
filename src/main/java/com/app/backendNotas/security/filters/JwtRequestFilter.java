package com.app.backendNotas.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.backendNotas.security.services.JwtUtilService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Excepcion x recibir jwt expirado
		try {
		
		final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        
     // Si empieza con Bearer, Luego de bearer lee lo que sigue
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);// Obtiene
            username = jwtUtilService.extractUsername(jwt);// lo extrae  
        }
        
     // si existe el usuario y no hay un contexto de seguridad inicializado -> trae la info de ese usuario y valida el token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            // Si la validación pasa, construye el sujeto que hemos visto e inicializa SecurityContextHolder
            if (jwtUtilService.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
		} catch (ExpiredJwtException eje) {
			System.out.println(" Token expired ");
			log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
	        ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (SignatureException se) {
			System.out.println(" token no coincide ");
			log.info("Security exception for user {} - {}", se.getMessage());
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (IllegalArgumentException iae) {
			System.out.println(" jwt claims es vacío ");
			log.info("Security exception for user {} - {}", iae.getMessage());
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (Exception e) {
			System.out.println(" Alguna excepcion del jwt ");
			log.info("Security exception for user {} - {}", e.getMessage());
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

}
