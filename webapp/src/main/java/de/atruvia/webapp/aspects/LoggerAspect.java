package de.atruvia.webapp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import de.atruvia.webapp.controllers.dtos.PersonDTO;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
	
	
	
	@Before(value = "Pointcuts.personControllerMethods() && args(personDTO,..)")
	public void logAdvice(JoinPoint joinPoint, PersonDTO personDTO) {
		log.error("Methode {} wird ausgeführt", joinPoint.getSignature().getName());
	}

	@AfterReturning(value = "Pointcuts.personControllerMethods()", returning = "retval")
	public void arAdvice(JoinPoint joinPoint, Object retval) {
		System.out.println("Rueckgabe: = " + retval.toString());
	}
	
	@AfterThrowing(pointcut = "execution(public * de.atruvia.webapp.controllers.PersonenController.*(..))", throwing = "ex")
	public void atAdvice(JoinPoint joinPoint, Throwable ex) {
		System.out.println("Rueckgabe: = " + ex.getMessage());
	}
	@Around(value = "Pointcuts.personControllerMethods()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		return proceedingJoinPoint.proceed();
		
	}
	
}
