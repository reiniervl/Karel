package se.skillytaire.belastingdienst.ee.persistance.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Handigheidje, kunnen we nog een JUnit Rule van maken
 *
 */
public class JPAMappingTest {

   
   @Test
   public void testMapping() {
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("stuga");
       EntityManager entityManager =factory.createEntityManager();     
       if (entityManager != null) {
          entityManager.close();
       }
       if (factory != null) {
          factory.close();
          while (factory.isOpen() && !Thread.interrupted()) {
             try {
                Thread.sleep(10);
             } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
             }
          }
       }
   }

}
