package de.atruvia.webapp.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class TranslatorToUpperImpl implements Translator{

	@Override
	public String translate(String message) {
		return message.toUpperCase();
	}

}
