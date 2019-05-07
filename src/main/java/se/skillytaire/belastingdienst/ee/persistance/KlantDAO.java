package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.Klant;

public interface KlantDAO extends DAO<Klant> {
   Optional<Klant> find(String username);
}