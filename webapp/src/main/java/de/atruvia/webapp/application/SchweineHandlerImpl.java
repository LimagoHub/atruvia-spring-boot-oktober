package de.atruvia.webapp.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.controllers.dtos.SchweinDTO;
import de.atruvia.webapp.controllers.mapper.SchweinDTOMapper;
import de.atruvia.webapp.services.SchweineService;
import de.atruvia.webapp.services.SchweineServiceException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class SchweineHandlerImpl {

	
	private final SchweineService service;
	private final SchweinDTOMapper mapper;
	
	public void handleSaved(SchweinDTO dto) throws SchweineServiceException{
		try {
			service.speichernOderAendern(mapper.convert(dto));
			// Erfolg event feuern
		} catch (SchweineServiceException e) {
			// Misserfolg event feuern
			throw e;
		}
	}
	
	
	
}
