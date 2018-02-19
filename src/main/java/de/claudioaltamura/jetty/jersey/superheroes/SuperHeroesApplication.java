package de.claudioaltamura.jetty.jersey.superheroes;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Used to set resource and providers classes.
 */
public class SuperHeroesApplication extends ResourceConfig {

	public SuperHeroesApplication() {
		super(
			JacksonFeature.class
		);
	}
}
