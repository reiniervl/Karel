package se.skillytaire.belastingdienst.ee.common;

import se.skillytaire.course.tools.jlc.AbstractComparableTestObjectFactory;
import se.skillytaire.course.tools.jlc.TestFactory;

@TestFactory
public class EmailAdresTestFactory 
 extends AbstractComparableTestObjectFactory<EmailAdres> {

   @Override
   public EmailAdres createGreaterThen() {
      return new EmailAdres("zzz@zzz.com");
   }

   @Override
   public EmailAdres createLessThen() {
      return new EmailAdres("aaa@aaa.com");
   }

   @Override
   public EmailAdres createThat() {
      return new EmailAdres("kim@gmail.com");
   }

   @Override
   public EmailAdres createThis() {
      return new EmailAdres("karel@gmail.com");
   }

}
