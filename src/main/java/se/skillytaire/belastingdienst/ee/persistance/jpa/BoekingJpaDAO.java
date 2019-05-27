package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

import se.skillytaire.belastingdienst.ee.entity.Boeking;
import se.skillytaire.belastingdienst.ee.persistance.BoekingDAO;

@Default
@ApplicationScoped
public class BoekingJpaDAO extends AbstractJPADAO<Boeking> implements BoekingDAO {
	public BoekingJpaDAO() {
		super(Boeking.class);
	}

	@Override
	public Optional<Boeking> findByOID(final Integer OID) {
		Boeking gevondenBoeking = this.em.find(Boeking.class, OID);
		return Optional.ofNullable(gevondenBoeking);
	}
}