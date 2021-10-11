package de.application.hello;

public class Hello {
	
	private String message = "Hallo Universum";
	
	private Translator translator;
	
	
	
	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	public Hello() {
		System.out.println("Ctor: " + message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void init() {
		System.out.println(translator.translate(message));
		System.out.println(translator.translate("Post-Init"));
	}

	
	public void close() {
		System.out.println("Und Tschüss");
		
	}
	
}
