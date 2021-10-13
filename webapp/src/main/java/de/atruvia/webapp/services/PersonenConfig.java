package de.atruvia.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonenConfig {

	
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

}
