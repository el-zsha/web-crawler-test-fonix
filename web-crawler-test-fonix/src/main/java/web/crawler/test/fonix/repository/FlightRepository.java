package web.crawler.test.fonix.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import web.crawler.test.fonix.model.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    @Query("SELECT fe FROM FlightEntity fe where fe.origin = :origin and fe.destination = :destination and price > :price") 
	List<FlightEntity> findByOriginAndDestination(@Param("origin") String origin, 
												  @Param("destination") String destination, 
												  @Param("price") BigDecimal price);
    
    List<FlightEntity> findByOriginAndDestinationOrderByPriceAsc(String origin, String destination);
    
    List<FlightEntity> findByOriginAndDestinationAndIdNotOrderByPriceAsc(String origin, String destination, Long id);
}
