package de.atruvia.webapp.repositories.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

@Entity
@Table(name="tbl_personen")
@NamedQuery(name="PersonEntity.findAllAsList", query = "select p from PersonEntity p")
public class PersonEntity {
	
	//@EqualsExclude
	@Id
	@Column(length = 36, nullable = false)
	private String id;
	@Column(length = 30)
	private String vorname;
	@Column(length = 30, nullable = false)
	private String nachname;
	
	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Kontakt> kontakte = new ArrayList<Kontakt>();

}
