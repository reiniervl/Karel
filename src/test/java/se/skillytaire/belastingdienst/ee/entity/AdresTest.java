package se.skillytaire.belastingdienst.ee.entity;

/*import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;*/

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = Adres.class, asJUnit = true)
public class AdresTest {
   GPSCoordinaat gps = new GPSCoordinaat(12.513321, 55.677069);

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
      Adres volledigAdres = new Adres(this.gps);
      GPSCoordinaat actual = volledigAdres.getGpsCoordinaat();
      Assert.assertEquals(this.gps, actual);
   }

   @Test
   public void equalsGelijkTest() {
      Adres adres1 = new Adres(this.gps);
      GPSCoordinaat hetzelfde = new GPSCoordinaat(12.513321, 55.677069);
      Adres adres2 = new Adres(hetzelfde);
      Assert.assertTrue(adres1.equals(adres2));
   }

   @Test
   public void equalsGelijkeRefTest() {
      Adres adres1 = new Adres(this.gps);
      Assert.assertTrue(adres1.equals(adres1));
   }

   @Test
   public void equalsNullTest() {
      Adres adres1 = new Adres(this.gps);
      Assert.assertFalse(adres1.equals(null));
   }

   @Test
   public void equalsOngelijkTest() {
      Adres adres1 = new Adres(this.gps);
      GPSCoordinaat hetzelfde = new GPSCoordinaat(12.523321, 55.677069);
      Adres adres2 = new Adres(hetzelfde);
      Assert.assertFalse(adres1.equals(adres2));
   }

   @Test
   public void testHash() {
      Adres adres1 = new Adres(this.gps);
      int notExpected = 604107971;
      int actual = adres1.hashCode();
      Assert.assertNotEquals(notExpected, actual);
   }
}
