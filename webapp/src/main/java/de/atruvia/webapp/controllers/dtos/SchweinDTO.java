package de.atruvia.webapp.controllers.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinDTO {
	
	@NotNull
	@Size(min = 36, max = 36)
	private String id;
	
	@Size(min = 2, max = 30)
	private String name;
	
	@DecimalMin(value = "10", inclusive = true)
	private int gewicht;
	
	
}