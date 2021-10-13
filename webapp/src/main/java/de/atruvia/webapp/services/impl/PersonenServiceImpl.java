package de.atruvia.webapp.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.PersonenService;
import de.atruvia.webapp.services.PersonenServiceException;
import de.atruvia.webapp.services.mapper.PersonMapper;
import de.atruvia.webapp.services.models.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service

@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED)
@Slf4j
public class PersonenServiceImpl implements PersonenService {
	
	private final PersonMapper mapper;
	private final PersonenRepository repo;
	private final List<String> antipathen;
	
	
	
	public PersonenServiceImpl(PersonMapper mapper, PersonenRepository repo, @Qualifier("antipathen") List<String> antipathen) {
		this.mapper = mapper;
		this.repo = repo;
		this.antipathen = antipathen;
	}

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
	@Override
	public boolean speicher(Person person) throws PersonenServiceException {
		try {
			return speichernImpl(person);
			
		} catch (RuntimeException e) {
			log.error("Upps", e);
			throw new PersonenServiceException("Fehler in der Datenbank", e);
		}
	}

	private boolean speichernImpl(Person person) throws PersonenServiceException {
		checkPerson(person);
		
		boolean result = repo.existsById(person.getId());
		
		repo.save(mapper.convert(person));
		
		return result;
	}

	private void checkPerson(Person person) throws PersonenServiceException {
		validatePerson(person);
		
		businesscheck(person);
	}

	private void businesscheck(Person person) throws PersonenServiceException {
		if(antipathen.contains(person.getVorname()))throw new PersonenServiceException("Antipath");
	}

	private void validatePerson(Person person) throws PersonenServiceException {
		if(person == null) throw new PersonenServiceException("Parameter darf nicht null sein.");
		if(person.getVorname() == null || person.getVorname().length() < 2)throw new PersonenServiceException("Vorname muss min. 2 Zeichen enthalten");
		if(person.getNachname() == null || person.getNachname().length() < 2)throw new PersonenServiceException("Nachname muss min. 2 Zeichen enthalten");
	}

	@Override
	public boolean loeschen(Person person) throws PersonenServiceException {
		return loeschen(person.getId());
	}

	@Override
	public boolean loeschen(String id) throws PersonenServiceException {
		try {
			if(repo.existsById(id)) {
				repo.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new PersonenServiceException(e);
		}
	}
	@Override
	public Optional<Person> findeNachId(String id) throws PersonenServiceException{
		try {
			return repo.findById(id).map(mapper::convert);
		} catch (Exception e) {
			throw new PersonenServiceException(e);
		}
	}
	@Override
	public Iterable<Person> findeAlle() throws PersonenServiceException{
		try {
			return mapper.convert(repo.findAll());
		} catch (Exception e) {
			throw new PersonenServiceException(e);
		}
	}
	

}
