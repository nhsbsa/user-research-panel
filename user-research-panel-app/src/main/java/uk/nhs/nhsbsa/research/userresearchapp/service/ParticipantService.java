package uk.nhs.nhsbsa.research.userresearchapp.service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.nhs.nhsbsa.research.userresearchapp.vo.ParticipantVO;
import uk.nhs.nhsbsa.research.userresearchapp.entity.Participant;
import uk.nhs.nhsbsa.research.userresearchapp.repository.ParticipantRepository;
import uk.nhs.nhsbsa.research.userresearchapp.util.ParticipantMapper;

@Service
public class ParticipantService implements IParticipantService {

	private ParticipantRepository repository;
	
	@Autowired
	public ParticipantService(ParticipantRepository repository) {
		this.repository = repository;
	}
	
	/* (non-Javadoc)
	 * @see uk.nhs.nhsbsa.research.userresearchapp.service.IParticipantService#register(uk.nhs.nhsbsa.research.userresearchapp.entity.Participant)
	 */
	@Override
	public void register(final ParticipantVO participant) {
		if(participant == null) {
			throw new IllegalArgumentException("Participant must be a value");
		}		
		
		Participant p = ParticipantMapper.createParticipantEntity(participant);
		p.setRegistrationDate(
				Date.from(
						LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)
						));
		
		repository.save(p);
	}
}
