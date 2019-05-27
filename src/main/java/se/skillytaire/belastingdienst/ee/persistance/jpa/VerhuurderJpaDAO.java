package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.VerhuurderDAO;

@Default
@ApplicationScoped
public class VerhuurderJpaDAO extends AbstractJPADAO<Verhuurder> implements VerhuurderDAO {
	public VerhuurderJpaDAO() {
		super(Verhuurder.class);
	}

	@Override
	public Optional<Verhuurder> find(String username) {
		TypedQuery<Verhuurder> query = this.em.createNamedQuery(Verhuurder.FIND_VERHUURDER_BY_USERNAME,
				Verhuurder.class);
		query.setParameter("username", username);
		Optional<Verhuurder> result = query.getResultList().stream().findFirst();
		return result;
	}
}