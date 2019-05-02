package se.skillytaire.belastingdienst.ee.entity;

import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = GPSCoordinaat.class, asJUnit = true)
public class GPSCoordinaatTest {
   @Test(expected = IllegalArgumentException.class)
   public void testDBC_001() {
      new GPSCoordinaat(GPSCoordinaat.MIN_LONGITUDE - 1,
            GPSCoordinaat.MIN_LATITUDE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDBC_002() {
      new GPSCoordinaat(GPSCoordinaat.MIN_LONGITUDE,
            GPSCoordinaat.MIN_LATITUDE - 1);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDBC_003() {
      new GPSCoordinaat(GPSCoordinaat.MAX_LONGITUDE + 1,
            GPSCoordinaat.MAX_LATITUDE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDBC_004() {
      new GPSCoordinaat(GPSCoordinaat.MAX_LONGITUDE,
            GPSCoordinaat.MAX_LATITUDE + 1);
   }
}
