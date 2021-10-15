package de.atruvia.webapp.application;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Setter;

@Configuration
@PropertySource("classpath:dozent.properties")
@ConfigurationProperties(prefix ="dozent")
@Setter
public class DozentConfig {
	
	//@Value("${dozent.name}")
	private String name;
	private String thema;
	
	@Bean
	public Dozent createDozent() {
		return Dozent.builder().name(name).thema(thema).build();
	}

}
