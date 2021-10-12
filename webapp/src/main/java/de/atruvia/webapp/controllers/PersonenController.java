package de.atruvia.webapp.controllers;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import de.atruvia.webapp.controllers.dtos.PersonDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {

	@ApiResponse(responseCode = "200", description = "Person wurde gefunden")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PersonDTO> findPersonById(@PathVariable String id) {
		// Optional<PersonDTO> personOptional =
		// Optional.of(PersonDTO.builder().id(id).vorname("John").nachname("Doe").build());
		Optional<PersonDTO> personOptional = Optional.empty();

		// return ResponseEntity.ok(person);
		return ResponseEntity.of(personOptional);
	}

	@ApiResponse(responseCode = "200", description = "Erfolg")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonDTO>> findAll(@RequestParam(required = false) String vorname,
			@RequestParam(required = false) String nachname) {
		var result = List.of(PersonDTO.builder().id("1").vorname("John").nachname("Doe").build(),
				PersonDTO.builder().id("2").vorname("John").nachname("Wayne").build(),
				PersonDTO.builder().id("3").vorname("John").nachname("Rambo").build());

		// return ResponseEntity.ok(person);
		return ResponseEntity.ok(result);
	}

	@ApiResponse(responseCode = "200", description = "Person wurde gelöscht")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deletePersonById(@PathVariable String id) {
		System.out.println("Person wurde gelöscht");
		return ResponseEntity.ok().build();
	}
	
	@ApiResponse(responseCode = "200", description = "Person wurde gelöscht")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePerson(@RequestBody PersonDTO dto) {
		System.out.println("Person wurde gelöscht");
		return ResponseEntity.ok().build();
	}
	
	@ApiResponse(responseCode = "201", description = "Person neu angelegt")
	@ApiResponse(responseCode = "200", description = "Person wurde geaendert")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveOrUpdateIdempotent(@Valid @RequestBody PersonDTO dto) {
		System.out.println("Person wurde gespeichert oder eingefuegt: " + dto);
		return ResponseEntity.ok().build();
	}

//	@ApiResponse(responseCode = "201", description = "Person neu angelegt")
//	@ApiResponse(responseCode = "200", description = "Person wurde geaendert")
//	@ApiResponse(responseCode = "400", description = "Falsches Format")
//	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
//	@PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Void> saveOrUpdateNichtIdempotent(@RequestBody PersonDTO dto, UriComponentsBuilder builder) {
//		dto.setId(UUID.randomUUID().toString());
//		
//		var uri = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());
//		
//		System.out.println("Person wurde gespeichert oder eingefuegt: " + dto);
//		
//		//return ResponseEntity.created(uri.toUri()).build();
//		return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).build();
//	}
	
//	// Ersatzget wegen Parameter OBJECT
//	@PostMapping(path="/toUpper", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PersonDTO> toUpper (@RequestBody PersonDTO dto) {
//		dto.setVorname(dto.getVorname().toUpperCase());
//		dto.setNachname(dto.getNachname().toUpperCase());
//		return ResponseEntity.ok(dto);
//	}
	
		@GetMapping(path="/{id}/toUpper", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<PersonDTO> toUpper (@PathVariable String id) {
			var dto = PersonDTO.builder().id(id).vorname("JOHN").nachname("DOE").build();
			return ResponseEntity.ok(dto);
		}
		
		@GetMapping(path="/{id}/toLower", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<PersonDTO> toLower (@PathVariable String id) {
			throw new ArrayIndexOutOfBoundsException(5);
		}

}
