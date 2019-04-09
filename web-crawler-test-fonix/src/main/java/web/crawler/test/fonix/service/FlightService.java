package web.crawler.test.fonix.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.crawler.test.fonix.model.FlightEntity;
import web.crawler.test.fonix.model.RegistrationInfoEntity;
import web.crawler.test.fonix.repository.FlightRepository;
import web.crawler.test.fonix.repository.RegistrationInfoRepositiry;

@Service
public class FlightService {
	
	private final RegistrationInfoRepositiry registrationInfoRepositiry;
	private final FlightRepository flightRepository;
	private final EmailService emailService;

	
	@Autowired
	public FlightService(final RegistrationInfoRepositiry registrationInfoRepositiry, final FlightRepository flightRepository, final EmailService emailService) {
		this.registrationInfoRepositiry = registrationInfoRepositiry;
		this.flightRepository = flightRepository;
		this.emailService = emailService;
	}
	
	public RegistrationInfoEntity registerFlightNotificationRequest(RegistrationInfoEntity registrationInfoEntity) {
		List<FlightEntity> flights = flightRepository.findByOriginAndDestinationOrderByPriceAsc(registrationInfoEntity.getOrigin(), 
				registrationInfoEntity.getDestination());
		
		FlightEntity bestAvailableFare = null;
		if(flights.size() > 0) {
			bestAvailableFare = flights.get(0);
			registrationInfoEntity.setBestSeenPrice(flights.get(0).getPrice());
		}
		registrationInfoEntity = registrationInfoRepositiry.saveAndFlush(registrationInfoEntity);
		
		emailService.sendWelcomeEmailToNewUser(registrationInfoEntity, bestAvailableFare);
			
		return registrationInfoEntity;
	}
	
	public FlightEntity addFlight(String origin, String destination, BigDecimal price, String flightDateTime, String flightNumber) {
		String[] dateAndTime = flightDateTime.split(" ");
		String[] flightDate = dateAndTime[0].split("-");
		String[] flightTime = dateAndTime[1].split(":");

		Timestamp flightTimestamp = new Timestamp(Integer.parseInt(flightDate[2]) - 1900, Integer.parseInt(flightDate[1]) - 1, Integer.parseInt(flightDate[0]), Integer.parseInt(flightTime[0]), Integer.parseInt(flightTime[1]), 0, 0);
		
		FlightEntity flightEntity = flightRepository.saveAndFlush(new FlightEntity(origin, destination, price, flightTimestamp, flightNumber));
		
		emailService.sendOfferEmail(flightEntity, "uncapped");
		
		return 	flightEntity;
	}
}
