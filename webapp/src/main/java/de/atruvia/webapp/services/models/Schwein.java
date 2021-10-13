package de.atruvia.webapp.services.models;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein {
	
	@Setter(value = AccessLevel.PRIVATE)
	private String id;
	
	private String name;
	
	@Setter(value = AccessLevel.PRIVATE)
	private int gewicht;
	
	public void fressen() {
		setGewicht(getGewicht() + 1);
	}
}
