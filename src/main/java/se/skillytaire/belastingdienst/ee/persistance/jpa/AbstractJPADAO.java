package se.skillytaire.belastingdienst.ee.persistance.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//FIXME lijkt me beter :-)
public class AbstractJPADAO {
	@PersistenceContext
	protected EntityManager em;
}
