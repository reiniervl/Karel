package se.skillytaire.belastingdienst.ee.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.ImmutableObject;
import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = Password.class, asJUnit = true)
@ImmutableObject
public class PasswordTest {
	@Test
	public void testGetValue() {
		final String p_sz = "passw0rd";
		final Password password = new Password(p_sz);
		assertEquals(p_sz, password.getValue());
	}
}