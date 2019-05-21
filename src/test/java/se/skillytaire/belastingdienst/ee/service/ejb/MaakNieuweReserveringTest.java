package se.skillytaire.belastingdienst.ee.service.ejb;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.persistance.jpa.ReserveringJpaDAO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringResultTO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringTO;
import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = Reservering.class, asJUnit = true)
public class MaakNieuweReserveringTest {

	@This
	private RivierTocht thisRivierTocht;
	@This
	private MeerTocht thisMeerTocht;
	@This
	private Account thisAccount;
	

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
		beanUnderTest.dao = ReserveringJpaDAO.getInstance();
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
		NieuweReserveringTO reserveringTO = new NieuweReserveringTO(thisAccount, thisRivierTocht);
		NieuweReserveringResultTO result = beanUnderTest.doIt(reserveringTO);
		assertTrue("Reservering geslaagd", result.isSuccessful());
	}
}