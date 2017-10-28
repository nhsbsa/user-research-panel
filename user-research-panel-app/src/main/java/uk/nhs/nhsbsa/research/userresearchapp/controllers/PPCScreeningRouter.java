package uk.nhs.nhsbsa.research.userresearchapp.controllers;

import uk.nhs.nhsbsa.research.userresearchapp.forms.ScreeningPurchasedPPC;
import uk.nhs.nhsbsa.research.userresearchapp.vo.NHSBSASystem;
import uk.nhs.nhsbsa.research.userresearchapp.vo.ParticipantVO;

public class PPCScreeningRouter {

	private PPCScreeningRouter() {
	}
	
	public static String getNextScreenInFlow(ScreeningPurchasedPPC model) {
		String page;
		if(model.isPreviousPpc()) {
		    page = ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_SCREENING_PPC_PREVIOUS_METHOD;
		}
		else {
			page = ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_PARTICIPANT_CONSENT;
		}
		return page;
	}

	public static String getNextScreenInFlow(ParticipantVO participant) {
		String path = ResearchPaths.PATH_PARTICIPANT_CONSENT;
		if(NHSBSASystem.PPC.toString().equals(participant.getSystem())) {
			path = ResearchPaths.PATH_SCREENING_PPC_PREVIOUS;
		}
		return ResearchPaths.PATH_REDIRECT + path;
	}
}
