package de.claudioaltamura.jetty.jersey.superheroes;

import java.net.URI;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Creates a superheroes app instance.
 */
@ApplicationPath("/superheroes/api")
public class SuperHeroesApp extends Application {

	private static final Logger LOG = Logger.getLogger(SuperHeroesApp.class);

	private static final int PORT = 8080;

	public static void main(String[] args) {

		//TODO uri relative just CONTEXT_PATH
		URI baseUri = UriBuilder.fromUri("http://localhost/" ).port(PORT).build();
		ResourceConfig config = new SuperHeroesApplication();
		config.packages("de.claudioaltamura.jetty.jersey");
		Server jettyServer = JettyHttpContainerFactory.createServer(baseUri, config);
		try {
			jettyServer.start();
			jettyServer.join();
			LOG.info("superheroes application started");
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			jettyServer.destroy();
			LOG.info("superheroes application stopped");
		}
	}

}
