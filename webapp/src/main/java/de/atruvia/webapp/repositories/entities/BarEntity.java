package de.atruvia.webapp.repositories.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "tbl_bars")
@NamedQuery(name= "BarEntity.findBarsWithKeeper", query = "select distinct b from BarEntity b left join fetch b.keepers k")
public class BarEntity {
	
	@Id
	private String id;
	private String name;
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "bar")
	@Builder.Default
	@lombok.ToString.Exclude
	private List<BarkeeperEntity> keepers = new ArrayList<>();
	
	
	
	public void addBarkeeper(BarkeeperEntity barkeeperEntity) {
		barkeeperEntity.setBar(this);
		keepers.add(barkeeperEntity);
	}

}
