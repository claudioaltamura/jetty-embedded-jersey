package de.claudioaltamura.jetty.jersey.superheroes.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.validation.Valid;

import de.claudioaltamura.jetty.jersey.superheroes.entity.Hero;

public class HeroService {

	public static final Map<Long, Hero> HEROES = new ConcurrentHashMap<>();
	static {
		HEROES.put(1L, Hero.create(1, "Superman", "Kent Clark", "Metropolis"));
		HEROES.put(2L, Hero.create(2, "Spiderman", "Peter Parker", "New York"));
		HEROES.put(3L, Hero.create(3, "Batman", "Bruce Wayne", "Gotham City"));
		HEROES.put(4L, Hero.create(4, "Batgirl", "Barbara Gordon", "Gotham City"));
		HEROES.put(5L, Hero.create(5, "Iron Man", "Anthony Edward", "New York"));
		HEROES.put(6L, Hero.create(6, "Thor", "Thor Odinson", "Asgard"));
		HEROES.put(7L, Hero.create(7, "Wolverine", "James Howlett", "Alberta"));
		HEROES.put(8L, Hero.create(8, "Captain America", "Steve Rogers", "New York"));
		HEROES.put(9L, Hero.create(9, "Hulk", "Robert Bruce Banner", "Dayton"));
	}

	public static final AtomicLong INDEX = new AtomicLong(HEROES.size() + 1);

	public Collection<Hero> findAll() {
		return Collections.unmodifiableMap(HEROES).values();
	}

	public long create(@Valid Hero hero){
		long id = INDEX.getAndIncrement();
		hero.setId(id);
		HEROES.put(id, hero);

		return id;
	}

	public Hero findById(long id) {
		if (!exists(id)) {
			throw new HeroNotFoundException("Hero=" + id + " not found");
		}

		return HEROES.get(id);
	}

	public void update(final int id, final Hero hero2Update) {
		if (!exists(id)) {
			throw new HeroNotFoundException("Hero=" + id + " not found");
		}

		hero2Update.setId(id);
		HEROES.put(hero2Update.getId(), hero2Update);
	}

	public void updateRealName(final long id, final String realname) {
		if (!exists(id)) {
			throw new HeroNotFoundException("Hero=" + id + " not found");
		}

		Hero hero2Updated = findById(id);
		hero2Updated.setRealname(realname);
	}

	public void delete(long id) {
		if (!exists(id)) {
			throw new HeroNotFoundException("Hero=" + id + " not found");
		}

		Hero hero = HEROES.get(id);
		HEROES.remove(id, hero);
	}

	public boolean exists(long id) {
		return HEROES.containsKey(id);
	}

	public List<Hero> findByCity(String city) {
		return HEROES
				.values()
				.stream()
				.filter(h -> city.equalsIgnoreCase(h.getCity()))
				.collect(Collectors.toList());
	}

}
