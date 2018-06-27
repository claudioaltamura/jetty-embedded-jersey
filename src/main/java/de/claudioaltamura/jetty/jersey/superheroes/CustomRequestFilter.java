package de.claudioaltamura.jetty.jersey.superheroes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.glassfish.jersey.message.internal.ReaderWriter;

public class CustomRequestFilter implements ContainerRequestFilter {

	private static final Logger LOG = Logger.getLogger(CustomRequestFilter.class);

	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LOG.info("Entering in Resource : /" + requestContext.getUriInfo().getPath());
		LOG.info("Method Name : " + resourceInfo.getResourceMethod().getName());
		LOG.info("Class : " + resourceInfo.getResourceClass().getCanonicalName());
		
		LOG.info(getQueryParameters(requestContext));
		LOG.info(getMethodAnnotations());
		LOG.info(getRequestHeader(requestContext));

		String entity = readEntityStream(requestContext);
		if (null != entity && entity.trim().length() > 0) {
			LOG.info("Entity Stream : " + entity);
		}
	}

	private String getQueryParameters(ContainerRequestContext requestContext) {
		Iterator<String> iterator = requestContext.getUriInfo().getPathParameters().keySet().iterator();
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext()) {
			String name = iterator.next();
			List<String> obj = requestContext.getUriInfo().getPathParameters().get(name);
			String value = null;
			if (null != obj && !obj.isEmpty()) {
				value = obj.get(0);
			}
			sb
				.append("name ")
				.append(name)
				.append("value ")
				.append(value)
				.append(" ");
		}
		return sb.toString();
	}

	private String getMethodAnnotations() {
		Annotation[] annotations = resourceInfo.getResourceMethod().getDeclaredAnnotations();

		StringBuilder sb = new StringBuilder();
		if (annotations != null && annotations.length > 0) {
			for (Annotation annotation : annotations) {
				sb.append(annotation);
			}
		}

		return sb.toString();
	}

	private String getRequestHeader(ContainerRequestContext requestContext) {
		Iterator<String> iterator;
		StringBuilder sb = new StringBuilder();
		sb.append("[")
			.append(requestContext.getMethod())
			.append("]");
		iterator = requestContext.getHeaders().keySet().iterator();
		while (iterator.hasNext()) {
			String headerName = iterator.next();
			String headerValue = requestContext.getHeaderString(headerName);
			sb.append(headerName)
				.append(": ")
				.append(headerValue);

		}
		return sb.toString();
	}

	private String readEntityStream(ContainerRequestContext requestContext) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		final InputStream inputStream = requestContext.getEntityStream();
		final StringBuilder builder = new StringBuilder();
		try {
			ReaderWriter.writeTo(inputStream, outStream);
			byte[] requestEntity = outStream.toByteArray();
			if (requestEntity.length == 0) {
				builder.append("");
			} else {
				builder.append(new String(requestEntity));
			}
			requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
		} catch (IOException ex) {
			LOG.info("----Exception occurred while reading entity stream : " + ex.getMessage());
		}
		return builder.toString();
	}


}
