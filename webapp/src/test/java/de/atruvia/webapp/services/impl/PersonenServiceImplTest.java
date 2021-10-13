package de.atruvia.webapp.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.PersonenServiceException;
import de.atruvia.webapp.services.mapper.PersonMapper;
import de.atruvia.webapp.services.models.Person;
import static org.mockito.Mockito.*;
/*
 * 
 * A rrange
 * A ct
 * A ssertion
 * 
 */

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class PersonenServiceImplTest {
	
	@InjectMocks
	private PersonenServiceImpl objectUnderTest;
	
	@Mock
	private PersonenRepository repoMock;
	
	@Mock
	private PersonMapper mapperMock;
	
	@Mock
	private List<String> antipathenMock;
	
	
	
	@Test
	void speichern_parameterIsNull_throwsPersonenServiceException() {
		PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speicher(null));
		assertEquals("Parameter darf nicht null sein.", ex.getMessage());
	}

	@Test
	void speichern_vornameIsNull_throwsPersonenServiceException() {
		
		Person p = Person.builder().id("1").vorname(null).nachname("Doe").build();
		
		PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speicher(p));
		assertEquals("Vorname muss min. 2 Zeichen enthalten", ex.getMessage());
	}

	@Test
	void speichern_vornameTooShort_throwsPersonenServiceException() {
		
		Person p = Person.builder().id("1").vorname("J").nachname("Doe").build();
		
		PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speicher(p));
		assertEquals("Vorname muss min. 2 Zeichen enthalten", ex.getMessage());
	}

	@Test
	void speichern_nachnameIsNull_throwsPersonenServiceException() {
		
		Person p = Person.builder().id("1").vorname("John").nachname(null).build();
		
		PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speicher(p));
		assertEquals("Nachname muss min. 2 Zeichen enthalten", ex.getMessage());
	}

	@Test
	void speichern_nachnameTooShort_throwsPersonenServiceException() {
		
		Person p = Person.builder().id("1").vorname("John").nachname("D").build();
		
		PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speicher(p));
		assertEquals("Nachname muss min. 2 Zeichen enthalten", ex.getMessage());
	}

	
	@Test
	void speichern_Antipath_throwsPersonenServiceException() {
		
		Person p = Person.builder().id("1").vorname("John").nachname("Doe").build();
		when(antipathenMock.contains(anyString())).thenReturn(true);
		
		PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speicher(p));
		assertEquals("Antipath", ex.getMessage());
	}
	
	@Test
	void speichern_ExceptionInUnderLyingService_throwsPersonenServiceException() {
		
		when(repoMock.save(any())).thenThrow(new ArrayIndexOutOfBoundsException());
		when(antipathenMock.contains(anyString())).thenReturn(false);
		
		Person valid = Person.builder().id("1").vorname("John").nachname("Doe").build();
		
		PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speicher(valid));
		assertEquals("Fehler in der Datenbank", ex.getMessage());
	}
	

}
