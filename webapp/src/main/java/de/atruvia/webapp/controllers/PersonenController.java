package de.atruvia.webapp.controllers;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.atruvia.webapp.controllers.dtos.PersonDTO;
import de.atruvia.webapp.controllers.mapper.PersonDTOMapper;
import de.atruvia.webapp.services.PersonenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/personen")
@AllArgsConstructor
public class PersonenController {
	
	
	private PersonenService service;
	private PersonDTOMapper mapper;
	

	@ApiResponse(responseCode = "200", description = "Person wurde gefunden")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PersonDTO> findPersonById(@PathVariable String id) throws Exception{
		return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));
	}

	@ApiResponse(responseCode = "200", description = "Erfolg")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<PersonDTO>> findAll(@RequestParam(required = false) String vorname,	@RequestParam(required = false) String nachname) throws Exception{
		return ResponseEntity.ok(mapper.convert(service.findeAlle()));
	}

	@ApiResponse(responseCode = "200", description = "Person wurde gel??scht")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deletePersonById(@PathVariable String id) throws Exception{
		if(service.loeschen(id))
			return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
	}
	
	@ApiResponse(responseCode = "200", description = "Person wurde gel??scht")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePerson(@RequestBody PersonDTO dto) throws Exception{
		
		return deletePersonById(dto.getId());
	}
	
	@ApiResponse(responseCode = "201", description = "Person neu angelegt")
	@ApiResponse(responseCode = "200", description = "Person wurde geaendert")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveOrUpdateIdempotent(@Valid @RequestBody PersonDTO dto) throws Exception{
		if(service.speicher(mapper.convert(dto)))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.CREATED).build();
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
	
//		@GetMapping(path="/{id}/to-upper", produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<PersonDTO> toUpper (@PathVariable String id) {
//			var dto = PersonDTO.builder().id(id).vorname("JOHN").nachname("DOE").build();
//			return ResponseEntity.ok(dto);
//		}
//		
//		@GetMapping(path="/{id}/toLower", produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<PersonDTO> toLower (@PathVariable String id) {
//			throw new ArrayIndexOutOfBoundsException(5);
//		}
//
}

/*

VERB	Safe	idempotent

GET	    ja	    ja	    Daten holen
DELETE	nein	ja	    Daten l??schen
PUT	    nein	ja	    Daten einf??gen oder ??ndern
POST	nein	nein	Daten einf??gen oder ??ndern

POST	Ja	Ja	Daten holen (Get-Ersatz wenn Parameter keine Strings oder zahlen sondern Objekte sind


 */
