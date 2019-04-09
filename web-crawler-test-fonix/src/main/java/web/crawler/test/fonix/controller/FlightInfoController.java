package web.crawler.test.fonix.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.crawler.test.fonix.model.FlightEntity;
import web.crawler.test.fonix.model.RegistrationInfoEntity;
import web.crawler.test.fonix.service.FlightService;

@RestController
public class FlightInfoController {
	
	private final FlightService flightInfoService;

	
	@Autowired
	public FlightInfoController(final FlightService flightInfoService) {
		this.flightInfoService = flightInfoService;
	}
	@GetMapping("/flight")
    public ResponseEntity<String> findAllFlights() {
        return ResponseEntity.ok("all flights");
    }
	
	@PostMapping("/notificationRegister")
    public ResponseEntity<Void> registerForNotification(@RequestParam(name = "origin") String origin,
    													  @RequestParam(name = "destination") String destination,
    													  @RequestParam(name = "email") String email,
    													  @RequestParam(name = "frequency") String frequency) {
		
		RegistrationInfoEntity registrationInfoEntity = new RegistrationInfoEntity(origin, destination, email, frequency);
		registrationInfoEntity = flightInfoService.registerFlightNotificationRequest(registrationInfoEntity);
        
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("index.html"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
	
	@PostMapping("/addFlight")
    public ResponseEntity<Void> addFlight(@RequestParam(name = "origin") String origin,
										    @RequestParam(name = "destination") String destination,
										    @RequestParam(name = "price") BigDecimal price,
										    @RequestParam(name = "date") String date,
										    @RequestParam(name = "flightNumber") String flightNumber) throws URISyntaxException {
		
		FlightEntity flightEntity = flightInfoService.addFlight(origin, destination, price, date, flightNumber);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("addflight.html"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
