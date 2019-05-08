package se.skillytaire.belastingdienst.ee.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.ImmutableObject;
import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
@RunWith(JLCRunner.class)
@JLC(value = Gebruikersnaam.class, asJUnit = true)
@ImmutableObject
public class GebruikersnaamTest {

   @Test
   public void testSetAndGet() {
      final String naam = "naam";
      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);

      Assert.assertEquals(naam, gebruikersnaam.getValue());
   }

//   @Test
//   public void testIsNull() {
//      final String naam = null;
//      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);
//
//      Assert.assertNull(gebruikersnaam.getValue());
//   }

   @Test
   public void testIsNotNull() {
      final String naam = "naam";
      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);

      Assert.assertNotNull(gebruikersnaam.getValue());
   }
//
//   @Test
//   public void testValidateLeeg() {
//      final String naam = "";
//      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);
//
//      Assert.assertFalse(gebruikersnaam.validate());
//      Assert.assertFalse(gebruikersnaam.validate(naam));
//   }

//   @Test
//   public void testValidateTeKort() {
//      Gebruikersnaam naam = new Gebruikersnaam("a");
//
//      Assert.assertFalse(naam.validate());
//   }
//
//   @Test
//   public void testValidateTeLang() {
//      Gebruikersnaam naam = new Gebruikersnaam("abcdefghijklmnopqrstuvwxyz");
//
//      Assert.assertFalse(naam.validate());
//   }

//   @Test
//   public void testValidateBegintMetGetal() {
//      Gebruikersnaam naam = new Gebruikersnaam("1naam");
//
//      Assert.assertFalse(naam.validate());
//   }

//   @Test
//   public void testValidateBevatSpatie() {
//      Gebruikersnaam naam = new Gebruikersnaam("na am");
//
//      Assert.assertFalse(naam.validate());
//   }

}
