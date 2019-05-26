package se.skillytaire.belastingdienst.ee.service.klart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import se.skillytaire.belastingdienst.ee.service.jms.MailSender;
import se.skillytaire.java.datatype.PositiveInteger;
import se.skillytaire.service.weather.api.WeatherForecast;
import se.skillytaire.service.weather.klart.KlartWeatherService;

@Singleton
public class KlartService {
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
        System.out.println("Initialize KlartService");
        weerservice = new KlartWeatherService();
    }
    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void pollKlart() {
    	weerservice.update();
    }
}
