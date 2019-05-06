package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.Reservering;

public interface ReserveringDAO extends DAO<Reservering> {
   Optional<Reservering> find(Integer reserveringsNummer);
}
