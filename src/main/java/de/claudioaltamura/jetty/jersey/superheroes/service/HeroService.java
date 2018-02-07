package de.claudioaltamura.jetty.jersey.superheroes.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import de.claudioaltamura.jetty.jersey.superheroes.model.Hero;

public class HeroService {

	private static Map<Integer, Hero> HEROES = new HashMap<>();
	static
	{
		HEROES.put(0, Hero.create(0, "Spidermen", "Peter Parker"));
		HEROES.put(1, Hero.create(1, "Batman", "Bruce Wa1yne"));
	}

	private static final AtomicInteger INDEX = new AtomicInteger(2);

	public Hero findById(int id) {
		//TODO add Null? Exception Handling
		return HEROES.get(id);
	}

	public Collection<Hero> findAll() {
		return Collections.unmodifiableMap(HEROES).values();
	}

	public int add(Hero hero) {
		//TODO Is the return type correct?
		int id = INDEX.getAndIncrement();
		hero.setId(id);
		HEROES.putIfAbsent(id, hero);
		
		return id;
	}

	public boolean deleteById(int id) {
		//TODO Is return type correct?
		if(HEROES.containsKey(id)) {
			Hero hero = HEROES.get(id);
			HEROES.remove(id,hero);
			return true;
		} else {
			return false;
		}
	}

}
