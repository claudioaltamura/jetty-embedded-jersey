package de.claudioaltamura.jetty.jersey.superheroes.resource.v1;


import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.claudioaltamura.jetty.jersey.superheroes.SuperHeroesApp;
import de.claudioaltamura.jetty.jersey.superheroes.entity.Hero;

public class HeroResourceTest {

	// @Override
	// protected Application configure() {
	// enable(TestProperties.LOG_TRAFFIC);
	// enable(TestProperties.DUMP_ENTITY);
	//
	// //return new
	// SuperHeroesApp().property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	// return new SuperHeroesApp();
	// }

	private Server server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		server = SuperHeroesApp.startServer();

		Client c = ClientBuilder.newClient();
		target = c.target(SuperHeroesApp.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testFind() {
		Hero responseMsg = target.path("v1/heroes").request().get(Hero.class);
		assertEquals("Got it!", responseMsg);
	}

}