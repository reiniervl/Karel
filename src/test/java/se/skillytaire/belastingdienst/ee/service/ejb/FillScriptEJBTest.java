package se.skillytaire.belastingdienst.ee.service.ejb;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.UserTransaction;

import org.junit.Rule;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.BoekingJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.EntityManagerTestRule;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.RivierTochtJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.VerhuurderJpaDAO;

public class FillScriptEJBTest {
	@Rule
	public EntityManagerTestRule jpa = EntityManagerTestRule.persistenceUnit("stuga");
	@Test
	public void doIt() {
		FillScriptEJB ejb = new FillScriptEJB();
		EntityManager em = jpa.em();
		VerhuurderJpaDAO verhuurderDao = new VerhuurderJpaDAO();
		RivierTochtJpaDAO rivierTochtDao = new RivierTochtJpaDAO();
		MeerTochtJpaDAO meerTochtDao = new MeerTochtJpaDAO();
		AccountJpaDAO accountDao = new AccountJpaDAO();
		BoekingJpaDAO boekingDao = new BoekingJpaDAO();
		verhuurderDao.setEntityManager(em);
		rivierTochtDao.setEntityManager(em);
		meerTochtDao.setEntityManager(em);
		accountDao.setEntityManager(em);
		boekingDao.setEntityManager(em);
		ejb.verhuurderDao = verhuurderDao;
		ejb.rivierTochtDao = rivierTochtDao;
		ejb.meerTochtDao = meerTochtDao;
		ejb.accountDao = accountDao;
		ejb.boekingDao = boekingDao;
		
		EntityTransaction tx = jpa.getNewTransaction();
		tx.begin();
		boolean actual = ejb.fill();
		assertTrue(actual);
		tx.commit();

	}
}
