package se.skillytaire.belastingdienst.ee.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.TestPropertyFile;
import se.skillytaire.course.tools.jlc.This;

@RunWith(JLCRunner.class)
@JLC(value = Verhuurder.class, asJUnit = true)
@TestPropertyFile
public class VerhuurderTest {
   @This
   private Verhuurder verhuurder;
   @This
   private Adres adres;

   // @Property(key="app.url.http")
   // private static String grapje;
   @Test
   public void testAdres() {
      this.verhuurder.setAdres(this.adres);
      Adres actual = this.verhuurder.getAdres();
      Assert.assertEquals(this.adres, actual);
      // System.out.println(grapje);
   }
}
