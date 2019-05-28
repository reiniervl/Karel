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
import se.skillytaire.belastingdienst.ee.service.ejb.FillScriptEJB;
import se.skillytaire.java.datatype.PositiveInteger;

@RequestScoped
@Path(Fill.ROOT_PATH)
public class Fill {
	public static final String ROOT_PATH = "init";
	public static final String FILL_GET_PATH = "fill";
	private static final Logger log = LoggerFactory.getLogger(Fill.class);
	@Inject
	private FillScriptEJB ejb;
	// Preparing the fill script
	@GET
	@Path(FILL_GET_PATH)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public boolean fill() {
		return ejb.fill();
	}

}
