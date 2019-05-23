package se.skillytaire.belastingdienst.ee.service.ejb;

import static org.junit.Assert.assertFalse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.ReserveringJpaDAO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringResultTO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringTO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;


public class MaakNieuweReserveringTest {

	@This
	private Account thisAccount;
	@This
	private Verhuurder thisVerhuurder;
	
	EntityManagerFactory factory;
	EntityManager em;

	MaakNieuweReserveringEJB beanUnderTest;


	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		factory = Persistence.createEntityManagerFactory("stuga");
		em = factory.createEntityManager();
		ReserveringJpaDAO.getInstance().setEntityManager(em);
		beanUnderTest = new MaakNieuweReserveringEJB();
		AccountExistsEJB existEJB = new AccountExistsEJB();
		AccountJpaDAO.getInstance().setEntityManager(em);
		existEJB.dao = AccountJpaDAO.getInstance();
		beanUnderTest.accountExistsService = existEJB;
		
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

	@Test
	public void testMaakNieuweReservering() {
		NieuweReserveringTO reserveringTO = new NieuweReserveringTO("Joop" , "Karel", 1);
		NieuweReserveringResultTO result = beanUnderTest.reserveren(reserveringTO);
		assertFalse(result.isSuccessful());
	}
}