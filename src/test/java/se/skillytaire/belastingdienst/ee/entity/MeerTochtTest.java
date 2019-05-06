package se.skillytaire.belastingdienst.ee.entity;

import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;

@RunWith(JLCRunner.class)
@JLC(value = MeerTocht.class, asJUnit = true)
public class MeerTochtTest {

   @Test
   public void TestValidation() {
      javax.validation.ValidatorFactory VALIDATOR_FACTORY =
            Validation.buildDefaultValidatorFactory();

      Validator validator = VALIDATOR_FACTORY.getValidator();
      Set<ConstraintViolation<Object>> violations =
            validator.validate(new MeerTocht());
      for (ConstraintViolation<Object> constraintViolation : violations) {
         System.out.println(constraintViolation);
      }
      assertFalse(violations.isEmpty());
   }
}
