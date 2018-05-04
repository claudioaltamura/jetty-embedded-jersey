package de.claudioaltamura.jetty.jersey.superheroes;

import java.net.URI;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Creates a superheroes app instance.
 */
public class SuperHeroesApp extends Application {

	private static final Logger LOG = Logger.getLogger(SuperHeroesApp.class);

	public static final String HOST = "localhost";

	public static final int PORT = 8080;

	public static final URI BASE_URI = UriBuilder.fromUri("http://" + HOST + "/" ).port(PORT).build();
	
	public static Server startServer() {
		ResourceConfig config = new SuperHeroesConfig();
		return SuperHeroesApp.startServer(config);
	}
	
	public static Server startServer(ResourceConfig config) {
		Server jettyServer = JettyHttpContainerFactory.createServer(SuperHeroesApp.BASE_URI, config);

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
		return jettyServer;
	}

	public static void main(String[] args) {
		startServer();
	}

}
