package de.atruvia.webapp.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TranslatorToLowerImpl implements Translator {

	@Override
	public String translate(String message) {
		
		return message.toLowerCase();
	}

}
