package se.skillytaire.belastingdienst.ee.service.klart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skillytaire.belastingdienst.ee.service.jms.MailSender;
import se.skillytaire.java.datatype.PositiveInteger;
import se.skillytaire.service.weather.api.WeatherForecast;
import se.skillytaire.service.weather.klart.KlartWeatherService;

@Singleton
public class KlartService {
	private static Logger log = LoggerFactory.getLogger(KlartService.class);
    @Inject
    private MailSender sender;
	private KlartWeatherService weerservice;
	
	
    public List<WeatherForecast> getForecasts(PositiveInteger daysAhead) {
		return this.weerservice.getForecasts(daysAhead).collect(Collectors.toList());
	}
	public Optional<WeatherForecast> getTodaysForecast() {
		return weerservice.getTodaysForecast();
	}
	@PostConstruct
    public void init() {
		log.info("Initialize KlartService");
        weerservice = new KlartWeatherService();
    }
    @Schedule(minute = "*/30", hour = "9,17", persistent = false)
    public void pollKlart() {
    	log.info("Updating the weather information.");
    	weerservice.update();
    }
}
