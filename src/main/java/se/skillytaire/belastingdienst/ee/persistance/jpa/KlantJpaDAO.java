package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.KlantDAO;
@Default
@ApplicationScoped
public class KlantJpaDAO extends AbstractJPADAO<Klant> implements KlantDAO {
   public KlantJpaDAO() {
	   super(Klant.class);
	 }

	@Override
	public Optional<Klant> find(String username) {
    TypedQuery<Klant> namedQuery = this.em.createNamedQuery(Klant.FIND_BY_USERNAME,
		Klant.class);
    namedQuery.setParameter("username", username);
    Optional<Klant> result = namedQuery.getResultList().stream().findFirst();
    return result;
	}
}
