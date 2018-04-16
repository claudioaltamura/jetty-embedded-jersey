package de.claudioaltamura.jetty.jersey.superheroes.service;

@SuppressWarnings("serial")
public class HeroNotFoundException extends RuntimeException {

	public HeroNotFoundException(String message) {
		super(message);
	}

}
