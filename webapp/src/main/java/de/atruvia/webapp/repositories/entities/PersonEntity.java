package de.atruvia.webapp.repositories.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class PersonEntity {
	
	//@EqualsExclude
	@Id
	@Column(length = 36, nullable = false)
	private String id;
	@Column(length = 30)
	private String vorname;
	@Column(length = 30, nullable = false)
	private String nachname;
	
	

}
