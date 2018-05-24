package de.claudioaltamura.jetty.jersey.superheroes.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import de.claudioaltamura.jetty.jersey.superheroes.service.HeroCreationException;

public class HeroCreationExceptionMapper implements ExceptionMapper<HeroCreationException> {

	@Override
	public Response toResponse(HeroCreationException exception) {
		int status = 400;//TODO status 
		Errors errors = new Errors();
		errors.add(new Errors.ErrorBuilder()
				.status(status)
				.detail(exception.getMessage())
				.build());
		//TODO https://stackoverflow.com/questions/18623667/serializing-array-with-jackson
		return Response.status(status).entity(errors).build();
	}

}
