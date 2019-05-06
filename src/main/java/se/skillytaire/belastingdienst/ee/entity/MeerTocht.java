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

   public MeerTocht() {
      super();
      // TODO Auto-generated constructor stub
   }

   public MeerTocht(Double prijs, Periode reserveringsPeriode) {
      super(prijs, reserveringsPeriode);
      // TODO Auto-generated constructor stub
   }

   public MeerTocht(MeerTocht tocht) {
      super(tocht);
      // TODO Auto-generated constructor stub
   }

   public static Object getBuilder() {
      // TODO Auto-generated method stub
      return null;
   }

   public void add(MeerTocht thisMeerTocht) {
      // TODO Auto-generated method stub

   }

}
