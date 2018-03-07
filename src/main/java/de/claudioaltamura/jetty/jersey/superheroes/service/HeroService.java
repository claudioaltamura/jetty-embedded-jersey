package de.claudioaltamura.jetty.jersey.superheroes.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

import de.claudioaltamura.jetty.jersey.superheroes.model.Hero;

//@ApplicationScoped
public class HeroService {

	private static Map<Long, Hero> HEROES = new ConcurrentHashMap<>();
	static
	{
		HEROES.put(0L, Hero.create(0, "Spidermen", "Peter Parker"));
		HEROES.put(1L, Hero.create(1, "Batman", "Bruce Wa1yne"));
	}

	private static final AtomicLong INDEX = new AtomicLong(2);


	public Collection<Hero> findAll() {
		return Collections.unmodifiableMap(HEROES).values();
	}

	public long create(Hero hero) throws HeroCreationException {
		//TODO create a new hero and copy the properties
		long id = INDEX.getAndIncrement();
		hero.setId(id);

		Hero existingHero = HEROES.putIfAbsent(id, hero);
		if(existingHero == null)
		{
			throw new HeroCreationException("couldn't create hero="+hero);
		}
		
		return id;
	}

	public Hero findById(long id) throws HeroNotfoundException {
		if(!exists(id))
		{
			throw new HeroNotfoundException("Hero=" + id + " not found");
		}

		return HEROES.get(id);
	}

	public void update(Hero hero2Update) throws HeroNotfoundException {
		long id = hero2Update.getId();
		if(!exists(id)) {
			throw new HeroNotfoundException("Hero=" + id + " not found");
		}

		HEROES.put(hero2Update.getId(), hero2Update);
	}

	public void updateRealName(long id, String realname) throws HeroNotfoundException {
		if(!exists(id)) {
			throw new HeroNotfoundException("Hero=" + id + " not found");
		}
		
		Hero hero2Updated = findById(id);
		hero2Updated.setRealname(realname);
	}

	public void delete(long id) throws HeroNotfoundException {
		if(!exists(id)) {
			throw new HeroNotfoundException("Hero=" + id + " not found");
		}

		Hero hero = HEROES.get(id);
		HEROES.remove(id,hero);
	}

	public boolean exists(long id) {
		return HEROES.containsKey(id);
	}

}
