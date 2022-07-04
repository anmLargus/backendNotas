package com.app.backendNotas.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(" http://localhost:4200") //Le indico que origen está permitido  
				.allowedMethods("GET", "POST", "PUT", "DELETE") //Le indico los métodos 
				.allowCredentials(true);
	}

}
