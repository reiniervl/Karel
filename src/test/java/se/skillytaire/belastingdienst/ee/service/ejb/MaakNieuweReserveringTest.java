package se.skillytaire.belastingdienst.ee.service.ejb;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.EntityManagerTestRule;
import se.skillytaire.belastingdienst.ee.persistance.jpa.ReserveringJpaDAO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringResultTO;
import se.skillytaire.belastingdienst.ee.service.reservering.NieuweReserveringTO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;


public class MaakNieuweReserveringTest {
	@Rule
	public EntityManagerTestRule jpa = EntityManagerTestRule.persistenceUnit("stuga");

	@This
	private Account thisAccount;
	@This
	private Verhuurder thisVerhuurder;
	
	private MaakNieuweReserveringEJB beanUnderTest;
	private ReserveringJpaDAO beanUnderTest2;
	private AccountJpaDAO beanUnderTest3;

	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		beanUnderTest2 = new ReserveringJpaDAO();
	    beanUnderTest2.setEntityManager(jpa.em());
		beanUnderTest = new MaakNieuweReserveringEJB();
		AccountExistsEJB existEJB = new AccountExistsEJB();
		beanUnderTest3 = new AccountJpaDAO();
	    beanUnderTest3.setEntityManager(jpa.em());
		existEJB.dao = beanUnderTest3;
		beanUnderTest.accountExistsService = existEJB;
		
	}

	@Test
	public void testMaakNieuweReservering() {
		NieuweReserveringTO reserveringTO = new NieuweReserveringTO("Joop" , "Karel", 1);
		NieuweReserveringResultTO result = beanUnderTest.reserveren(reserveringTO);
		assertFalse(result.isSuccessful());
	}
}