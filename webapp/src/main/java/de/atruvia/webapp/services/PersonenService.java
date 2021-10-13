package de.atruvia.webapp.services;

import java.util.Optional;

import de.atruvia.webapp.services.models.Person;

public interface PersonenService {

	/*
	 * person null -> PSE
	 * vorname null -> PSE
	 * vorname too short -> PSE
	 * nachname null -> PSE
	 * nachname too short -> PSE
	 * 
	 * vorname Attila ->PSE
	 * 
	 * technische Exception -> PSE
	 * 
	 * Happy Day -> Person an Repo übergeben
	 * 
	 */
	boolean speicher(Person person) throws PersonenServiceException;

	boolean loeschen(Person person) throws PersonenServiceException;

	boolean loeschen(String id) throws PersonenServiceException;

	Optional<Person> findeNachId(String id) throws PersonenServiceException;

	Iterable<Person> findeAlle() throws PersonenServiceException;

}