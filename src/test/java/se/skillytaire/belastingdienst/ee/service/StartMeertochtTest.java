package se.skillytaire.belastingdienst.ee.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;

import se.skillytaire.belastingdienst.ee.persistance.jpa.KlantJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtJpaDAO;
import se.skillytaire.course.tools.jlc.JLCRunner;

public class StartMeertochtTest {

	private StartMeertocht beanUnderTest;
	private EntityManagerFactory factory;
	private EntityManager entityManager;

	@Before
	public void before() {
		JLCRunner.init(this);
	}

	@Before
	public void initJPA() {
		this.factory = Persistence.createEntityManagerFactory("stuga");
		this.entityManager = this.factory.createEntityManager();
		MeerTochtJpaDAO.getInstance().setEntityManager(this.entityManager);
	}
}