package se.skillytaire.belastingdienst.ee.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class GebruikersnaamTest {

   @Test
   public void testSetAndGet() {
      final String naam = "naam";
      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);

      assertEquals(naam, gebruikersnaam.getValue());
   }

   @Test
   public void testIsNull() {
      final String naam = null;
      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);

      assertNull(gebruikersnaam.getValue());
   }

   @Test
   public void testIsNotNull() {
      final String naam = "naam";
      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);

      assertNotNull(gebruikersnaam.getValue());
   }

   @Test
   public void testValidateLeeg() {
      final String naam = "";
      Gebruikersnaam gebruikersnaam = new Gebruikersnaam(naam);

      assertFalse(gebruikersnaam.validate());
      assertFalse(gebruikersnaam.validate(naam));
   }

   @Test
   public void testValidateTeKort() {
      Gebruikersnaam naam = new Gebruikersnaam("a");

      assertFalse(naam.validate());
   }

   @Test
   public void testValidateTeLang() {
      Gebruikersnaam naam = new Gebruikersnaam("abcdefghijklmnopqrstuvwxyz");

      assertFalse(naam.validate());
   }

   @Test
   public void testValidateBegintMetGetal() {
      Gebruikersnaam naam = new Gebruikersnaam("1naam");

      assertFalse(naam.validate());
   }

   @Test
   public void testValidateBevatSpatie() {
      Gebruikersnaam naam = new Gebruikersnaam("na am");

      assertFalse(naam.validate());
   }

}
