package se.skillytaire.belastingdienst.ee.service.ejb;

import java.time.LocalDateTime;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
import se.skillytaire.belastingdienst.ee.service.rest.Fill;

@Stateless
public class FillScriptEJB {
	private static final Logger log = LoggerFactory.getLogger(FillScriptEJB.class);
	@Inject
	VerhuurderJpaDAO verhuurderDao;
	@Inject
	RivierTochtJpaDAO rivierTochtDao;	
	@Inject
	MeerTochtJpaDAO meerTochtDao;	
	@Inject
	AccountJpaDAO accountDao;
	@Inject
	BoekingJpaDAO boekingDao;
	
	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
					reservering.add(meertocht);
					reservering.add(rivierTocht);
					addToReservering = true;
				}
			}
		//	account.add(reservering);
			accountDao.update(account);
			
			Boeking boeking = new Boeking(reservering);
			boekingDao.add(boeking);
			
//            ValidatorFactory factory =
//                    Validation.buildDefaultValidatorFactory();
//              Validator validator = factory.getValidator();
//              Set<ConstraintViolation<Object>> violations =
//                    validator.validate(verhuurder);
//
//  			for (ConstraintViolation<?> constraintViolation : violations) {
//  				System.out.println("Violation: '"+ constraintViolation.getMessage()+"' propertyPath:=" +constraintViolation.getPropertyPath() +" Root bean:="+ constraintViolation.getRootBean()+"  leafbean:="+constraintViolation.getLeafBean());
//  			}
			succes = true;
		} catch (ConstraintViolationException e) {
			succes = false;
			log.warn("Sorry fillscripts has collapsed based on violations", e);
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : violations) {
				log.warn("Violation: '"+ constraintViolation.getMessage()+"' propertyPath:=" +constraintViolation.getPropertyPath() +" Root bean:="+ constraintViolation.getRootBean()+"  leafbean:="+constraintViolation.getLeafBean());
			}
		} catch (RuntimeException e) {
			succes = false;
			log.warn("Sorry fillscripts has collapsed", e);
		}
		return succes;
	}

	

}
