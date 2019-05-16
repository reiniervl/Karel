package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;

public interface MeerTochtDAO extends DAO<MeerTocht> {
	Optional<MeerTocht> findByBoot(Boot boot);
}