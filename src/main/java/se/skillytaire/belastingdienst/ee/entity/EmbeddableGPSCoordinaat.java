package se.skillytaire.belastingdienst.ee.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import se.skillytaire.belastingdienst.ee.common.AbstractComparableObject;
import se.skillytaire.belastingdienst.ee.common.GPSCoordinaat;
import se.skillytaire.didactic.annotation.fluent.Fluent;
import se.skillytaire.didactic.annotation.fluent.FluentConstructorArgument;
@Fluent
@Embeddable
public class EmbeddableGPSCoordinaat
      extends AbstractComparableObject<EmbeddableGPSCoordinaat>
      implements Serializable {

   private static final long serialVersionUID = 1L;
   public static final String PROPERTY_LAT = "latitude";
   public static final String PROPERTY_LONG = "longtitude";
   
   @Fluent( required=true)
   private double longtitude;
   @Fluent( required=true)
   private double latitude;

   public EmbeddableGPSCoordinaat(final GPSCoordinaat gps) {
      if (gps == null) {
         throw new IllegalArgumentException("gps is void");
      }
      this.longtitude = gps.getLongtitude();
      this.latitude = gps.getLongtitude();
   }
  
   @FluentConstructorArgument(fieldName="longtitude")
   @FluentConstructorArgument(fieldName="latitude")
  
   public EmbeddableGPSCoordinaat(double longtitude, double latitude) {
      this.longtitude = longtitude;
      this.latitude = latitude;
   }
   

   public EmbeddableGPSCoordinaat() {
   }

   public double getLongtitude() {
      return this.longtitude;
   }

   public double getLatitude() {
      return this.latitude;
   }

   @Override
   public String toString() {
      return "GPSCoordinaat [longtitude=" + this.longtitude + ", latitude="
            + this.latitude + "]";
   }

   @Override
   public int hashCode() {
      return GPSCoordinaat.hashCode(this.getLongtitude(), this.getLatitude());
   }

   @Override
   public int compareTo(final EmbeddableGPSCoordinaat that) {
      int compareTo = Double.compare(this.getLongtitude(),
            that.getLongtitude());
      if (compareTo == 0) {
         compareTo = Double.compare(this.getLatitude(), that.getLatitude());
      }
      return compareTo;
   }
}
