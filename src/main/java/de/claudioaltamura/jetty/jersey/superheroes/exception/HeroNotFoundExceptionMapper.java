package de.claudioaltamura.jetty.jersey.superheroes.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.Logger;

import de.claudioaltamura.jetty.jersey.superheroes.service.HeroNotFoundException;

public class HeroNotFoundExceptionMapper implements ExceptionMapper<HeroNotFoundException> {

	private static final Logger LOG = Logger.getLogger(HeroNotFoundExceptionMapper.class);
	
	@Override
	public Response toResponse(HeroNotFoundException exception) {
		LOG.debug(exception.getMessage());
		//TODO add json error response
		return Response.status(404).entity(exception.getMessage()).build();
	}

}
