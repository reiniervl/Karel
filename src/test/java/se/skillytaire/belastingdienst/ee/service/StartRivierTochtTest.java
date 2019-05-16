package se.skillytaire.belastingdienst.ee.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.common.QRCode;
import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.jpa.BootJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.RivierTochtJpaDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class StartRivierTochtTest {
	@This private Account account;
	@This private RivierTocht rivierTocht;

	StartRiviertocht startRivierTocht;

	EntityManagerFactory factory;
	EntityManager em;

	@Before
	public void before() {
		JLCRunner.init(this);
	}
	
	@Before
	public void setupJPA() {
		factory = Persistence.createEntityManagerFactory("stuga");
		em = factory.createEntityManager();
		startRivierTocht = new StartRiviertocht();
		BootJpaDAO.getInstance().setEntityManager(em);
		RivierTochtJpaDAO.getInstance().setEntityManager(em);
		startRivierTocht.bootDAO = BootJpaDAO.getInstance();
		startRivierTocht.rivierTochtDAO = RivierTochtJpaDAO.getInstance();
	}

	@After
	public void destroyJPA() {
		if (this.em != null) {
			this.em.close();
		}
		if (this.factory != null) {
			this.factory.close();
			while (this.factory.isOpen() && !Thread.interrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

		private void addWithTX(final RivierTocht rivierTocht) {
		EntityTransaction unmanagedTx = this.em.getTransaction();
		try {
			unmanagedTx.begin();
			startRivierTocht.rivierTochtDAO.add(rivierTocht);
			unmanagedTx.commit();
		} catch (RuntimeException e) {
			if (unmanagedTx.isActive()) {
				unmanagedTx.rollback();
			}
			throw e;
		}
	}

	@Test
	public void TestStartRivierTocht() {
		assertTrue("rivierTocht != null", rivierTocht != null);
		addWithTX(rivierTocht);
		QRCode code = new QRCode(rivierTocht.getBoot().getEigenaar().getUserName(), rivierTocht.getBoot().getNummer());
		assertFalse("Tocht nog niet gestart", rivierTocht.getActuelePeriode().isGestart());
		StartRivierTochtResultTO result = startRivierTocht.start(code);
		assertTrue("result is successful", result.isSuccessful());
	}
}