package se.skillytaire.belastingdienst.ee.persistance.jpa;

import org.junit.Assert;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Klant;

public class KlantDAOTest {
   @Test
   public void testDaoAdd() {
      Klant klant = Klant.getBuilder().addEmail("test@test.com")
            .addUsername("username").addPassword("password").build();
      KlantDAO dao = KlantDAO.getDAO();
      Assert.assertFalse(dao == null);
      Assert.assertFalse(klant == null);
      dao.add(klant);
      Assert.assertTrue("Klant is opgeslagen", klant.isPersistant());

   }
}
