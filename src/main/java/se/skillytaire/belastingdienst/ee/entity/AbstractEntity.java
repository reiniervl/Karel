package se.skillytaire.belastingdienst.ee.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import se.skillytaire.belastingdienst.ee.common.AbstractComparableObject;

@MappedSuperclass
public abstract class AbstractEntity<E extends AbstractEntity<E>>
      extends AbstractComparableObject<E> implements Cloneable {
   private static final long serialVersionUID = 1L;
   /*
    * The database primairy key. JPA will set this, there is no set-method.
    */
   @Id
   @GeneratedValue
   private Integer oid;

   /**
    * Developers should not use the default constructor. Please use the same
    * visibility modifier "protected" for overriding classes.
    */
   protected AbstractEntity() {
   }

   /**
    * @param that
    *           an other entity
    */
   protected AbstractEntity(final E that) {
      if (that == null) {
         throw new IllegalArgumentException("That is void");
      }
      this.oid = that.getOid();
   }

   public Integer getOid() {
      return this.oid;
   }

   public boolean isPersistant() {
      return this.oid != null;
   }

   /**
    * Checks if the database identity is identical
    *
    * @param that
    *           the other entity
    * @return true when the entities have the same database identity.
    */
   public boolean isIdentical(final E that) {
      boolean isIdentical = false;
      if (that != null && this.isPersistant() && that.isPersistant()) {
         isIdentical = this.getOid().equals(that.getOid());
      }
      return isIdentical;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E clone() {
      E clone;
      try {
         clone = (E) super.clone();
      } catch (CloneNotSupportedException e) {
         throw new AssertionError(
               "The cloneable interface is implemented, may never happen", e);
      }
      return clone;
   }

   @Override
   public String toString() {
      return String.format(", oid=%s, persistant=%s]", this.oid,
            this.isPersistant());
   }
}
