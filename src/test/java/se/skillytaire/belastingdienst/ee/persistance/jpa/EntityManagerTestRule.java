package se.skillytaire.belastingdienst.ee.persistance.jpa;

import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class EntityManagerTestRule implements TestRule {
	private EntityManagerFactory factory;
	private EntityManager entityManager;


    private EntityManagerTestRule(String unitName) {
    	this.factory = Persistence.createEntityManagerFactory(unitName);
        this.entityManager = factory.createEntityManager();
        
    }

    public final static EntityManagerTestRule persistenceUnit(String unitName) {
        return new EntityManagerTestRule(unitName);
    }
    
    public EntityManager em() {
        return this.entityManager;
    }


    public EntityTransaction getNewTransaction() {
    	EntityTransaction tx = this.entityManager.getTransaction();
        return tx;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                entityManager.clear();
                if (EntityManagerTestRule.this.entityManager != null) {
                	EntityManagerTestRule.this.entityManager.close();
                 }
                 if (EntityManagerTestRule.this.factory != null) {
                	 EntityManagerTestRule.this.factory.close();
                    while (EntityManagerTestRule.this.factory.isOpen() && !Thread.interrupted()) {
                       try {
                          Thread.sleep(10);
                       } catch (InterruptedException e) {
                          Thread.currentThread().interrupt();
                       }
                    }
                 }                
            }

        };
    }

}
