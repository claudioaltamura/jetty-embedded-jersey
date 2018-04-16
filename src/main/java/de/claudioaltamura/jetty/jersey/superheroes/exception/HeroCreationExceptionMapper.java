package de.claudioaltamura.jetty.jersey.superheroes.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import de.claudioaltamura.jetty.jersey.superheroes.service.HeroCreationException;

public class HeroCreationExceptionMapper implements ExceptionMapper<HeroCreationException> {

	@Override
	public Response toResponse(HeroCreationException exception) {
		//TODO Add JSON Error Response
		return Response.status(400).entity(exception.getMessage()).build();
	}

}
