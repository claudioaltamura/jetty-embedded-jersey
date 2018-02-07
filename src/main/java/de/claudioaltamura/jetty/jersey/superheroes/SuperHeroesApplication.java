package de.claudioaltamura.jetty.jersey.superheroes;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson1.Jackson1Feature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class SuperHeroesApplication {

	private static final Logger LOG = Logger.getLogger(SuperHeroesApplication.class);
	
	public static void main(String[] args) {

		ResourceConfig config = new ResourceConfig();
		config.register(Jackson1Feature.class);
		config.packages("de.claudioaltamura.jetty.jersey");
		ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));

		Server server = new Server(8080);
		server.addBean(LOG);
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servletHolder, "/superheroes/api/*");
		
		DefaultServlet defaultServlet = new DefaultServlet();
		ServletHolder defaultServletHolder = new ServletHolder("default", defaultServlet);
		defaultServletHolder.setInitParameter("resourceBase", "./src/webapp/");
		context.addServlet(defaultServletHolder, "/*");

		// ServletContextHandler context = new
		// ServletContextHandler(ServletContextHandler.SESSIONS);
		// context.setContextPath("/");
		//
		// Server server = new Server(8080);
		// server.setHandler(context);
		//
		// ServletHolder servlet =
		// context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class,
		// "/*");
		// servlet.setInitOrder(0);
		//
		// // Tells the Jersey Servlet which REST service/class to load.
		// servlet.setInitParameter("jersey.config.server.provider.classnames",
		// "de.claudioaltamura.jetty.jersey");

		try {
			server.start();
			server.join();
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			server.destroy();
		}
	}

}
