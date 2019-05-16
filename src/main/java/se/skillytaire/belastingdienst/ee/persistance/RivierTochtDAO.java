package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;

public interface RivierTochtDAO extends DAO<RivierTocht> {
	Optional<RivierTocht> findByBoot(Boot boot);
}