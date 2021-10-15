package de.atruvia.webapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.atruvia.webapp.repositories.entities.BarEntity;

public interface BarRepo extends CrudRepository<BarEntity, String> {

	
	List<BarEntity> findBarsWithKeeper();
}
