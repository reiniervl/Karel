package se.skillytaire.belastingdienst.ee.service;

public class StartMeerTochtTest {
	/*
	 * @This private Account account;
	 * 
	 * @This private MeerTocht meerTocht;
	 * 
	 * StartMeertocht startMeerTocht;
	 * 
	 * EntityManagerFactory factory; EntityManager em;
	 * 
	 * @Before public void before() { JLCRunner.init(this); }
	 * 
	 * @Before public void setupJPA() { factory =
	 * Persistence.createEntityManagerFactory("stuga"); em =
	 * factory.createEntityManager(); startMeerTocht = new StartMeertocht();
	 * BootJpaDAO.getInstance().setEntityManager(em);
	 * MeerTochtJpaDAO.getInstance().setEntityManager(em); startMeerTocht.bootDAO =
	 * BootJpaDAO.getInstance(); startMeerTocht.meerTochtDAO =
	 * MeerTochtJpaDAO.getInstance(); }
	 * 
	 * @After public void destroyJPA() { if (this.em != null) { this.em.close(); }
	 * if (this.factory != null) { this.factory.close(); while
	 * (this.factory.isOpen() && !Thread.interrupted()) { try { Thread.sleep(100); }
	 * catch (InterruptedException e) { Thread.currentThread().interrupt(); } } } }
	 * 
	 * private void addWithTX(final MeerTocht meerTocht) { EntityTransaction
	 * unmanagedTx = this.em.getTransaction(); try { unmanagedTx.begin();
	 * startMeerTocht.meerTochtDAO.add(meerTocht); unmanagedTx.commit(); } catch
	 * (RuntimeException e) { if (unmanagedTx.isActive()) { unmanagedTx.rollback();
	 * } throw e; } }
	 * 
	 * @Test public void TestStartMeerTocht() { assertTrue("meerTocht != null",
	 * meerTocht != null); addWithTX(meerTocht); QRCode code = new
	 * QRCode(meerTocht.getBoot().getEigenaar().getUserName(),
	 * meerTocht.getBoot().getNummer()); assertFalse("Tocht nog niet gestart",
	 * meerTocht.getActuelePeriode().isGestart()); StartMeerTochtResultTO result =
	 * startMeerTocht.start(code); assertTrue("result is successful",
	 * result.isSuccessful()); }
	 */
}