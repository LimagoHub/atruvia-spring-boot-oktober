package de.atruvia.webapp.services;

import java.util.Optional;

import de.atruvia.webapp.services.models.Schwein;

public interface SchweineService {
	
	boolean speichernOderAendern(Schwein schwein) throws SchweineServiceException; 
	boolean loeschen(String id) throws SchweineServiceException;
	Optional<Schwein> findeNachPrimaerSchluessel(String pk) throws SchweineServiceException;
	Iterable<Schwein> findeAlle() throws SchweineServiceException;
	boolean fuettern(String id) throws SchweineServiceException;

}
