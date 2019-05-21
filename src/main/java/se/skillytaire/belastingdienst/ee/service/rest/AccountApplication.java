package se.skillytaire.belastingdienst.ee.service.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(AccountApplication.PATH)
public class AccountApplication extends Application {
   public static final String PATH ="login";
}