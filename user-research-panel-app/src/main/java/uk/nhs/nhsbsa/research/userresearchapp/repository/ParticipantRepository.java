package uk.nhs.nhsbsa.research.userresearchapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import uk.nhs.nhsbsa.research.userresearchapp.entity.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
	
	List<Participant> findByRegistrationDate(Date registrationDate);
}
