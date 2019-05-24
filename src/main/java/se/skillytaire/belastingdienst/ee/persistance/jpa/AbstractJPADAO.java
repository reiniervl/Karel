package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import se.skillytaire.belastingdienst.ee.entity.AbstractEntity;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.DAO;
//FIXME lijkt me beter :-)
//FIXME JPADao? als naam?
public class AbstractJPADAO <T extends AbstractEntity<T>> implements DAO<T>{
	@PersistenceContext(unitName="stuga")
	protected EntityManager em;
	private Class<T> entityType;
	
	/**
	 * Let op werkt alleen voor JUnit en niet voor de server! Dit ivm welding CDI!
	 */
	@SuppressWarnings("unchecked")
	private AbstractJPADAO() {
	      this.entityType = (Class<T>) ((ParameterizedType) getClass()
	              .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected AbstractJPADAO(Class<T> entityType) {
		this.entityType = entityType;
	}
	/**
	 * For easy testing
	 * @param entityManager
	 */
	public void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}
	
	@Override
	public T update(final T entity) {
		return this.em.merge(entity);
	}

	@Override
	public boolean delete(final T entity) {
		this.em.remove(entity);
		return true;
	}
	@Override
	public void add(final T entity) {
		this.em.persist(entity);
	}
	@Override
	public boolean deleteByOID(final Integer OID) {
		boolean deleted = false;
		 Optional<T> gevonden = this.findByOID(OID);
		 if (gevonden.isPresent()) {
				em.remove(gevonden.get());
				deleted = true;
		 }
		 return deleted;
	}
	@Override
	public Optional<T> findByOID(final Integer OID) {
		T gevondenVerhuurder = this.em.find(this.entityType, OID);
		return Optional.ofNullable(gevondenVerhuurder);
	}
}
