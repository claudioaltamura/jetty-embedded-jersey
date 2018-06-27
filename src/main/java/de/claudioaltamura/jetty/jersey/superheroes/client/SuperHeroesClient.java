package de.claudioaltamura.jetty.jersey.superheroes.client;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.claudioaltamura.jetty.jersey.superheroes.entity.Hero;

public class SuperHeroesClient {

    private static final String URI = "http://localhost:8080/v1";
    
    private Client client = ClientBuilder.newClient();

    public Hero getHero(long id) {
    	return client
    			.target(URI)
    			.path("heroes")
    			.path(Long.toString(id))
    			.request(MediaType.APPLICATION_JSON)
    			.get(Hero.class);
    }
    
    public long addHero(Hero hero) throws Exception {
    	Response response = client
    			.target(URI)
    			.path("heroes")
    			.request(MediaType.APPLICATION_JSON)
    			.post(Entity.entity(hero, MediaType.APPLICATION_JSON));
    	
    	if(response != null && response.getStatus() >= 400)
    	{
    		throw new Exception(response.readEntity(String.class));
    	}
    	
    	return response.readEntity(Long.class);
    }
        
	public static void main(String[] args) {
		SuperHeroesClient superHeroesClient = new SuperHeroesClient();
		
		//Get
		Hero hero = superHeroesClient.getHero(1);
		System.out.println(hero);

		//Doesn't exists
		long notFoundId = 100;
		try {
			superHeroesClient.getHero(notFoundId);
		} catch(NotFoundException nfe) {
			System.out.println(String.format("Hero doesn't exists with id=%d",notFoundId));
		}
		
		//Put
		Hero newHero = new Hero();
		newHero.setCity("Hamburg");
		newHero.setRealname("Ham Burg");
		long newId;
		try {
			newId = superHeroesClient.addHero(newHero);
			newHero.setId(newId);
			System.out.println(newHero);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
