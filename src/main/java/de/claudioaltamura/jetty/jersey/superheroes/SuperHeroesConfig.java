package de.claudioaltamura.jetty.jersey.superheroes;

import java.net.URI;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import de.claudioaltamura.jetty.jersey.superheroes.exception.HeroCreationExceptionMapper;
import de.claudioaltamura.jetty.jersey.superheroes.exception.HeroNotFoundExceptionMapper;

/**
 * Configures an JAX-RS Application.
 * 
 * @see https://jersey.github.io/documentation/latest/deployment.html#deployment.servlet.3
 */
public class SuperHeroesConfig extends ResourceConfig {

	public SuperHeroesConfig() {
		super(JacksonFeature.class);

		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);

		packages("de.claudioaltamura.jetty.jersey");
		
		register(HeroCreationExceptionMapper.class);
		register(HeroNotFoundExceptionMapper.class);
	}
	
	

}
