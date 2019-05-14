package se.skillytaire.belastingdienst.ee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.persistance.AccountDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.This;

public class RegistreerNieuwAccountTest {
	// @This Klant klant;
	// @This Account account;

	// RegistreerNieuwAccount beanUnderTest;
	// EntityManagerFactory factory;
	// EntityManager em;

	// @Before
	// public void before() {
	// 	JLCRunner.init(this);
	// }

	// @Before
	// public void initJPA() {
	// 	factory = Persistence.createEntityManagerFactory("stuga");
	// 	em = factory.createEntityManager();
	// 	AccountJpaDAO.getInstance().setEntityManager(em);
	// 	beanUnderTest = new RegistreerNieuwAccount();
	// 	beanUnderTest.dao = AccountJpaDAO.getInstance();
	// }

	// @After
	// public void destroyJPA() {
	// 	if (this.em != null) {
	// 		this.em.close();
	// 	}
	// 	if (this.factory != null) {
	// 		this.factory.close();
	// 		while (this.factory.isOpen() && !Thread.interrupted()) {
	// 			try {
	// 				Thread.sleep(100);
	// 			} catch (InterruptedException e) {
	// 				Thread.currentThread().interrupt();
	// 			}
	// 		}
	// 	}
	// }

}