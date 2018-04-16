
package de.claudioaltamura.jetty.jersey.superheroes.resource.v1;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import de.claudioaltamura.jetty.jersey.superheroes.entity.Hero;
import de.claudioaltamura.jetty.jersey.superheroes.service.HeroCreationException;
import de.claudioaltamura.jetty.jersey.superheroes.service.HeroNotFoundException;
import de.claudioaltamura.jetty.jersey.superheroes.service.HeroService;

@Path("v1/heroes")
public class HeroResource {

	private static final Logger LOG = Logger.getLogger(HeroResource.class);

//	@Inject
	protected HeroService heroService;

	@PostConstruct
	public void init() {
		LOG.debug(this.getClass().getSimpleName() + " initialized");
//		// TODO Replace with CDI
		heroService = new HeroService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Hero> findall() {
		//TODO Change return type to Response ?
		LOG.info("find all heroes");

		return heroService.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") int id) throws HeroNotFoundException {
		LOG.info("find=" + id);
		Hero hero = heroService.findById(id);

		return Response.ok().entity(hero).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid Hero hero) throws HeroCreationException {
		LOG.info("create=" + hero);

		Long id = heroService.create(hero);

		return Response.status(Status.CREATED).entity(id).build();
	}

	@PUT
	@Path("{id}")
	public Response put(@PathParam("id") int id, @Valid Hero hero) throws HeroNotFoundException {
		LOG.info("replace=" + id);

		heroService.update(hero);

		return Response.ok().build();
	}

	@PATCH
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response patch(@PathParam("id") int id,@NotNull String realname) throws HeroNotFoundException {
		LOG.info("patch=" + id);

		heroService.updateRealName(id, realname);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) throws HeroNotFoundException {
		LOG.info("delete=" + id);

		heroService.delete(id);

		return Response.status(Status.NO_CONTENT).build();
	}

}
