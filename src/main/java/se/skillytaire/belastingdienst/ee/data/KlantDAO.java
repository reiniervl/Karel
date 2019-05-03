package se.skillytaire.belastingdienst.ee.data;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.skillytaire.belastingdienst.ee.entity.Klant;

public class KlantDAO implements DAO<Klant> {
	private static KlantDAO dao = null;

	private KlantDAO() {
	}

	public static KlantDAO getDAO() {
			if(KlantDAO.dao == null) KlantDAO.dao = new KlantDAO();
			return KlantDAO.dao;
	}

	@Override
	public void add(Klant klant) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stuga");
		EntityManager em = factory.createEntityManager();

		em.persist(klant);

		em.close();
		factory.close();
	}

	@Override
	public Optional<Klant> findByOID(Integer OID) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stuga");
		EntityManager em = factory.createEntityManager();

		Klant gevondenKlant = em.find(Klant.class, OID);
		Optional<Klant> optioneleKlant = Optional.ofNullable(gevondenKlant);

		em.close();
		factory.close();

		return optioneleKlant;
	}

	@Override
	public void update(Klant klant) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stuga");
		EntityManager em = factory.createEntityManager();

		em.merge(klant);

		em.close();
		factory.close();
	}

	@Override
	public void delete(Klant klant) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stuga");
		EntityManager em = factory.createEntityManager();

		em.remove(klant);

		em.close();
		factory.close();
	}

	@Override
	public void deleteByOID(Integer OID) {
		Optional<Klant> gevondenKlant = findByOID(OID);
		if(gevondenKlant.isPresent()) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("stuga");
			EntityManager em = factory.createEntityManager();

			em.remove(gevondenKlant.get());

			em.close();
			factory.close();
		}
	}

}