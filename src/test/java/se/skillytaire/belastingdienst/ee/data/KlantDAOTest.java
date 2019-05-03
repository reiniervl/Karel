package se.skillytaire.belastingdienst.ee.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Klant;

public class KlantDAOTest {
   @Test
   public void testDaoAdd() {
      Klant klant = Klant.getBuilder().addEmail("test@test.com").addUsername("username").addPassword("password").build();
      KlantDAO dao = KlantDAO.getDAO();
      assertFalse(dao == null);
      assertFalse(klant == null);
      dao.add(klant);
      assertTrue("Klant is opgeslagen", klant.isPersistant());

   }
}
