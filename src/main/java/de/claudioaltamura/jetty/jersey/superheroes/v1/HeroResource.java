package de.claudioaltamura.jetty.jersey.superheroes.v1;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import de.claudioaltamura.jetty.jersey.superheroes.model.Hero;
import de.claudioaltamura.jetty.jersey.superheroes.service.HeroService;

@Path("v1/heroes")
public class HeroResource {

	private static final Logger LOG = Logger.getLogger(HeroResource.class);

	protected HeroService heroService;

	// TODO Replace with CDI
	public HeroResource() {
		heroService = new HeroService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Hero> findall() {
		//TODO Change return type to Response
		LOG.info("find all persons");
		return heroService.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") int id) {
		LOG.info("find=" + id);
		Hero hero = heroService.findById(id);

		if (hero == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok().build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Hero person) {
		LOG.info("create=" + person);

		Integer index = heroService.create(person);

		return Response.status(201).entity(index).build();
	}

	// @PUT

	@PATCH
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response patch(@PathParam("id") int id, String realname) {
		LOG.info("patch=" + id);

		if(heroService.exists(id)) {
			Hero hero2Updated = heroService.findById(id);
			hero2Updated.setRealname(realname);
			heroService.update(hero2Updated);
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		LOG.info("delete=" + id);

		if(heroService.exists(id)) {
			heroService.delete(id);
			return Response.status(204).build();
		} else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

}
