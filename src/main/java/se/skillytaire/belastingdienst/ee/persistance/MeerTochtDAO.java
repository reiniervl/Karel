package se.skillytaire.belastingdienst.ee.persistance;

import java.util.List;
import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.MeerTocht;

public interface MeerTochtDAO extends DAO<MeerTocht> {
	Optional<List<MeerTocht>> findBeschikbareTochten();

}