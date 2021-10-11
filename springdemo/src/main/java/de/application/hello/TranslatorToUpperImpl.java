package de.application.hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upper")
public class TranslatorToUpperImpl implements Translator{

	@Override
	public String translate(String message) {
		return message.toUpperCase();
	}

}
