package se.skillytaire.belastingdienst.ee.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GPSCoordinaat extends AbstractComparableObject<GPSCoordinaat>
      implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final double MIN_LONGITUDE = -180;
   public static final double MAX_LONGITUDE = 180;
   public static final double MIN_LATITUDE = -85.05112878;
   public static final double MAX_LATITUDE = 85.05112878;
   public static final GPSCoordinaat STUGA =
         new GPSCoordinaat(60.138421, 14.360336);
   public static final GPSCoordinaat MAX_VALUE = new GPSCoordinaat(
         GPSCoordinaat.MAX_LONGITUDE, GPSCoordinaat.MAX_LATITUDE);
   public static final GPSCoordinaat MIN_VALUE = new GPSCoordinaat(
         GPSCoordinaat.MIN_LONGITUDE, GPSCoordinaat.MIN_LATITUDE);
   private double longtitude;
   private double latitude;

   public GPSCoordinaat(final double longitude, final double latitude) {
      if (longitude < GPSCoordinaat.MIN_LONGITUDE) {
         String msg = String.format("longitude '%f' less then '%f'", longitude,
               GPSCoordinaat.MIN_LONGITUDE);
         throw new IllegalArgumentException(msg);
      }
      if (longitude > GPSCoordinaat.MAX_LONGITUDE) {
         String msg = String.format("longitude '%f' more then '%f'", longitude,
               GPSCoordinaat.MAX_LONGITUDE);
         throw new IllegalArgumentException(msg);
      }
      if (latitude < GPSCoordinaat.MIN_LATITUDE) {
         String msg = String.format("latitude '%f' less then '%f'", latitude,
               GPSCoordinaat.MIN_LATITUDE);
         throw new IllegalArgumentException(msg);
      }
      if (latitude > GPSCoordinaat.MAX_LATITUDE) {
         String msg = String.format("latitude '%f' more then '%f'", latitude,
               GPSCoordinaat.MAX_LATITUDE);
         throw new IllegalArgumentException(msg);
      }
      this.longtitude = longitude;
      this.latitude = latitude;
   }

   public GPSCoordinaat() {
      this(0, 0);
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
      int longAsInt = (int) this.getLongtitude();
      return AbstractComparableObject.HASH_PRIME ^ longAsInt;
   }

   @Override
   public int compareTo(final GPSCoordinaat that) {
      int compareTo =
            Double.compare(this.getLongtitude(), that.getLongtitude());
      if (compareTo == 0) {
         compareTo = Double.compare(this.getLatitude(), that.getLatitude());
      }
      return compareTo;
   }
}
