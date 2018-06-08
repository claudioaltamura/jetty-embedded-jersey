package de.claudioaltamura.jetty.jersey.superheroes.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.Logger;

import de.claudioaltamura.jetty.jersey.superheroes.service.HeroCreationException;

public class HeroCreationExceptionMapper implements ExceptionMapper<HeroCreationException> {

	private static final Logger LOG = Logger.getLogger(HeroCreationExceptionMapper.class);

	@Override
	public Response toResponse(HeroCreationException exception) {
		Error error = new Error.ErrorBuilder()
							.status(400)
							.detail(exception.getMessage())
							.build();
		LOG.error(error);

		return Response.status(400).entity(error).build();
	}

}
