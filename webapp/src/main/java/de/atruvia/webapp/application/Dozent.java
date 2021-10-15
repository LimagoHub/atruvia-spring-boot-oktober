package de.atruvia.webapp.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dozent {
	private String name;
	private String thema;
}
