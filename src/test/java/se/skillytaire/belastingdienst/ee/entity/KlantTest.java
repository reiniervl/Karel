package se.skillytaire.belastingdienst.ee.entity;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.TestPropertyFile;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = Klant.class, asJUnit = true)
@TestPropertyFile
public class KlantTest {
   /**
    * The validation factory
    */
   private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

   @This
   private Klant klant;
   @Test
   public void testNullPassword() {
      klant.setPassword(null);
      Validator validator = VALIDATOR_FACTORY.getValidator();
      Set<ConstraintViolation<Object>> violations = validator.validate(klant);
      assertTrue(!violations.isEmpty());
   }
   
}