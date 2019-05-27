package se.skillytaire.belastingdienst.ee.persistance.jpa;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.TypedQuery;

import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.BootDAO;
@Default
@ApplicationScoped
public class BootJpaDAO extends AbstractJPADAO<Boot> implements BootDAO {
	public BootJpaDAO() {
		super(Boot.class);
	}

	@Override
	public Optional<Boot> findBeschikbareBoot(Verhuurder verhuurder) {
		Boot beschikbareBoot = null;
		TypedQuery<Boot> query = this.em.createNamedQuery(Boot.SELECT_BY_ISBESCHIKBAAR, Boot.class);
		query.setParameter("verhuurder", verhuurder.getUserName());
		List<Boot> lijstMetBoten = query.getResultList();
		for (Boot boot : lijstMetBoten) {
			if (boot.isBeschikbaar()) {
				beschikbareBoot = boot;
				break;
			}
		}
		return Optional.ofNullable(beschikbareBoot);
	}

}
