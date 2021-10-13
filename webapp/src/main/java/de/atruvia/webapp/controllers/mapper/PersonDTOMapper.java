package de.atruvia.webapp.controllers.mapper;

import org.mapstruct.Mapper;

import de.atruvia.webapp.controllers.dtos.PersonDTO;
import de.atruvia.webapp.services.models.Person;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {
	
	PersonDTO convert(Person person);
	Person convert(PersonDTO dto);
	Iterable<PersonDTO> convert(Iterable<Person> personen);

}
