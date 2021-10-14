package de.atruvia.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import de.atruvia.webapp.repositories.entities.SchweinEntity;

public interface SchweineRepository extends CrudRepository<SchweinEntity, String> {

}
