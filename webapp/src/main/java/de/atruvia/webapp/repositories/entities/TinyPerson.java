package de.atruvia.webapp.repositories.entities;

public class TinyPerson {
	
	private final String id;
	private final String nachname;
	
	@Override
	public String toString() {
		return "TinyPerson [id=" + id + ", nachname=" + nachname + "]";
	}

	public String getId() {
		return id;
	}

	public String getNachname() {
		return nachname;
	}

	public TinyPerson(String id, String nachname) {
		this.id = id;
		this.nachname = nachname;
	}
	
	

}
