
package de.claudioaltamura.jetty.jersey.superheroes.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import de.claudioaltamura.jetty.jersey.superheroes.service.HeroNotFoundException;

public class HeroNotFoundExceptionMapper implements ExceptionMapper<HeroNotFoundException> {
	
	@Override
	public Response toResponse(HeroNotFoundException exception) {
		int status = 404;//TODO status 
		Errors errors = new Errors();
		errors.add(new Errors.ErrorBuilder()
				.status(status)
				.detail(exception.getMessage())
				.build());
		//TODO https://stackoverflow.com/questions/18623667/serializing-array-with-jackson
		return Response.status(status).entity(errors).build();
	}

}
