package de.claudioaltamura.jetty.jersey.superheroes.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import de.claudioaltamura.jetty.jersey.superheroes.entity.Hero;

public class HeroService {

	private static Map<Long, Hero> HEROES = new ConcurrentHashMap<>();
	static {
		HEROES.put(1L, Hero.create(1, "Superman", "Kent Clark", "Metropolis"));
		HEROES.put(1L, Hero.create(2, "Spiderman", "Peter Parker", "New York"));
		HEROES.put(2L, Hero.create(3, "Batman", "Bruce Wa1yne", "Gotham City"));
		HEROES.put(3L, Hero.create(4, "Batgirl", "Girl ???", "Gotham City"));
	}

	private static final AtomicLong INDEX = new AtomicLong(2);

	public Collection<Hero> findAll() {
		return Collections.unmodifiableMap(HEROES).values();
	}

	public long create(Hero hero) throws HeroCreationException {
		long id = INDEX.getAndIncrement();
		hero.setId(id);

		Hero existingHero = HEROES.putIfAbsent(id, hero);
		if (existingHero == null) {
			throw new HeroCreationException("couldn't create hero=" + hero);
		}

		return id;
	}

	public Hero findById(long id) throws HeroNotFoundException {
		if (!exists(id)) {
			throw new HeroNotFoundException("Hero=" + id + " not found");
		}

		return HEROES.get(id);
	}

	public void update(Hero hero2Update) throws HeroNotFoundException {
		long id = hero2Update.getId();
		if (!exists(id)) {
			throw new HeroNotFoundException("Hero=" + id + " not found");
		}

		HEROES.put(hero2Update.getId(), hero2Update);
	}

	public void updateRealName(long id, String realname) throws HeroNotFoundException {
		if (!exists(id)) {
			throw new HeroNotFoundException("Hero=" + id + " not found");
		}

		Hero hero2Updated = findById(id);
		hero2Updated.setRealname(realname);
	}

	public void delete(long id) throws HeroNotFoundException {
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
		return HEROES.values().stream().filter(h -> city.equalsIgnoreCase(h.getCity())).collect(Collectors.toList());
	}

}
