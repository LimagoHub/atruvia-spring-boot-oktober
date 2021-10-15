package de.atruvia.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
	
	@Pointcut("execution(public * de.atruvia.webapp.controllers.PersonenController.*(..))")
	public void personControllerMethods(){}
	
	@Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)" )
    public void restControllerMethodes() {}

}
