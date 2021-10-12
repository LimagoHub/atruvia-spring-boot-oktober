package de.atruvia.webapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.atruvia.webapp.repositories.entities.PersonEntity;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> {
	
//	List<PersonEntity> findByVorname(String vorname);
//	List<PersonEntity> findByVornameAndNachname(String vorname, String nachname);
//	List<PersonEntity> findByVornameOrNachname(String vorname, String nachname);
//	List<PersonEntity> findByVornameOrNachnameOrderByNachname(String vorname, String nachname);

}
