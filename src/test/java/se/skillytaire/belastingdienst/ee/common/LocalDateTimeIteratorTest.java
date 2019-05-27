package se.skillytaire.belastingdienst.ee.common;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class LocalDateTimeIteratorTest {
	@Test
	public void quickTest() {
		
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = from.plusDays(1);
		
		LocalDateTimeIterator it = new LocalDateTimeIterator(from, to, t -> t.plusHours(1));
		int counter = 0;
		while (it.hasNext()) {
			it.next();
			counter++;
			
		}
		assertEquals(24, counter);
	}
}
