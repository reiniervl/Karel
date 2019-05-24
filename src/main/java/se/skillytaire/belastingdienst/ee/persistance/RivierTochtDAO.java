package se.skillytaire.belastingdienst.ee.persistance;

import java.util.List;
import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.RivierTocht;

public interface RivierTochtDAO extends DAO<RivierTocht> {
	Optional<List<RivierTocht>> findBeschikbareTochten();
}