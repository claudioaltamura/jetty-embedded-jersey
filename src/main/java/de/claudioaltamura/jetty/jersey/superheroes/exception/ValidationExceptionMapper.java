package de.claudioaltamura.jetty.jersey.superheroes.exception;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
	
	private static final Logger LOG = Logger.getLogger(ValidationExceptionMapper.class);

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("ValidationMessages");

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		List<String> violationMessages = 
				exception.getConstraintViolations()
				.stream()
				.map(this::getViolationMessage).sorted().collect(Collectors.toList());
		
		Error error = new Error.ErrorBuilder()
				.status(400)
				.detail(violationMessages.toString())
				.build();
		LOG.error(error);
		
		return Response.status(400).entity(error).build();		
    }
	
	private String getViolationMessage(final ConstraintViolation<?> constraintViolation) {
		String resourceString = (String) constraintViolation.getConstraintDescriptor().getAttributes().get("message");
		return translate(resourceString);
	}

	private String translate(final String resourceKey) {
		String simpleKey = resourceKey.replaceAll("[{}]", "");
		return resourceBundle.getString(simpleKey); 
	}

}
