package se.skillytaire.belastingdienst.ee.persistance;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.AbstractEntity;

public interface DAO<T extends AbstractEntity<T>> {
   void add(T t);

   Optional<T> findByOID(Integer OID);

   T update(T t);

   public default boolean delete(T t) {
      boolean isDeleted = false;
      if (t.isPersistant()) {
         isDeleted = deleteByOID(t.getOid());
      }
      return isDeleted;
   }

   boolean deleteByOID(Integer OID);
}
