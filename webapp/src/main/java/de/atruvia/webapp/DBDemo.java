package de.atruvia.webapp;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.repositories.entities.PersonEntity;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class DBDemo {
	
	private final PersonenRepository repo;
	
	
	@PostConstruct
	public void init() {
//		PersonEntity p = repo.findById("dd39801b-60e7-4308-910c-c6a505d322c3").get();
//		p.setVorname("Elisabeth");
//		System.out.println(p);
		
//		PersonEntity p = PersonEntity.builder().id("dd39801b-60e7-4308-910c-c6a505d322c4").vorname("john").nachname("Doe").build();
//		p = repo.save(p);
//		p.setVorname("Jane");
		
		var tinies = repo.findTinies();
		tinies.forEach(System.out::println);
	}

}
