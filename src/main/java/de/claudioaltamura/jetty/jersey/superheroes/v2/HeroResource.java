package de.claudioaltamura.jetty.jersey.superheroes.v2;

import javax.ws.rs.Path;

@Path("v2/heroes")
public class HeroResource extends de.claudioaltamura.jetty.jersey.superheroes.v1.HeroResource {

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
