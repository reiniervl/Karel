package se.skillytaire.belastingdienst.ee.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.ImmutableObject;
import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = QRCode.class, asJUnit = true)
@ImmutableObject
public class QRCodeTest {
	@Test
	public void testUsername() {
		QRCode code = new QRCode("bert", 1);
		assertEquals("bert", code.getUsername());
	}

	@Test
	public void testBootNummer() {
		QRCode code = new QRCode("bert", 1);
		assertEquals(1, code.getBootNummer());
	}
}