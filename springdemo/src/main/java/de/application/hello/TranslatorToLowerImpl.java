package de.application.hello;

public class TranslatorToLowerImpl implements Translator {

	@Override
	public String translate(String message) {
		
		return message.toLowerCase();
	}

}
