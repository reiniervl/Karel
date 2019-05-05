package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Adres;

public interface AdresDao extends DAO<Adres> {
   Optional<Adres> find(GPSCoordinaat coordinate);
}
