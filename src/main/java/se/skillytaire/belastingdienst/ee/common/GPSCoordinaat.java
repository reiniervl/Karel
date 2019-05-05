package se.skillytaire.belastingdienst.ee.common;

import java.io.Serializable;
import java.util.Comparator;

public final class GPSCoordinaat extends AbstractComparableObject<GPSCoordinaat>
      implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final double MIN_LONGITUDE = -180;
   public static final double MAX_LONGITUDE = 180;
   public static final double MIN_LATITUDE = -85.05112878;
   public static final double MAX_LATITUDE = 85.05112878;
   public static final GPSCoordinaat STUGA = new GPSCoordinaat(60.138421,
         14.360336);
   public static final GPSCoordinaat MAX_VALUE = new GPSCoordinaat(
         GPSCoordinaat.MAX_LONGITUDE, GPSCoordinaat.MAX_LATITUDE);
   public static final GPSCoordinaat MIN_VALUE = new GPSCoordinaat(
         GPSCoordinaat.MIN_LONGITUDE, GPSCoordinaat.MIN_LATITUDE);
   /**
    * Natural order of the GPS coordinate
    */
   public static final Comparator<
         GPSCoordinaat> COMPARATOR_NATURAL_ORDER = new NaturalOrder();
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
      return GPSCoordinaat.hashCode(this);
   }

   public static int hashCode(final GPSCoordinaat gps) {
      return GPSCoordinaat.hashCode(gps.getLongtitude(), gps.getLatitude());
   }

   public static int hashCode(final double longitude, final double latitude) {
      int longHash = Double.hashCode(longitude);
      int latHash = Double.hashCode(latitude);
      return longHash ^ latHash;
   }

   @Override
   public int compareTo(final GPSCoordinaat that) {
      return GPSCoordinaat.COMPARATOR_NATURAL_ORDER.compare(this, that);
   }

   private static class NaturalOrder implements Comparator<GPSCoordinaat> {

      @Override
      public int compare(final GPSCoordinaat aThis, final GPSCoordinaat aThat) {
         int compareTo = Double.compare(aThis.getLongtitude(),
               aThat.getLongtitude());
         if (compareTo == 0) {
            compareTo = Double.compare(aThis.getLatitude(),
                  aThat.getLatitude());
         }
         return compareTo;
      }

   }
}
