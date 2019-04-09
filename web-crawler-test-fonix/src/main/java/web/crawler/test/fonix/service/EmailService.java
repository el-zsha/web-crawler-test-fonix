package web.crawler.test.fonix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.crawler.test.fonix.model.FlightEntity;
import web.crawler.test.fonix.model.RegistrationInfoEntity;
import web.crawler.test.fonix.repository.FlightRepository;
import web.crawler.test.fonix.repository.RegistrationInfoRepositiry;

@Service
public class EmailService {

	private FlightRepository flightRepository;
	private RegistrationInfoRepositiry registrationInfoRepositiry;
	
	@Autowired
	public EmailService(FlightRepository flightRepository, RegistrationInfoRepositiry registrationInfoRepositiry) {
		this.flightRepository = flightRepository;
		this.registrationInfoRepositiry = registrationInfoRepositiry;
	}
	public void sendOfferEmail(FlightEntity flightEntity, String frequency){
				
		List<RegistrationInfoEntity> users = registrationInfoRepositiry.findByOriginAndDestinationAndFrequency(flightEntity.getOrigin(), 
				flightEntity.getDestination(), frequency);
		for (RegistrationInfoEntity registrationInfoEntity : users) {
			if (registrationInfoEntity.getBestSeenPrice() != null) {
				if(flightEntity.getPrice().compareTo(registrationInfoEntity.getBestSeenPrice()) == -1) {
					System.out.println("Hi " + registrationInfoEntity.getEmail() + ", latest offer for your interested route:" + flightEntity.toString());
					registrationInfoEntity.setBestSeenPrice(flightEntity.getPrice());
					registrationInfoRepositiry.saveAndFlush(registrationInfoEntity);
				}
			} else {
				System.out.println("Hi " + registrationInfoEntity.getEmail() + ", latest offer for your interested route:" + flightEntity.toString());
				registrationInfoEntity.setBestSeenPrice(flightEntity.getPrice());
				registrationInfoRepositiry.saveAndFlush(registrationInfoEntity);
			}
		}
	}
	
	public void sendOfferEmailForScheduledUser(String frequency) {
		List<RegistrationInfoEntity> users = registrationInfoRepositiry.findByFrequency(frequency);
		for (RegistrationInfoEntity registrationInfoEntity : users) {
			List<FlightEntity> flights  = flightRepository.findByOriginAndDestinationOrderByPriceAsc(registrationInfoEntity.getOrigin(), 
					registrationInfoEntity.getDestination());
			
			if(flights.size() > 0) {
				if (registrationInfoEntity.getBestSeenPrice() != null) {
					if(flights.get(0).getPrice().compareTo(registrationInfoEntity.getBestSeenPrice()) == -1) {
						System.out.println("Hi " + registrationInfoEntity.getEmail() + ", latest offer for your interested route:" + flights.get(0).toString());
						registrationInfoEntity.setBestSeenPrice(flights.get(0).getPrice());
						registrationInfoRepositiry.saveAndFlush(registrationInfoEntity);
					}
				} else {
					System.out.println("Hi " + registrationInfoEntity.getEmail() + ", latest offer for your interested route:" + flights.get(0).toString());
					registrationInfoEntity.setBestSeenPrice(flights.get(0).getPrice());
					registrationInfoRepositiry.saveAndFlush(registrationInfoEntity);
				}
			}	
		}
	}
	public void sendWelcomeEmailToNewUser(RegistrationInfoEntity registrationInfoEntity, FlightEntity cheapestFlight) {
		System.out.println("Hi " + registrationInfoEntity.getEmail() + " Welcome to web crawler");
	}
	
}
