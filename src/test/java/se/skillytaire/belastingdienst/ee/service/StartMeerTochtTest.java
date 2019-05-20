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
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.persistance.jpa.BootJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtJpaDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class StartMeerTochtTest {
	/*
	 * @This private Account account;
	 * 
	 * @This private MeerTocht meerTocht;
	 * 
	 * StartMeertocht startMeerTocht;
	 * 
	 * EntityManagerFactory factory; EntityManager em;
	 * 
	 * @Before public void before() { JLCRunner.init(this); }
	 * 
	 * @Before public void setupJPA() { factory =
	 * Persistence.createEntityManagerFactory("stuga"); em =
	 * factory.createEntityManager(); startMeerTocht = new StartMeertocht();
	 * BootJpaDAO.getInstance().setEntityManager(em);
	 * MeerTochtJpaDAO.getInstance().setEntityManager(em); startMeerTocht.bootDAO =
	 * BootJpaDAO.getInstance(); startMeerTocht.meerTochtDAO =
	 * MeerTochtJpaDAO.getInstance(); }
	 * 
	 * @After public void destroyJPA() { if (this.em != null) { this.em.close(); }
	 * if (this.factory != null) { this.factory.close(); while
	 * (this.factory.isOpen() && !Thread.interrupted()) { try { Thread.sleep(100); }
	 * catch (InterruptedException e) { Thread.currentThread().interrupt(); } } } }
	 * 
	 * private void addWithTX(final MeerTocht meerTocht) { EntityTransaction
	 * unmanagedTx = this.em.getTransaction(); try { unmanagedTx.begin();
	 * startMeerTocht.meerTochtDAO.add(meerTocht); unmanagedTx.commit(); } catch
	 * (RuntimeException e) { if (unmanagedTx.isActive()) { unmanagedTx.rollback();
	 * } throw e; } }
	 * 
	 * @Test public void TestStartMeerTocht() { assertTrue("meerTocht != null",
	 * meerTocht != null); addWithTX(meerTocht); QRCode code = new
	 * QRCode(meerTocht.getBoot().getEigenaar().getUserName(),
	 * meerTocht.getBoot().getNummer()); assertFalse("Tocht nog niet gestart",
	 * meerTocht.getActuelePeriode().isGestart()); StartMeerTochtResultTO result =
	 * startMeerTocht.start(code); assertTrue("result is successful",
	 * result.isSuccessful()); }
	 */
}