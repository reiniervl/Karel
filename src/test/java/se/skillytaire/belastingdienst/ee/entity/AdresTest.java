package se.skillytaire.belastingdienst.ee.entity;

/*import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;*/

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = Adres.class, asJUnit = true)
public class AdresTest {
   private static final GPSCoordinaat FIXED_GPS = new GPSCoordinaat(12.513321,
         55.677069);
   private static final EmbeddableGPSCoordinaat FIXED = new EmbeddableGPSCoordinaat(
         AdresTest.FIXED_GPS);

   /*
    * @Test public void testPersistence() {
    *
    * Adres adres1 = new Adres(new GPSCoordinaat(0, 0));
    * adres1.setStreet("Een"); adres1.setHouseNumber(1);
    * adres1.setNumberSuffix("a"); adres1.setCity("Aa"); adres1.setState("Aaa");
    * adres1.setPostalCode("1111 AA"); adres1.setCountry("De A"); // //
    * ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); //
    * Validator validator = factory.getValidator(); //
    * Set<ConstraintViolation<Adres>> violations = //
    * validator.validate(adres1); // violations.stream().forEach((v) ->
    * System.err.println(v));
    *
    * EntityManagerFactory factory =
    * Persistence.createEntityManagerFactory("stuga"); EntityManager em =
    * factory.createEntityManager();
    * Assert.assertFalse("Entity Manager is null", em == null);
    * Assert.assertFalse("Adres is null", em == null); em.persist(adres1);
    * Assert.assertTrue(adres1.isPersistant()); }
    */

   @Test
   public void fullConstructorTest() {
      Adres volledigAdres = new Adres(AdresTest.FIXED);
      EmbeddableGPSCoordinaat actual = volledigAdres.getGpsCoordinaat();
      Assert.assertEquals(AdresTest.FIXED, actual);
   }

   @Test
   public void equalsGelijkTest() {
      Adres adres1 = new Adres(AdresTest.FIXED);
      EmbeddableGPSCoordinaat hetzelfde = AdresTest.FIXED;
      Adres adres2 = new Adres(hetzelfde);
      Assert.assertTrue(adres1.equals(adres2));
   }

   @Test
   public void equalsGelijkeRefTest() {
      Adres adres1 = new Adres(AdresTest.FIXED);
      Assert.assertTrue(adres1.equals(adres1));
   }

   @Test
   public void equalsNullTest() {
      Adres adres1 = new Adres(AdresTest.FIXED);
      Assert.assertFalse(adres1.equals(null));
   }
   // FIXME deze test klopt niet
   // @Test
   // public void equalsOngelijkTest() {
   // Adres adres1 = new Adres(FIXED);
   // EmbeddableGPSCoordinaat hetzelfde = FIXED;
   // Adres adres2 = new Adres(hetzelfde);
   // Assert.assertFalse(adres1.equals(adres2));
   // }

   @Test
   public void testHash() {
      Adres adres1 = new Adres(AdresTest.FIXED);
      int notExpected = 604107971;
      int actual = adres1.hashCode();
      Assert.assertNotEquals(notExpected, actual);
   }
}
