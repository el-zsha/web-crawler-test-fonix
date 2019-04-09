package web.crawler.test.fonix.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlightEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String origin;
	private String destination;
	private BigDecimal price;
	private Timestamp date;
	private String flightNumber;
	
	public FlightEntity() {
		// TODO Auto-generated constructor stub
	}
	public FlightEntity(String origin, String destination, BigDecimal price, Timestamp date, String flightNumber) {
		this.origin = origin;
		this.destination = destination;
		this.price = price;
		this.date = date;
		this.flightNumber = flightNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "FlightEntity [id=" + id + ", origin=" + origin + ", destination=" + destination + ", price=" + price
				+ ", date=" + date + ", flightNumber=" + flightNumber + "]";
	}
	
}
