package se.skillytaire.belastingdienst.ee.data;

import java.util.Optional;

import se.skillytaire.belastingdienst.ee.entity.AbstractEntity;

public interface DAO<T extends AbstractEntity<T>> {
   public abstract void add(T t);

   public abstract Optional<T> findByOID(Integer OID);

   public abstract void update(T t);

   public abstract void delete(T t);

   public abstract void deleteByOID(Integer OID);
}
