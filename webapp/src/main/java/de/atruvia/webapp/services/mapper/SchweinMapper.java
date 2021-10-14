package de.atruvia.webapp.services.mapper;

import org.mapstruct.Mapper;

import de.atruvia.webapp.repositories.entities.SchweinEntity;
import de.atruvia.webapp.services.models.Schwein;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

	Schwein convert(SchweinEntity entity);
	SchweinEntity convert(Schwein schwein);
	Iterable<Schwein> convert(Iterable<SchweinEntity> entities);
	
}
