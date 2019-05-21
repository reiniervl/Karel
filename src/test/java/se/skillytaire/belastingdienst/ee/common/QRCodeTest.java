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
private Integer testBoekingsNummer = 1;

	@Test
	public void testBootNummer() {
		QRCode code = new QRCode(1);
		assertEquals(testBoekingsNummer, code.getBoekingsNummer());
	}
}