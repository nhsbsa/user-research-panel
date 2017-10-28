package uk.nhs.nhsbsa.research.userresearchapp.util;

import uk.nhs.nhsbsa.research.userresearchapp.entity.Participant;
import uk.nhs.nhsbsa.research.userresearchapp.vo.ParticipantVO;

public class ParticipantMapper {

	private ParticipantMapper() {
	}
	
	public static Participant createParticipantEntity(ParticipantVO participant) {
		Participant p = new Participant();
		
		p.setName(participant.getName());
		p.setEmail(participant.getEmail());
		p.setHomeTelephone(participant.getTelephone());
		
		return p;
	}
}
