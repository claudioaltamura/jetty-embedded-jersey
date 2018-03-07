package de.claudioaltamura.jetty.jersey.superheroes;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Configures an JAX-RS Application.
 * @see https://jersey.github.io/documentation/latest/deployment.html#deployment.servlet.3
 */
//@ApplicationPath("/superheroes/api")
public class SuperHeroesApplication extends ResourceConfig {

	public SuperHeroesApplication() {
		super(
			JacksonFeature.class
		);
	}
}
