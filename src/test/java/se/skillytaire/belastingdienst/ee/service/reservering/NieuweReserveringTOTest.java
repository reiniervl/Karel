//package se.skillytaire.belastingdienst.ee.service.reservering;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import se.skillytaire.belastingdienst.ee.entity.Account;
//import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
//import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
//import se.skillytaire.belastingdienst.ee.service.account.NieuweKlantResultTO;
//import se.skillytaire.course.tools.jlc.JLCRunner;
//import se.skillytaire.course.tools.jlc.This;
//
//public class NieuweReserveringTOTest {
//	@This
//	private RivierTocht thisRivierTocht;
//	@This
//	private MeerTocht thisMeerTocht;
//	@This
//	private Account thisAccount;
//
//	@Before
//	public void initJLC(){
//		JLCRunner.init(this);
//	}
//	
//	@Test
//	public void testEmptyReserveringTO() {
//		NieuweReserveringResultTO result = new NieuweReserveringResultTO();
//		assertEquals(NieuweKlantResultTO.NIEUWE_KLANT_EXISTS, result.getCode());
//	}
//	@Test
//	public void testNieuweReserveringTO() {
//		NieuweReserveringTO reserveringTO = new NieuweReserveringTO(thisAccount, thisRivierTocht);
//		assertTrue(reserveringTO.getAccount() != null);
//		assertTrue(reserveringTO.getMijnTochten() != null);
//		assertTrue(reserveringTO.toString() != null);
//	}
//	
//	@Test
//	public void testNieuweReserveringTO2() {
//		NieuweReserveringTO reserveringTO = new NieuweReserveringTO(thisAccount, thisMeerTocht);
//		assertTrue(reserveringTO.getAccount() != null);
//		assertTrue(reserveringTO.getMijnTochten() != null);
//		assertTrue(reserveringTO.toString() != null);
//	}
//
//	@SuppressWarnings("unused")
//	@Test(expected = IllegalArgumentException.class)
//	public void testNoAccount() {
//		NieuweReserveringTO reserveringTO = new NieuweReserveringTO(null, thisRivierTocht);
//	}
//	
//	@SuppressWarnings("unused")
//	@Test(expected = IllegalArgumentException.class)
//	public void testNoAccount2() {
//		NieuweReserveringTO reserveringTO = new NieuweReserveringTO(null, thisMeerTocht);
//	}
//		
//	@Test
//	public void getReserveringsDatum() {
//		NieuweReserveringTO reserveringTO = new NieuweReserveringTO(thisAccount, thisMeerTocht);
//		assertTrue(reserveringTO.getReserveringsDatum() != null);
//	}
//	
//	@Test
//	public void getVerloopDatum() {
//		NieuweReserveringTO reserveringTO = new NieuweReserveringTO(thisAccount, thisMeerTocht);
//		assertTrue(reserveringTO.getVerloopDatum() != null);
//	}
//}