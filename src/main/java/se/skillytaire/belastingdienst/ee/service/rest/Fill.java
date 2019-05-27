package se.skillytaire.belastingdienst.ee.service.rest;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.belastingdienst.ee.common.LocalDateTimeIterator;
import se.skillytaire.belastingdienst.ee.entity.Account;
import se.skillytaire.belastingdienst.ee.entity.AccountBuilder;
import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.AdresBuilder;
import se.skillytaire.belastingdienst.ee.entity.Boeking;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.BootBuilder;
import se.skillytaire.belastingdienst.ee.entity.EmbeddableGPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Klant;
import se.skillytaire.belastingdienst.ee.entity.KlantBuilder;
import se.skillytaire.belastingdienst.ee.entity.MeerTocht;
import se.skillytaire.belastingdienst.ee.entity.Periode;
import se.skillytaire.belastingdienst.ee.entity.Reservering;
import se.skillytaire.belastingdienst.ee.entity.ReserveringBuilder;
import se.skillytaire.belastingdienst.ee.entity.RivierTocht;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.entity.VerhuurderBuilder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.AccountJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.BoekingJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.MeerTochtJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.RivierTochtJpaDAO;
import se.skillytaire.belastingdienst.ee.persistance.jpa.VerhuurderJpaDAO;
import se.skillytaire.java.datatype.PositiveInteger;

@RequestScoped
@Path(Fill.ROOT_PATH)
public class Fill {
	public static final String ROOT_PATH = "init";
	public static final String FILL_GET_PATH = "fill";
	private static final Logger log = LoggerFactory.getLogger(Fill.class);
	@Inject
	private VerhuurderJpaDAO verhuurderDao;
	
	@Inject
	private RivierTochtJpaDAO rivierTochtDao;	

	
	@Inject
	private MeerTochtJpaDAO meerTochtDao;	
	
	@Inject
	private AccountJpaDAO accountDao;
	@Inject
	private BoekingJpaDAO boekingDao;
	// Preparing the fill script
	@GET
	@Path(FILL_GET_PATH)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public boolean fill() {

		EmbeddableGPSCoordinaat embc = new EmbeddableGPSCoordinaat(GPSCoordinaat.STUGA);
		Adres adres = AdresBuilder.builder().withGpsCoordinaat(embc).withCity("Fredriksberg").withHouseNumber(5)
				.withCountry("Sweden").withPostalCode("77010").withStreet("Algsjokullvagen").build();
		// maak een verhuurder aan
		Verhuurder verhuurder = VerhuurderBuilder.builder().withAdres(adres).withName("karel").withUserName("kareluser")
				.build();
		
		Klant maarten = KlantBuilder.builder()
				.withPassword("storm")
				.withUsername("maarten")
				.withEmail("mtstorm@gmail.com")
				.build();
		
		for (int i = 1; i <= 10; i++) {
			// PositiveInteger bootNummer = new PositiveInteger(i);
			// Boot boot = new Boot(verhuurder, bootNummer);
			Boot boot = BootBuilder.builder().withEigenaar(verhuurder).withNummer(i).build();
			verhuurder.add(boot);
		}
		boolean succes;
		try {
			verhuurderDao.add(verhuurder);
			Account account = AccountBuilder.builder()
					.withVerhuurder(verhuurder)
					.withKlant(maarten)
					.build();
			accountDao.add(account);
			

			
			//Hoe weet karel nu welke tochten van hem zijn???
			LocalDateTime from = LocalDateTime.now();
			LocalDateTime to = from.plusDays(30);
			
			Periode verloopPeriode = new Periode(from, to);
			Reservering reservering = ReserveringBuilder.builder()
					.withAccount(account)
					.withVerloopDatum(verloopPeriode )
					.build();
			boolean addToReservering = false;
			LocalDateTimeIterator it = new LocalDateTimeIterator(from, to, t -> t.plusHours(1));
			while (it.hasNext()) {
				to = it.next();
			
				Periode reserveringsPeriode = new Periode(from, to);
				MeerTocht meertocht = new MeerTocht(99.99, reserveringsPeriode);
				RivierTocht rivierTocht = new RivierTocht(159.67, reserveringsPeriode);
				from = to;
				this.meerTochtDao.add(meertocht);
				this.rivierTochtDao.add(rivierTocht);
				
				if(!addToReservering) {
					reservering.add(rivierTocht);
					reservering.add(rivierTocht);
				}
			}
			
			Boeking boeking = new Boeking(reservering);
			boekingDao.add(boeking);
			
			succes = true;
		} catch (RuntimeException e) {
			log.warn("Sorry fillscripts has collapsed", e);
			succes = false;
		}
		return succes;
	}

}
