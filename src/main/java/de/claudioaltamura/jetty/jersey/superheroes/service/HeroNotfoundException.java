package de.claudioaltamura.jetty.jersey.superheroes.service;

@SuppressWarnings("serial")
public class HeroNotfoundException extends RuntimeException {

	public HeroNotfoundException(String message) {
		super(message);
	}

}
