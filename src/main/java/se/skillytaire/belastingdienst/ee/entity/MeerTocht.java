package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("m")
public class MeerTocht extends Tocht<MeerTocht> {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

}
