package de.atruvia.webapp;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.repositories.BarRepo;
import de.atruvia.webapp.repositories.BarkeeperRepo;
import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.repositories.entities.BarEntity;
import de.atruvia.webapp.repositories.entities.BarkeeperEntity;
import de.atruvia.webapp.repositories.entities.PersonEntity;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class DBDemo {
	
	private final BarkeeperRepo barkeeperRepo;
	private final BarRepo barRepo;
	
	@PostConstruct
	public void init() {

//		BarEntity bar = BarEntity.builder().id(UUID.randomUUID().toString()).name("Sonderbar").build();
//		
//		BarkeeperEntity bk = new BarkeeperEntity();
//		bk.setId(UUID.randomUUID().toString());
//		bk.setKeeperName("Jonny");
//		bk.setBar(bar);
//		
//		barkeeperRepo.save(bk);
//		
		
		
//		BarkeeperEntity bk = BarkeeperEntity.builder().id(UUID.randomUUID().toString()).keeperName("Tom").build();
//		
//		
//		BarEntity bar = BarEntity.builder().id(UUID.randomUUID().toString()).name("Milchbar").build();
//		
//		bar.addBarkeeper(bk);
//		
//		barRepo.save(bar);
		
		
		BarEntity m = barRepo.findBarsWithKeeper().get(0);
		
		System.out.println(m);
		for (BarkeeperEntity bk : m.getKeepers()) {
			System.out.println(bk);
		}
		
	}

}
