package de.atruvia.webapp.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.atruvia.webapp.repositories.SchweineRepository;
import de.atruvia.webapp.services.SchweineService;
import de.atruvia.webapp.services.impl.SchweineServiceImpl;
import de.atruvia.webapp.services.mapper.SchweinMapper;

@Configuration
public class SchweineConfig {

	
//	@Bean
//	public SchweineService createSchweineService(SchweineRepository repo, SchweinMapper mapper) {
//		return new SchweineServiceImpl(repo, mapper);
//	}
}
