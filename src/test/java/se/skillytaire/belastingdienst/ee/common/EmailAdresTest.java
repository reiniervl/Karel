package se.skillytaire.belastingdienst.ee.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailAdresTest {

   @Test
   public void testSetAndGet() {
      final String naam = "naam";
      EmailAdres emailAdres = new EmailAdres(naam);

      assertEquals(naam, emailAdres.getValue());
   }

   @Test
   public void testIsNull() {
      final String naam = null;
      EmailAdres emailAdres = new EmailAdres(naam);

      assertNull(emailAdres.getValue());
   }

   @Test
   public void testValid() {
      final String naam = "test@createment.nl";
      EmailAdres emailAdres = new EmailAdres(naam);

      assertTrue(emailAdres.validate());
      assertTrue(emailAdres.validate(naam));
   }

   @Test
   public void testIsNotNull() {
      final String naam = "test@createment.nl";
      EmailAdres emailAdres = new EmailAdres(naam);

      assertNotNull(emailAdres.getValue());
   }

   @Test
   public void testValidateLeeg() {
      final String naam = "";
      EmailAdres emailAdres = new EmailAdres(naam);

      assertFalse(emailAdres.validate());
      assertFalse(emailAdres.validate(naam));
   }
}
