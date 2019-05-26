package se.skillytaire.belastingdienst.ee.service.rest;

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
import se.skillytaire.belastingdienst.ee.entity.Adres;
import se.skillytaire.belastingdienst.ee.entity.AdresBuilder;
import se.skillytaire.belastingdienst.ee.entity.Boot;
import se.skillytaire.belastingdienst.ee.entity.BootBuilder;
import se.skillytaire.belastingdienst.ee.entity.EmbeddableGPSCoordinaat;
import se.skillytaire.belastingdienst.ee.entity.Verhuurder;
import se.skillytaire.belastingdienst.ee.entity.VerhuurderBuilder;
import se.skillytaire.belastingdienst.ee.persistance.jpa.VerhuurderJpaDAO;
import se.skillytaire.java.datatype.PositiveInteger;

@RequestScoped
@Path(Fill.ROOT_PATH)
public class Fill {
   public static final String ROOT_PATH ="init";
   public static final String FILL_GET_PATH = "fill";
   private static final Logger log = LoggerFactory.getLogger(Fill.class);
   @Inject
   VerhuurderJpaDAO dao;
   
   // Preparing the fill script
   @GET
   @Path(FILL_GET_PATH)
   @Produces(MediaType.APPLICATION_JSON)
   @Transactional
   public boolean fill() {
      
      EmbeddableGPSCoordinaat embc = new EmbeddableGPSCoordinaat(
            GPSCoordinaat.STUGA);
      Adres adres = AdresBuilder.builder()
            .withGpsCoordinaat(embc)
            .withCity("Fredriksberg")
            .withHouseNumber(5)
            .withCountry("Sweden")
            .withPostalCode("77010")
            .withStreet("Algsjokullvagen")
            .build();
      // maak een verhuurder aan
      Verhuurder verhuurder = VerhuurderBuilder.builder()
    		  .withAdres(adres)
    		  .withName("karel")
    		  .withUserName("kareluser")
    		  .build();
      for (int i = 1; i <= 10; i++) {
        // PositiveInteger bootNummer = new PositiveInteger(i);
        // Boot boot = new Boot(verhuurder, bootNummer);
         Boot boot = BootBuilder.builder().withEigenaar(verhuurder).withNummer(i).build();
         verhuurder.add(boot);
      }
      boolean succes;
      try {
      dao.add(verhuurder);
      succes = true;
      }catch(RuntimeException e) {
    	  log.warn("Sorry fillscripts has collapsed", e);
    	  succes = false;
      }
      return succes;
   }
   
   
}
