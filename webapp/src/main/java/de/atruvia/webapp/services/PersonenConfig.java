package de.atruvia.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.impl.PersonenServiceImpl;
import de.atruvia.webapp.services.mapper.PersonMapper;

@Configuration
public class PersonenConfig {

//	@Autowired
//	private PersonenRepository repo;
//	
//	@Autowired
//	private PersonMapper mapper;
	
	
	@Bean
	@Qualifier("antipathen")
	public List<String> getAntipathen() {
		return List.of("Attila","Peter","Paul","Mary");
	}

	@Bean
	@Qualifier("fruits")
	public List<String> getFruits() {
		return List.of("Banana","Apple","Strawberry","Cherry");
	}
	
//	@Bean
//	public PersonenService createPersonenService(PersonMapper mapper, PersonenRepository repo, @Qualifier("antipathen") List<String> antipathen) {
//		return new PersonenServiceImpl(mapper, repo, antipathen);
//	}

}
