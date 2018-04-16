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
//@ApplicationPath("/superheroes/api")
public class SuperHeroesApp extends Application {

	private static final Logger LOG = Logger.getLogger(SuperHeroesApp.class);

	public static final String BASE_URI = "localhost";

	public static final int PORT = 8080;

	public static Server startServer() {
		//TODO uri relative just CONTEXT_PATH
		URI baseUri = UriBuilder.fromUri("http://" + BASE_URI + "/" ).port(PORT).build();
		ResourceConfig config = new SuperHeroesConfig();
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
		return jettyServer;
	}

	public static void main(String[] args) {
		startServer();
	}

}
