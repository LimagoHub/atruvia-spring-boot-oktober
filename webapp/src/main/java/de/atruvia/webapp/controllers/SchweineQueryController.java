package de.atruvia.webapp.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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

import de.atruvia.webapp.controllers.dtos.SchweinDTO;
import de.atruvia.webapp.controllers.mapper.SchweinDTOMapper;
import de.atruvia.webapp.services.SchweineService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/schweine")
@AllArgsConstructor
public class SchweineQueryController {

	private final SchweineService service;
	private final SchweinDTOMapper mapper;
	
	
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SchweinDTO> findById(@PathVariable String id) throws Exception{
		return ResponseEntity.of(service.findeNachPrimaerSchluessel(id).map(mapper::convert));
	}
	
	@GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<SchweinDTO>> findAll
			(
					@RequestParam(required = false) String name,
					@RequestParam(required = false, defaultValue = "0") int gewicht
			) throws Exception{
		return ResponseEntity.ok(mapper.convert(service.findeAlle()));
	}
	
	
	
}
