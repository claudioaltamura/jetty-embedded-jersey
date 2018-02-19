package de.claudioaltamura.jetty.jersey.superheroes;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Creates superheroe instance.
 */
public class SuperHeroesApp {

	private static final Logger LOG = Logger.getLogger(SuperHeroesApp.class);

	private static final int PORT = 8080;
	private static final String CONTEXT_PATH = "/*";
	private static final String SERVLET_PATH = "/superheroes/api/*";

	public static void main(String[] args) {

		ResourceConfig config = new SuperHeroesApplication();
		config.packages("de.claudioaltamura.jetty.jersey");

		ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));

		Server server = new Server(PORT);
		server.addBean(LOG);
		ServletContextHandler context = new ServletContextHandler(server, CONTEXT_PATH);
		context.addServlet(servletHolder, SERVLET_PATH);

		DefaultServlet defaultServlet = new DefaultServlet();
		ServletHolder defaultServletHolder = new ServletHolder("default", defaultServlet);
		defaultServletHolder.setInitParameter("resourceBase", "./src/webapp/");
		context.addServlet(defaultServletHolder, "/*");

		try {
			server.start();
			server.join();
			LOG.info("superheroes application started");
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			server.destroy();
			LOG.info("superheroes application stopped");
		}
	}

}
