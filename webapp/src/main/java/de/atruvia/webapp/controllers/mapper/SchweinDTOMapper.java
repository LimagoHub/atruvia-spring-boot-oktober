package de.atruvia.webapp.controllers.mapper;

import org.mapstruct.Mapper;

import de.atruvia.webapp.controllers.dtos.SchweinDTO;
import de.atruvia.webapp.services.models.Schwein;

@Mapper(componentModel = "spring")
public interface SchweinDTOMapper {

	SchweinDTO convert(Schwein schwein);
	Schwein convert(SchweinDTO dto);
	Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
	
}