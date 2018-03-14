package de.claudioaltamura.jetty.jersey.superheroes.service;

@SuppressWarnings("serial")
public class HeroCreationException extends RuntimeException {

	public HeroCreationException(String message) {
		super(message);
	}

}
