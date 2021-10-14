package de.atruvia.webapp.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.repositories.SchweineRepository;
import de.atruvia.webapp.services.SchweineService;
import de.atruvia.webapp.services.SchweineServiceException;
import de.atruvia.webapp.services.mapper.SchweinMapper;
import de.atruvia.webapp.services.models.Schwein;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SchweineServiceException.class)
@Slf4j
@AllArgsConstructor
public class SchweineServiceImpl implements SchweineService {
	
	private final SchweineRepository repo;
	private final SchweinMapper mapper;

	@Override
	public boolean speichernOderAendern(Schwein schwein) throws SchweineServiceException {
		try {
			boolean exists = repo.existsById(schwein.getId());
			repo.save(mapper.convert(schwein));
			return exists;
		} catch (RuntimeException e) {
			SchweineServiceException ex = new SchweineServiceException(e);
			log.error("Upps", ex);
			throw ex;
		}
	}

	@Override
	public boolean loeschen(String id) throws SchweineServiceException {
		try {
			boolean exists = repo.existsById(id);
			if(exists) repo.deleteById(id);
			return exists;
		} catch (Exception e) {
			SchweineServiceException ex = new SchweineServiceException(e);
			log.error("Upps", ex);
			throw ex;
		}
	}

	@Override
	public Optional<Schwein> findeNachPrimaerSchluessel(String pk) throws SchweineServiceException {
		return repo.findById(pk).map(mapper::convert);
	}

	@Override
	public Iterable<Schwein> findeAlle() throws SchweineServiceException {
		
		return mapper.convert(repo.findAll());
	}

	@Override
	public boolean fuettern(String id) throws SchweineServiceException {
		var schweinOptional = findeNachPrimaerSchluessel(id); 
		if(schweinOptional.isPresent()) {
			var hungrigesSchwein = schweinOptional.get();
			hungrigesSchwein.fressen();
			speichernOderAendern(hungrigesSchwein);
			return true;
		}
		return false;
	}

}
