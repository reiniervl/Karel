package se.skillytaire.belastingdienst.ee.entity;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import se.skillytaire.course.tools.jlc.JLC;
import se.skillytaire.course.tools.jlc.JLCRunner;
import se.skillytaire.course.tools.jlc.TestPropertyFile;

//TODO setUsername is missing
@RunWith(JLCRunner.class)
@JLC(value = Klant.class, asJUnit = true)
@TestPropertyFile
public class KlantTest {

   // @Test
   // public void testUnsername() {
   // final String username = "test username";
   // Klant klant = new Klant();
   // klant.setUsername(username);
   //
   // Assert.assertEquals(username, klant.getUsername());
   // }

   @Test
   public void testPassword() {
      final String password = "passw0rd";
      Klant klant = new Klant();
      klant.setPassword(password);

      Assert.assertEquals(password, klant.getPassword());
   }

   @Test
   public void testEmail() {
      final String email = "test@createment.nl";
      Klant klant = new Klant();
      klant.setEmail(email);

      Assert.assertEquals(email, klant.getEmail());

      String regexPattern = "[\\d\\w]*@[\\d\\w\\.]*\\.[\\w]{2,3}$";
      Assert.assertTrue(Pattern.matches(regexPattern, email));
   }

   @Test
   public void testEmailFormat() {

      final String email = "test@createment.nl";
      Klant klant = new Klant();
      klant.setEmail(email);

      String regexPattern = "[\\d\\w]*@[\\d\\w\\.]*\\.[\\w]{2,3}$";
      Assert.assertTrue(Pattern.matches(regexPattern, klant.getEmail()));
   }

   // @Test
   // public void testReserveringen() {
   //
   // }

   // @Test
   // public void testEqual() {
   // final String username1 = new String("test username");
   // final String username2 = new String("test username");
   //
   // final String password1 = new String("password");
   // final String password2 = new String("password");
   //
   // final String email1 = new String("test@createment.nl");
   // final String email2 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = new Klant();
   // klant2.setUsername(username2);
   // klant2.setPassword(password2);
   // klant2.setEmail(email2);
   //
   // Assert.assertEquals(klant1, klant2);
   // }

   // @Test
   // public void testUsernameEqual() {
   // final String username1 = new String("test username");
   // final String username2 = new String("test username DIFFERENT");
   //
   // final String password1 = new String("password");
   // final String password2 = new String("password");
   //
   // final String email1 = new String("test@createment.nl");
   // final String email2 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = new Klant();
   // klant2.setUsername(username2);
   // klant2.setPassword(password2);
   // klant2.setEmail(email2);
   //
   // Assert.assertNotEquals(klant1, klant2);
   // }
   //
   // @Test
   // public void testPasswordEqual() {
   // final String username1 = new String("test username");
   // final String username2 = new String("test username");
   //
   // final String password1 = new String("password");
   // final String password2 = new String("DIFFERENT");
   //
   // final String email1 = new String("test@createment.nl");
   // final String email2 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = new Klant();
   // klant2.setUsername(username2);
   // klant2.setPassword(password2);
   // klant2.setEmail(email2);
   //
   // Assert.assertFalse(klant1.equals(klant2));
   // }
   //
   // @Test
   // public void testEmailEqual() {
   // final String username1 = new String("test username");
   // final String username2 = new String("test username");
   //
   // final String password1 = new String("password");
   // final String password2 = new String("password");
   //
   // final String email1 = new String("test@createment.nl");
   // final String email2 = new String("test@DIFFERENT.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = new Klant();
   // klant2.setUsername(username2);
   // klant2.setPassword(password2);
   // klant2.setEmail(email2);
   //
   // Assert.assertNotEquals(klant1, klant2);
   // }
   //
   // @Test
   // public void testInstanceEqual() {
   // final String username1 = new String("test username");
   //
   // final String password1 = new String("password");
   //
   // final String email1 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Object klant2 = new Object();
   //
   // Assert.assertNotEquals(klant1, klant2);
   // }
   //
   // @Test
   // public void testSuperEqual() {
   // final String username1 = new String("test username");
   // final String username2 = new String("test username");
   //
   // final String password1 = new String("password");
   // final String password2 = new String("password");
   //
   // final String email1 = new String("test@createment.nl");
   // final String email2 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = new Klant();
   // klant2.setUsername(username2);
   // klant2.setPassword(password2);
   // klant2.setEmail(email2);
   //
   // Assert.assertFalse(klant1.getClass().getSuperclass().equals(klant2));
   // }
   //
   // @Test
   // public void testNullEqual() {
   // final String username1 = new String("test username");
   // final String password1 = new String("password");
   // final String email1 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = null;
   //
   // Assert.assertNotEquals(klant1, klant2);
   // }
   //
   // @Test
   // public void testToString() {
   // final String username1 = new String("test username");
   // final String password1 = new String("password");
   // final String email1 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   // StringBuilder sb = new StringBuilder().append("username: ")
   // .append(klant1.getUsername()).append("\nemail: ")
   // .append(klant1.getEmail());
   //
   // Assert.assertEquals(klant1.toString(), sb.toString());
   // }
   //
   // @Test
   // public void testHashCode() {
   // final String username1 = new String("test username");
   // final String username2 = new String("test username");
   //
   // final String password1 = new String("password");
   // final String password2 = new String("password");
   //
   // final String email1 = new String("test@createment.nl");
   // final String email2 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = new Klant();
   // klant2.setUsername(username2);
   // klant2.setPassword(password2);
   // klant2.setEmail(email2);
   //
   // Assert.assertTrue(klant1.hashCode() == klant2.hashCode());
   // }
   //
   // @Test
   // public void testCompare() {
   // final String username1 = new String("test username");
   // final String username2 = new String("test username");
   //
   // final String password1 = new String("password");
   // final String password2 = new String("password");
   //
   // final String email1 = new String("test@createment.nl");
   // final String email2 = new String("test@createment.nl");
   //
   // Klant klant1 = new Klant();
   // klant1.setUsername(username1);
   // klant1.setPassword(password1);
   // klant1.setEmail(email1);
   //
   // Klant klant2 = new Klant();
   // klant2.setUsername(username2);
   // klant2.setPassword(password2);
   // klant2.setEmail(email2);
   //
   // Assert.assertTrue(klant1.compareTo(klant2) == 0);
   // }
}
