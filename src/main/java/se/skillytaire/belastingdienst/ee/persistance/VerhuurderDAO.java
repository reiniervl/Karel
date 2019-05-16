package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.Verhuurder;

public interface VerhuurderDAO extends DAO<Verhuurder>{
	Optional<Verhuurder> find(String username);
}
