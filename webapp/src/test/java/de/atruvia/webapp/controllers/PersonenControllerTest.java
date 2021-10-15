package de.atruvia.webapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.atruvia.webapp.controllers.dtos.PersonDTO;
import de.atruvia.webapp.services.PersonenService;
import de.atruvia.webapp.services.models.Person;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"create.sql", "insert.sql"})
@ExtendWith(SpringExtension.class)
public class PersonenControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean // Springspezifisch
	private PersonenService serviceMock;
	
	
	@Test
	void findPersonById_PersonExist_success() throws Exception{
		
		// Arrange
        Person validPerson = Person.builder().id("123").vorname("john").nachname("doe").build();
        Optional<Person> optional = Optional.of(validPerson);
		when(serviceMock.findeNachId(anyString())).thenReturn(optional);
		
		ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
		
		PersonDTO person = entity.getBody();
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("john", person.getVorname());
		assertEquals("doe", person.getNachname());
	}
	@Test
	void test2() throws Exception{
		
		// Arrange
        
		when(serviceMock.findeNachId(anyString())).thenReturn(Optional.empty());
		
		//ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
		ResponseEntity<PersonDTO> entity = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.GET, null, PersonDTO.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
		
	}
	
	@Test
	void test3() throws Exception{
		
		// Arrange
        List<Person> liste = List.of( Person.builder().id("123").vorname("john").nachname("Rambo").build(), Person.builder().id("456").vorname("john").nachname("Wayne").build());
		when(serviceMock.findeAlle()).thenReturn(liste);
		
		ResponseEntity<List<PersonDTO>> entity = restTemplate.exchange("/v1/personen", HttpMethod.GET, null, new ParameterizedTypeReference<List<PersonDTO>>() {});
		
		
		List<PersonDTO> personen = entity.getBody();
		assertEquals(2, personen.size());
		for (PersonDTO personDTO : personen) {
			assertEquals("john", personDTO.getVorname());
		}
		
	}
	
	@Test
	void test4_speichern() throws Exception{
		
		String id = UUID.randomUUID().toString();
		
		// Arrange
        var person = Person.builder().id(id).vorname("john").nachname("Rambo").build();
        
        var dto = PersonDTO.builder().id(id).vorname("john").nachname("Rambo").build();
        
        when(serviceMock.speicher(any())).thenReturn(false);
        
		HttpEntity<PersonDTO> requestEntity = new HttpEntity<>(dto);
		
		
		// Act
		ResponseEntity<Void> entity = restTemplate.exchange("/v1/personen", HttpMethod.PUT, requestEntity, Void.class);
		
		
		// Assertion
		assertEquals(HttpStatus.CREATED , entity.getStatusCode());
		
		verify(serviceMock,times(1)).speicher(person);
	}
	

	@Test
	void test4_update() throws Exception{
		
		// Arrange
		String id = UUID.randomUUID().toString();
		
		
        var person = Person.builder().id(id).vorname("john").nachname("Rambo").build();
        
        var dto = PersonDTO.builder().id(id).vorname("john").nachname("Rambo").build();
        when(serviceMock.speicher(any())).thenReturn(true);
		HttpEntity<PersonDTO> requestEntity = new HttpEntity<>(dto);
		
		ResponseEntity<Void> entity = restTemplate.exchange("/v1/personen", HttpMethod.PUT, requestEntity, Void.class);
		
		assertEquals(HttpStatus.OK , entity.getStatusCode());
		
		verify(serviceMock,times(1)).speicher(person);
	}
}
