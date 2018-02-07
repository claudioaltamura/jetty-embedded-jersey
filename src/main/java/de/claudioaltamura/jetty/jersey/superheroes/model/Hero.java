package de.claudioaltamura.jetty.jersey.superheroes.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

//TODO lombok nutzen
@SuppressWarnings("serial")
@XmlRootElement
public class Hero implements Serializable {

	private int id;
	private String name;
	private String realname;

	public Hero()
	{
	}

	public Hero(String name, String realname) {
		this.name = name;
		this.realname = realname;
	}

	public static Hero create(int id, String name, String realname) {
		Hero hero = new Hero(name, realname);
		hero.setId(id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		return "Hero [id=" + id + ", name=" + name + ", realname=" + realname + "]";
	}

}
