package de.application.hello;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@Lazy
public class Hello {
	
	
	private String message ;
	
	
	private final Translator translator;
	
	
	//@Autowired
	public Hello( @Qualifier("upper") Translator translator, @Value("tu'lu'chugh") String message) {
		this.message = message;
		this.translator = translator;	
		System.out.println("Ctor: " + message);
	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@PostConstruct
	public void init() {
		System.out.println(translator.translate(message));
		System.out.println(translator.translate("Post-Init"));
	}

	@PreDestroy
	public void close() {
		System.out.println("Und Tschüss");
		
	}
	
}
