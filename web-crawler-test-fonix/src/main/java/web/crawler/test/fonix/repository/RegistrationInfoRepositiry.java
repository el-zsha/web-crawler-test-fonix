package web.crawler.test.fonix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.crawler.test.fonix.model.RegistrationInfoEntity;

@Repository
public interface RegistrationInfoRepositiry extends JpaRepository<RegistrationInfoEntity, Long>{
	Optional<RegistrationInfoEntity> findByEmail(String email); 
	List<RegistrationInfoEntity> findByFrequency(String frequency);
	List<RegistrationInfoEntity> findByOriginAndDestinationAndFrequency(String origin, String destination, String frequency);
}
