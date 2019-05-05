package se.skillytaire.belastingdienst.ee.entity;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;
import se.skillytaire.java.datatype.PositiveInteger;

@TestFactory
public class PositiveTestFactory
      extends AbstractComparableTestObjectFactory<PositiveInteger> {
   @Override
   public PositiveInteger createThat() {
      return new PositiveInteger(2);
   }

   @Override
   public PositiveInteger createThis() {
      return new PositiveInteger(1);
   }

   @Override
   public PositiveInteger createGreaterThen() {
      return new PositiveInteger(121);
   }

   @Override
   public PositiveInteger createLessThen() {
      return new PositiveInteger(0);
   }

}
