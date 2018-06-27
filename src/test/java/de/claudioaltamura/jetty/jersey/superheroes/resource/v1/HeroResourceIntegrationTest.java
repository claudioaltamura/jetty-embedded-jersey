package de.claudioaltamura.jetty.jersey.superheroes.resource.v1;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import de.claudioaltamura.jetty.jersey.superheroes.SuperHeroesApp;
import de.claudioaltamura.jetty.jersey.superheroes.SuperHeroesConfig;
import de.claudioaltamura.jetty.jersey.superheroes.entity.Hero;
import de.claudioaltamura.jetty.jersey.superheroes.exception.Error;

public class HeroResourceIntegrationTest extends JerseyTest {

	@Override
	protected Application configure() {

		ResourceConfig config = new SuperHeroesConfig();
		enable(ServerProperties.BV_SEND_ERROR_IN_RESPONSE);
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		return config;
	}

	@Override
	protected URI getBaseUri() {
		return SuperHeroesApp.BASE_URI;
	}

	@Test
	public void testGetHero() throws Exception {
		final WebTarget target = target().path("v1/heroes/1");

		final Response response = target.request().get();
		final Hero hero = response.readEntity(Hero.class);

		assertEquals(200, response.getStatus());
		assertEquals(hero.getId(), 1);
		assertEquals(hero.getName(), "Superman");
	}

	@Test(expected = NotFoundException.class)
	public void testGetNonExistingHero() throws Exception {
		client()
			.target(SuperHeroesApp.BASE_URI)
			.path("v1/heroes/99")
			.request(MediaType.APPLICATION_JSON)
			.get(Hero.class);
	}

	@Test
	public void testCreateNotSuccessfullHero() {
    	final Hero hero = new Hero();
    	hero.setName("Test Hero");

		final WebTarget target = target().path("v1/heroes");
		final Response response = 
				target.request(MediaType.APPLICATION_JSON).post(Entity.entity(hero, MediaType.APPLICATION_JSON));

		assertEquals(500, response.getStatus());
		final Error error = response.readEntity(Error.class);
		assertEquals("[City is empty., Real name is empty.]", error.getDetail());
	}	

	@Test
	public void testCreateSuccessfullHero() {
    	final Hero hero = Hero.create(0, "Test Hero", "Peter Klein", "Hamburg");

		final WebTarget target = target().path("v1/heroes");
		final Response response = target
    			.request(MediaType.APPLICATION_JSON)
    			.post(Entity.entity(hero, MediaType.APPLICATION_JSON));
		
		assertEquals(201, response.getStatus());
	}	
	
}