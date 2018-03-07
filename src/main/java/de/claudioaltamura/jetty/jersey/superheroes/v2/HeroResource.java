package de.claudioaltamura.jetty.jersey.superheroes.v2;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

import org.apache.log4j.Logger;

@Path("v2/heroes")
public class HeroResource extends de.claudioaltamura.jetty.jersey.superheroes.v1.HeroResource {

	private static final Logger LOG = Logger.getLogger(HeroResource.class);

	@PostConstruct
	public void init() {
		LOG.debug(this.getClass().getSimpleName() + " initialized");
	}

	//https://www.logicbig.com/tutorials/java-ee-tutorial/jax-rs/using-query-param.html
	//TODO get example with filtering, sorting, searching
	///filtering tickets?state=open
	//sortring GET /tickets?sort=created_at,priority
	//searching GET /employees?query=Paul
	//HINT: Query Parameter example
	//pagination /employees?offset=30&limit=15 # returns the employees 30 to 45
	//Updates & creation should return a resource representation

	//@QueryParam
	//@PathParam
	//@FormParam
	
}
