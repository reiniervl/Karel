package se.skillytaire.belastingdienst.ee.common;

import java.io.Serializable;

public abstract class AbstractComparableObject<E>
      implements Comparable<E>, Serializable {
   private static final long serialVersionUID = 1L;
   /**
    * Prime number used for calculating hash code
    *
    * @see #hashCode()
    */
   protected static final int HASH_PRIME = 13;

   @Override
   public boolean equals(final Object obj) {
      boolean equal = super.equals(obj);
      if (!equal && obj != null && this.getClass() == obj.getClass()) {
         @SuppressWarnings("unchecked")
         E that = (E) obj;
         equal = this.compareTo(that) == 0;
      }
      return equal;
   }

   @Override
   public abstract int hashCode();
}
