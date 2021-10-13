package de.atruvia.webapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.atruvia.webapp.repositories.entities.PersonEntity;
import de.atruvia.webapp.repositories.entities.TinyPerson;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> {
	
//	List<PersonEntity> findByVorname(String vorname);
//	List<PersonEntity> findByVornameAndNachname(String vorname, String nachname);
//	List<PersonEntity> findByVornameOrNachname(String vorname, String nachname);
//	List<PersonEntity> findByVornameOrNachnameOrderByNachname(String vorname, String nachname);
	
	List<PersonEntity> findAllAsList ();
	
	@Query("select new de.atruvia.webapp.repositories.entities.TinyPerson(p.id,p.nachname) from PersonEntity p")
	List<TinyPerson> findTinies();

//	@Query("select new Object[]{p.id,p.nachname} from PersonEntity p")
//	List<Object[]> findTinies();
	
//	@Query("update PersonEntity set vorname=:vorname where id=:id")
//	void updateVorname(String vorname, String id);
//	@Query("select count(p) from PersonEntity")
//	long anzahl();

}
