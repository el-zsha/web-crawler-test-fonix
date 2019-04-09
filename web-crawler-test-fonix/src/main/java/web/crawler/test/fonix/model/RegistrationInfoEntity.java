package web.crawler.test.fonix.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegistrationInfoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String origin;
	private String destination;
	private String email;
	private String frequency;
	private BigDecimal bestSeenPrice;
	
	public RegistrationInfoEntity() {
		// TODO Auto-generated constructor stub
	}
	public RegistrationInfoEntity(String origin, String destination, String email, String frequency) {
		this.origin = origin;
		this.destination = destination;
		this.email = email;
		this.frequency = frequency;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public BigDecimal getBestSeenPrice() {
		return bestSeenPrice;
	}
	public void setBestSeenPrice(BigDecimal bestSeenPrice) {
		this.bestSeenPrice = bestSeenPrice;
	}
	@Override
	public String toString() {
		return "RegistrationInfoEntity [id=" + id + ", origin=" + origin + ", destination=" + destination + ", email="
				+ email + ", frequency=" + frequency + ", bestSeenPrice=" + bestSeenPrice + "]";
	}	
}
