package de.application.hello;

public class TranslatorToUpperImpl implements Translator{

	@Override
	public String translate(String message) {
		return message.toUpperCase();
	}

}
