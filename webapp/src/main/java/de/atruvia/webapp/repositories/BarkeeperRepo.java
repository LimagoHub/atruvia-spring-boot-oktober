package de.atruvia.webapp.repositories;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.repository.CrudRepository;

import de.atruvia.webapp.repositories.entities.BarkeeperEntity;

public interface BarkeeperRepo extends CrudRepository<BarkeeperEntity, String>{

}
