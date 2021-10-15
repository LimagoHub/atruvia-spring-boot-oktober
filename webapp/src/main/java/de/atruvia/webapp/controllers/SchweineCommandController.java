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
public class SchweineCommandController {

	private final SchweineService service;
	private final SchweinDTOMapper mapper;
	
		
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody SchweinDTO dto) throws Exception{
		if(service.speichernOderAendern(mapper.convert(dto))) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> remove(@PathVariable String id) throws Exception{
		if(service.loeschen(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path="/{id}/fuettern")
	public ResponseEntity<Void> feed(@PathVariable String id) throws Exception{
		if(service.fuettern(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
