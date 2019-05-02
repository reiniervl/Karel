package se.skillytaire.belastingdienst.ee.common;

import org.junit.Assert;
import org.junit.Test;

public class EmailAdresTest {

   @Test
   public void testSetAndGet() {
      final String naam = "naam";
      EmailAdres emailAdres = new EmailAdres(naam);

      Assert.assertEquals(naam, emailAdres.getValue());
   }

   @Test
   public void testIsNull() {
      final String naam = null;
      EmailAdres emailAdres = new EmailAdres(naam);

      Assert.assertNull(emailAdres.getValue());
   }

   @Test
   public void testValid() {
      final String naam = "test@createment.nl";
      EmailAdres emailAdres = new EmailAdres(naam);

      Assert.assertTrue(emailAdres.validate());
      Assert.assertTrue(emailAdres.validate(naam));
   }

   @Test
   public void testIsNotNull() {
      final String naam = "test@createment.nl";
      EmailAdres emailAdres = new EmailAdres(naam);

      Assert.assertNotNull(emailAdres.getValue());
   }

   @Test
   public void testValidateLeeg() {
      final String naam = "";
      EmailAdres emailAdres = new EmailAdres(naam);

      Assert.assertFalse(emailAdres.validate());
      Assert.assertFalse(emailAdres.validate(naam));
   }
}
