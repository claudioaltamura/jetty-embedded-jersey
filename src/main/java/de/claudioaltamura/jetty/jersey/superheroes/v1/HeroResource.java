package de.claudioaltamura.jetty.jersey.superheroes.v1;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

	protected HeroService heroServivce;

	//TODO Replace with CDI
	public HeroResource() {
		heroServivce = new HeroService();
	}

	@PostConstruct
	public void init() {
		LOG.debug("init");
	}

	//TODO get example with filtering, sorting, searching
	///filtering tickets?state=open
	//sortring GET /tickets?sort=created_at,priority
	//searching GET /employees?query=Paul
	//HINT: Query Parameter example
	//pagination /employees?offset=30&limit=15 # returns the employees 30 to 45
	
	//Updates & creation should return a resource representation
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Hero> findall() {
		return heroServivce.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//HINT: Path Parameter example
	public Hero find(@PathParam("id") int id) {
		return heroServivce.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Hero person) {
		Integer index = heroServivce.add(person);
		return Response.status(200).entity(index).build();
	}

	// @PUT

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		//TODO What should Return HTTP DELETE? 200 or 204
		LOG.info("delete="+id);
		heroServivce.deleteById(id);
		//NO Content
		return Response.status(204).build();
	}

}
