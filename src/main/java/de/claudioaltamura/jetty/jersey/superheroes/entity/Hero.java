package de.claudioaltamura.jetty.jersey.superheroes.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
public class Hero implements Serializable {

	private long id;
	@NotNull(message = "{hero.name.not_empty}")
	private String name;
	@NotNull(message = "{hero.realname.not_empty}")
	private String realname;
	@NotNull(message = "{hero.city.not_empty}")
	private String city;
	
	public static Hero create(long id, String name, String realname, String city) {
		Hero hero = new Hero();
		hero.setId(id);
		hero.setName(name);
		hero.setRealname(realname);
		hero.setCity(city);
		
		return hero;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((realname == null) ? 0 : realname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hero other = (Hero) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", realname=" + realname + ", city=" + city + "]";
	}
	
}
